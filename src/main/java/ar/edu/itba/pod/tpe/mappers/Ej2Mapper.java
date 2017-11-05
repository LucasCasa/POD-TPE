package ar.edu.itba.pod.tpe.mappers;

import ar.edu.itba.pod.tpe.utils.CensusEntry;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class Ej2Mapper implements Mapper<String,CensusEntry,String,Integer> {

	private String province;

	public Ej2Mapper(String province){
		this.province = province;
	}

	@Override
	public void map(String s, CensusEntry censusEntry, Context<String, Integer> context) {
		if(censusEntry.getProvince().equals(province)){
			context.emit(censusEntry.getDepartment(),1);
		}
	}
}
