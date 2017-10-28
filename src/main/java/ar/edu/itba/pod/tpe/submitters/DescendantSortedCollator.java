package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.*;

public class DescendantSortedCollator implements Collator<Map.Entry<String,Integer>, Set<KeyValue<Integer>>> {
	SortedSet<KeyValue<Integer>> sorted = new TreeSet<>((KeyValue<Integer> o1, KeyValue<Integer> o2) -> o2.getValue()-o1.getValue());

	@Override
	public Set<KeyValue<Integer>> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		for(Map.Entry<String,Integer> e : iterable){
			sorted.add(new KeyValue<>(e.getKey(),e.getValue()));
		}
		return sorted;
	}
}
