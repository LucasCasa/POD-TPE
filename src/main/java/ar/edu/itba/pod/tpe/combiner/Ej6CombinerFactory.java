package ar.edu.itba.pod.tpe.combiner;

import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nlopez on 11/5/17.
 */
public class Ej6CombinerFactory implements CombinerFactory<String, String, Set<String>> {
    @Override

    public Combiner<String, Set<String>> newCombiner(String key) {
        return new Ej6Combiner();
    }

    private class Ej6Combiner extends Combiner<String, Set<String>> {
        Set<String> prov;

        public Ej6Combiner() {
            this.prov = new HashSet<>();
        }

        public void reset() {
            this.prov = new HashSet<>();
        }

        @Override
        public void combine(String s) {
            prov.add(s);
        }

        @Override
        public Set<String> finalizeChunk() {
            return prov;
        }
    }
}
