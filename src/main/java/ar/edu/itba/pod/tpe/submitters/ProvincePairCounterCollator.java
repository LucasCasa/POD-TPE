package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;
import ar.edu.itba.pod.tpe.utils.ProvincePair;
import com.hazelcast.mapreduce.Collator;

import java.util.*;

public class ProvincePairCounterCollator implements Collator<Map.Entry<String,Set<ProvincePair>>, Set<KeyValue<ProvincePair,Integer>>>{

	private int n;

	public ProvincePairCounterCollator(int n){
		this.n = n;
	}

	@Override
	public Set<KeyValue<ProvincePair,Integer>> collate(Iterable<Map.Entry<String, Set<ProvincePair>>> iterable) {
		Map<ProvincePair,Integer> map = new TreeMap<>();
		for(Map.Entry<String, Set<ProvincePair>> entry: iterable){
			for(ProvincePair pp: entry.getValue()){
				if(map.containsKey(pp))
					map.put(pp,map.get(pp)+1);
				else
					map.put(pp,1);
			}
		}

		Set<KeyValue<ProvincePair,Integer>> res = new TreeSet<>();

		for(Map.Entry<ProvincePair,Integer> entry: map.entrySet()){
			if(entry.getValue()>=n)
				res.add(new KeyValue<>(entry.getKey(),entry.getValue()));
		}

		return res;
	}
}
