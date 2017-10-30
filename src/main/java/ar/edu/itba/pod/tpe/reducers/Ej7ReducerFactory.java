package ar.edu.itba.pod.tpe.reducers;

import ar.edu.itba.pod.tpe.utils.ProvincePair;
import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.util.*;

public class Ej7ReducerFactory implements ReducerFactory<String, String, Set<ProvincePair>> {
	@Override
	public Reducer<String, Set<ProvincePair>> newReducer(String s) {
		return new Ej7Reducer();
	}

	private class Ej7Reducer extends Reducer<String, Set<ProvincePair>> {

		List<String> provinces = new ArrayList<>();

		@Override
		public void reduce(String s) {
			if(!provinces.contains(s))
				provinces.add(s);
		}

		@Override
		public Set<ProvincePair> finalizeReduce() {
			Set<ProvincePair> set = new HashSet<>();
			for(int i=0; i<provinces.size(); i++){
				for(int j=i+1; j<provinces.size(); j++)
					set.add(new ProvincePair(provinces.get(i),provinces.get(j)));
			}
			return set;
		}
	}
}
