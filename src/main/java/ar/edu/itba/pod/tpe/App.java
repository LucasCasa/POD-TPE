package ar.edu.itba.pod.tpe;

import ar.edu.itba.pod.tpe.mappers.Ej2_Mapper;
import ar.edu.itba.pod.tpe.mappers.ProvinceMapper;
import ar.edu.itba.pod.tpe.reducers.Ej2_ReducerFactory;
import ar.edu.itba.pod.tpe.reducers.ProvinceReducerFactory;
import ar.edu.itba.pod.tpe.submitters.DescendantSortedCollator;
import ar.edu.itba.pod.tpe.submitters.TopNFromDescendantSortedCollator;
import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.KeyValue;
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
        Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        final ClientConfig cc = new ClientConfig();
        final HazelcastInstance hi = HazelcastClient.newHazelcastClient(cc);

        JobTracker jt = hi.getJobTracker("province-count");
        Scanner s = new Scanner(new File("census100.csv"));
        ISet<CensusEntry> set = hi.getSet("censusData666");
        while(s.hasNextLine()){
            set.add(new CensusEntry(s.nextLine()));
        }
        final KeyValueSource<String, CensusEntry> source = KeyValueSource.fromSet(set);
        Job<String, CensusEntry> job = jt.newJob(source);
        ICompletableFuture<Set<KeyValue>> future = job
                .mapper(new Ej2_Mapper("Buenos Aires"))
                .reducer(new Ej2_ReducerFactory())
                .submit(new TopNFromDescendantSortedCollator(10));

        System.out.println(future.get());
    }

}