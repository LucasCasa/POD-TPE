package ar.edu.itba.pod.tpe.combiner;

import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

import java.util.Set;

/**
 * Created by nlopez on 11/5/17.
 */
public class Ej4CombinerFactory implements CombinerFactory<String ,Integer, Integer> {
    @Override
    public Combiner<Integer, Integer> newCombiner(String key) {
        return new Ej4Combiner();
    }

    private class Ej4Combiner extends Combiner<Integer, Integer> {
        private int count;

        public Ej4Combiner() {
            this.count = 0;
        }

        public void reset() {
            this.count = 0;
        }

        @Override
        public void combine(Integer integer) {
            count += integer;
        }

        @Override
        public Integer finalizeChunk() {
            return count;
        }
    }
}
