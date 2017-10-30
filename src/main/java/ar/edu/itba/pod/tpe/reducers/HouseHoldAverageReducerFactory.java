package ar.edu.itba.pod.tpe.reducers;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.text.DecimalFormat;
import java.util.HashMap;

public class HouseHoldAverageReducerFactory implements ReducerFactory<String, Integer, Float> {
    @Override
    public Reducer<Integer, Float> newReducer(String s) {
        return new HouseHoldAverageReducer();
    }

    private class HouseHoldAverageReducer extends Reducer<Integer, Float> {

        private HashMap<Integer,Integer> houses;

        HouseHoldAverageReducer(){
            houses = new HashMap<>();
        }
        @Override
        public void reduce(Integer houseHold) {
            if(houses.containsKey(houseHold)){
                houses.put(houseHold, houses.get(houseHold) + 1);
            }else{
                houses.put(houseHold,1);
            }
        }

        @Override
        public Float finalizeReduce() {
            float sum = 0;
            for (int i: houses.values()){
                sum += i;
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            return Float.valueOf(decimalFormat.format(sum / houses.size()));
        }
    }
}
