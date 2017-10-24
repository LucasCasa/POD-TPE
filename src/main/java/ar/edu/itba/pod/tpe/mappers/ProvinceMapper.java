package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.ProvinceTo;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ProvinceMapper implements Mapper<String,CensusEntry,String,Integer>{

    public ProvinceMapper() {

    }
    @Override
    public void map(String s, CensusEntry ce, Context<String, Integer> context) {
        StringTokenizer tokenizer = new StringTokenizer(ce.getProvince().toLowerCase());
        while(tokenizer.hasMoreTokens()) {
            String aux = tokenizer.nextToken(",");
            String t = ProvinceTo.region.get(aux);
            context.emit(t,1);
        }
    }
}
