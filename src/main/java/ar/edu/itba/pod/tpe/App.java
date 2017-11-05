package ar.edu.itba.pod.tpe;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import ar.edu.itba.pod.tpe.utils.ParamLoader;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.*;
import com.hazelcast.mapreduce.*;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class App {
	final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws Exception{
		//HazelcastInstance hc = Hazelcast.newHazelcastInstance();
		ParamLoader params = new ParamLoader();

		final HazelcastInstance hi = HazelcastClient.newHazelcastClient(params.getConfig());

		JobTracker jt = hi.getJobTracker("province-pairs");
        IList<CensusEntry> set = hi.getList("Data");

		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\n");
		settings.getFormat().setDelimiter(',');
		CsvParser parser = new CsvParser(settings);

		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("%d{dd/MM/yyyy HH:mm:ss:SSSS} - %m%n");
		FileAppender appender = new FileAppender(layout, params.getLogFile(), false);
		logger.addAppender(appender);
		long time = System.currentTimeMillis();
		logger.info("Start to read File");

		List<String[]> allRows = parser.parseAll(getReader(params.getDataPath()));
		System.out.println(allRows.size());
		List<CensusEntry> nl = new ArrayList<>(1000000);
        for(String[] row : allRows){
            nl.add(new CensusEntry(row));
        }
    System.out.println("Adding to IList");
    set.addAll(nl);
		System.out.println("Finished Adding to IList");
		logger.info("Finished Reading and Parsing data. Time elapsed: " + (System.currentTimeMillis() - time) + "ms");
		System.out.println(set.size());
        final KeyValueSource<String, CensusEntry> source = KeyValueSource.fromList(set);
        Job<String, CensusEntry> job = jt.newJob(source);
		ICompletableFuture<Set<KeyValue>> future;
		if(params.hasCombiner()){
			future = job
					.mapper(params.getMapper())
					//.combiner(params.getCombiner())
					.reducer(params.getReducer())
					.submit(params.getCollator());
		}else{
			future = job
					.mapper(params.getMapper())
					.reducer(params.getReducer())
					.submit(params.getCollator());
		}
		time = System.currentTimeMillis();
		logger.info("Starting");
		System.out.println("ST");
		Set<KeyValue> res = future.get();
		System.out.println("Fin");
		logger.info("Finished: Job completed in " + (System.currentTimeMillis() - time) + "ms");
		BufferedWriter writer = new BufferedWriter(new FileWriter(params.getOutPath()));
		for(KeyValue o : res){
			writer.write(o.getKey() + " " + o.getValue() + "\n");
		}
		writer.close();
        System.out.println(res);

    }

	/**
	 * Creates a reader for a resource in the relative path
	 *
	 * @param relativePath relative path of the resource to be read
	 *
	 * @return a reader of the resource
	 */
	 private static Reader getReader(String relativePath) {
		try {
			return new InputStreamReader(new FileInputStream(relativePath), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Unable to read input", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}