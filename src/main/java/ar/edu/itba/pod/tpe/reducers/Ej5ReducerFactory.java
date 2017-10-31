package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.text.DecimalFormat;
import java.util.HashSet;

public class Ej5ReducerFactory implements ReducerFactory<String, Integer, Float> {
    @Override
    public Reducer<Integer, Float> newReducer(String s) {
        return new Ej5Reducer();
    }

    private class Ej5Reducer extends Reducer<Integer, Float> {

        private HashSet<Integer> houses;
        private int counterPop = 0;

        Ej5Reducer(){
            houses = new HashSet<>();
        }
        @Override
        public void reduce(Integer houseHold) {
            if(!houses.contains(houseHold)){
                houses.add(houseHold);
            }
            counterPop += 1;
        }

        @Override
        public Float finalizeReduce() {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            return Float.valueOf(decimalFormat.format(counterPop / houses.size()));
        }
    }
}
