package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;

public class Ej5ReducerFactory implements ReducerFactory<String, Integer, Float> {
    @Override
    public Reducer<Integer, Float> newReducer(String s) {
        return new Ej5Reducer();
    }

    private class Ej5Reducer extends Reducer<Integer, Float> {

        private HashSet<Integer> houses;
        private float counterPop = 0;

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
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
            DecimalFormat df = (DecimalFormat)nf;
            df.applyPattern("#.##");
            //return counterPop / houses.size();
            return Float.valueOf(df.format(counterPop / houses.size()));
        }
    }
}
