package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.ProvinceTo;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class Ej1Mapper implements Mapper<String,CensusEntry,Integer,Integer>{

    public Ej1Mapper() {}

    @Override
    public void map(String s, CensusEntry ce, Context<Integer, Integer> context) {
        String aux = ce.getProvince().toLowerCase();
        Integer t = ProvinceTo.provinceToRegionId.get(aux);
        context.emit(t,1);
    }
}
