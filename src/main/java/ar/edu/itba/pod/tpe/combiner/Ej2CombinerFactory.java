package ar.edu.itba.pod.tpe.combiner;

import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

/**
 * Created by nlopez on 11/5/17.
 */

public class Ej2CombinerFactory implements CombinerFactory<String, Integer, Integer> {

    @Override
    public Combiner<Integer, Integer> newCombiner(String key) {
        return new Ej2Combiner();
    }

    private class Ej2Combiner extends Combiner<Integer, Integer> {

        private int total;

        public Ej2Combiner() {
            this.total = 0;
        }

        @Override
        public void combine(Integer value) {
            total += value;
        }

        @Override
        public void reset() {
            total = 0;
        }

        @Override
        public Integer finalizeChunk() {
            return total;
        }
    }

}