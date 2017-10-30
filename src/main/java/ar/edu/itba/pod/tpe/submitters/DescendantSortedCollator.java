package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.*;

public class DescendantSortedCollator implements Collator<Map.Entry<String,Integer>, Set<KeyValue<String,Integer>>> {
	SortedSet<KeyValue<String,Integer>> sorted = new TreeSet<>((KeyValue<String,Integer> o1, KeyValue<String,Integer> o2) -> o2.getValue()-o1.getValue());

	@Override
	public Set<KeyValue<String,Integer>> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		for(Map.Entry<String,Integer> e : iterable){
			sorted.add(new KeyValue<>(e.getKey(),e.getValue()));
		}
		return sorted;
	}
}
