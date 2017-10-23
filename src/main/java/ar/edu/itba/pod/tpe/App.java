package ar.edu.itba.pod.tpe;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import com.hazelcast.mapreduce.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception{
        Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        final ClientConfig cc = new ClientConfig();
        final HazelcastInstance hi = HazelcastClient.newHazelcastClient(cc);

        JobTracker jt = hi.getJobTracker("province-count");
        Scanner s = new Scanner(new File("census100.csv"));
        IMap<Integer,String> map = hi.getMap("censusData666");
        while(s.hasNextLine()){
            String[] data = s.nextLine().split(",");
            map.put(Integer.parseInt(data[1]),data[3]);
        }
        final KeyValueSource<Integer,String> source = KeyValueSource.fromMap(map);
        Job<Integer, String> job = jt.newJob(source);
        ICompletableFuture<Map<String,Integer>> future = job.mapper(new ProvinceMapper()).reducer(new ProvinceReducerFactory()).submit();

        System.out.println(future.get());
    }

}