package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.ProvinceTo;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import java.util.HashSet;

public class HouseHoldMapper implements Mapper<String,CensusEntry,String ,Integer> {

    HashSet<Integer> houseHolds;

    public HouseHoldMapper(){
        houseHolds = new HashSet<>();
    }

    @Override
    public void map(String s, CensusEntry cE, Context<String, Integer> context) {
        if(!houseHolds.contains(cE.getId())){
            houseHolds.add(cE.getId());
            String t = ProvinceTo.region.get(cE.getProvince().toLowerCase());
            context.emit(t,1);
        }
    }
}
