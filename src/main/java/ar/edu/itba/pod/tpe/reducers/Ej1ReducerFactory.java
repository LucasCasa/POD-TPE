package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class Ej1ReducerFactory implements ReducerFactory<Integer, Integer, Integer> {

    public Reducer<Integer, Integer> newReducer(Integer string) {
        return new ProvinceReducer();
    }

    private class ProvinceReducer extends Reducer<Integer, Integer> {
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
