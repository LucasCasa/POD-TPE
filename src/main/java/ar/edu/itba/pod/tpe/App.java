package ar.edu.itba.pod.tpe;

import ar.edu.itba.pod.tpe.mappers.Ej7Mapper;
import ar.edu.itba.pod.tpe.reducers.Ej7ReducerFactory;
import ar.edu.itba.pod.tpe.submitters.ProvincePairCounterCollator;
import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import ar.edu.itba.pod.tpe.utils.ParamLoader;
import ar.edu.itba.pod.tpe.utils.ProvincePair;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.*;
import com.hazelcast.mapreduce.*;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception{

    	final HazelcastInstance hi = HazelcastClient.newHazelcastClient();
		ParamLoader params = new ParamLoader();

        JobTracker jt = hi.getJobTracker("province-pairs");
        Scanner s = new Scanner(new File(params.getDataPath()));
        ISet<CensusEntry> set = hi.getSet("DataSet");
        while(s.hasNextLine()){
            set.add(new CensusEntry(s.nextLine()));
        }

        final KeyValueSource<String, CensusEntry> source = KeyValueSource.fromSet(set);
        Job<String, CensusEntry> job = jt.newJob(source);
		ICompletableFuture<Set<KeyValue>> future;
		if(params.hasCombiner()){
			future = job
					.mapper(params.getMapper())
					.reducer(params.getReducer())
					.submit(params.getCollator());
		}else{
			future = job
					.mapper(params.getMapper())
					.combiner(params.getCombiner())
					.reducer(params.getReducer())
					.submit(params.getCollator());
		}

        System.out.println(future.get());
    }

}