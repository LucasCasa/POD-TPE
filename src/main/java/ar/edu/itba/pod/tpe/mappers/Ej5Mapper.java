package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.ProvinceTo;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class Ej5Mapper implements Mapper<String,CensusEntry,String ,Integer> {

    public Ej5Mapper(){ }

    @Override
    public void map(String s, CensusEntry cE, Context<String, Integer> context) {
        String t = ProvinceTo.region.get(cE.getProvince().toLowerCase());
        context.emit(t, cE.getId());
    }
}
