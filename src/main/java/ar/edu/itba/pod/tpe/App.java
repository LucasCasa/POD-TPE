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
		ParamLoader params = new ParamLoader();

		final HazelcastInstance hi = HazelcastClient.newHazelcastClient(params.getConfig());

		JobTracker jt = hi.getJobTracker("55165-55302-55206-53774");
        IList<CensusEntry> list = hi.getList("55165-55302-55206-53774");

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
		System.out.println("Start to read File");

		List<String[]> allRows = parser.parseAll(getReader(params.getDataPath()));
		List<CensusEntry> nl = new ArrayList<>(1000000);
        for(String[] row : allRows){
            nl.add(new CensusEntry(row));
        }
    	list.addAll(nl);
		logger.info("Finished Reading and Parsing data. Time elapsed: " + (System.currentTimeMillis() - time) + "ms");
		System.out.println("Finished Reading and Parsing data. Time elapsed: " + (System.currentTimeMillis() - time) + "ms");
		final KeyValueSource<String, CensusEntry> source = KeyValueSource.fromList(list);
        Job<String, CensusEntry> job = jt.newJob(source);
		ICompletableFuture<Set<KeyValue>> future;
		if(params.hasCombiner()){
			future = job
					.mapper(params.getMapper())
					.combiner(params.getCombiner())
					.reducer(params.getReducer())
					.submit(params.getCollator());
		}else{
			future = job
					.mapper(params.getMapper())
					.reducer(params.getReducer())
					.submit(params.getCollator());
		}
		time = System.currentTimeMillis();
		logger.info("Starting map/reduce job");
		System.out.println("Start map/reduce job");
		Set<KeyValue> res = future.get();
		long totalTime = (System.currentTimeMillis() - time);
		System.out.println("End of map/reduce job in " + totalTime + "ms");
		logger.info("Finished: Map/reduce job completed in " + totalTime + "ms");
		BufferedWriter writer = new BufferedWriter(new FileWriter(params.getOutPath()));
		for(KeyValue o : res){
			writer.write(o.getKey() + "," + o.getValue() + "\n");
		}
		writer.close();
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