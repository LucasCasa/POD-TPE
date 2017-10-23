package ar.edu.itba.pod.tpe;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ProvinceReducerFactory implements ReducerFactory<String, Integer, Integer> {

    public Reducer<Integer, Integer> newReducer(String string) {
        return new ProvinceReducer();
    }

    private class ProvinceReducer extends Reducer<Integer, Integer> {
        int count = 0;

        @Override
        public void reduce(Integer integer) {
            count++;
        }

        @Override
        public Integer finalizeReduce() {
            return count;
        }
    }
}
