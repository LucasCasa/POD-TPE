package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.util.HashSet;
import java.util.Set;

public class Ej6ReducerFactory implements ReducerFactory<String, String, Integer> {
	public Reducer<String, Integer> newReducer(String string) {
		return new Ej6ReducerFactory.Ej6Reducer();
	}

	private class Ej6Reducer extends Reducer<String, Integer> {
		int count = 0;
		Set<String> counted = new HashSet<>();

		@Override
		public void reduce(String s) {
			if(!counted.contains(s))
				count++;
			counted.add(s);
		}

		@Override
		public Integer finalizeReduce() {
			return counted.size();
		}
	}
}
