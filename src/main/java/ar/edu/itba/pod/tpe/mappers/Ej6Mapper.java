package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class Ej6Mapper implements Mapper<String, CensusEntry, String, String> {

	public Ej6Mapper() {}

	@Override
	public void map(String s, CensusEntry censusEntry, Context<String, String> context) {
			context.emit(censusEntry.getDepartment(),censusEntry.getProvince());
	}
}
