package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class Ej7Mapper implements Mapper<String, CensusEntry, String, String> {

	@Override
	public void map(String s, CensusEntry censusEntry, Context<String, String> context) {
		context.emit(censusEntry.getDepartment(), censusEntry.getProvince());
	}
}
