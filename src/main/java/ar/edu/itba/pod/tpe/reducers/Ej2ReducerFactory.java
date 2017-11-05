package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class Ej2ReducerFactory implements ReducerFactory<String, Integer, Integer> {

	public Reducer<Integer, Integer> newReducer(String string) {
		return new Ej2Reducer();
	}

	private class Ej2Reducer extends Reducer<Integer, Integer> {
		int count = 0;

		@Override
		public void reduce(Integer integer) {
			count+= integer;
		}

		@Override
		public Integer finalizeReduce() {
			return count;
		}
	}
}
