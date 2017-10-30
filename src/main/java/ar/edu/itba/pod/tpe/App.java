package ar.edu.itba.pod.tpe;

import ar.edu.itba.pod.tpe.mappers.Ej6Mapper;
import ar.edu.itba.pod.tpe.mappers.Ej7Mapper;
import ar.edu.itba.pod.tpe.reducers.Ej6ReducerFactory;
import ar.edu.itba.pod.tpe.reducers.Ej7ReducerFactory;
import ar.edu.itba.pod.tpe.submitters.ProvincePairCounterCollator;
import ar.edu.itba.pod.tpe.submitters.TopNFromDescendantsWithMinimumSortedCollator;
import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import ar.edu.itba.pod.tpe.utils.ProvincePair;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import com.hazelcast.mapreduce.*;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception{
        HazelcastInstance h = Hazelcast.newHazelcastInstance();
        final HazelcastInstance hi = HazelcastClient.newHazelcastClient();

        JobTracker jt = hi.getJobTracker("province-pairs");
        Scanner s = new Scanner(new File("census100.csv"));
        ISet<CensusEntry> set = hi.getSet("censusData669");
        while(s.hasNextLine()){
            set.add(new CensusEntry(s.nextLine()));
        }
        final KeyValueSource<String, CensusEntry> source = KeyValueSource.fromSet(set);
        Job<String, CensusEntry> job = jt.newJob(source);

        ICompletableFuture<Set<KeyValue<ProvincePair,Integer>>> future = job
                .mapper(new Ej7Mapper())
                .reducer(new Ej7ReducerFactory())
                .submit(new ProvincePairCounterCollator(2));

        System.out.println(future.get());
    }

}