package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.util.HashSet;

public class Ej4ReducerFactory implements ReducerFactory<String, Integer, Integer> {
    @Override
    public Reducer<Integer, Integer> newReducer(String s) {
        return new Ej4Reducer();
    }

    private class Ej4Reducer extends Reducer<Integer, Integer> {

        private HashSet<Integer> houses;

        Ej4Reducer(){
            houses = new HashSet<>();
        }
        @Override
        public void reduce(Integer houseHold) {
            houses.add(houseHold);
        }

        @Override
        public Integer finalizeReduce() {
            return houses.size();
        }
    }
}
