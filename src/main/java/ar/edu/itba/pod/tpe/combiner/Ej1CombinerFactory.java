package ar.edu.itba.pod.tpe.combiner;

import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

public class Ej1CombinerFactory implements CombinerFactory<String,Integer,Integer>{

	@Override
	public Combiner<Integer, Integer> newCombiner(String s) {
		return new Ej1Combiner();
	}

	private class Ej1Combiner extends Combiner<Integer,Integer>{
		int count = 0;

		@Override
		public void combine(Integer integer) {
			count++;
		}

		@Override
		public Integer finalizeChunk() {
			return count;
		}
	}
}
