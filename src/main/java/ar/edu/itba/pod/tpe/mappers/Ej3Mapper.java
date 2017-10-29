package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import ar.edu.itba.pod.tpe.utils.Employment_Status;
import ar.edu.itba.pod.tpe.utils.ProvinceTo;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class Ej3Mapper implements Mapper<String,CensusEntry,String,Boolean> {

	@Override
	public void map(String s, CensusEntry censusEntry, Context<String, Boolean> context) {
		String region = ProvinceTo.region.get(censusEntry.getProvince().toLowerCase());
		if(censusEntry.getOcupation() == Employment_Status.EMPLOYED.getValue())
			context.emit(region,true);
		else if(censusEntry.getOcupation() == Employment_Status.UNEMPLOYED.getValue())
			context.emit(region,false);
	}
}
