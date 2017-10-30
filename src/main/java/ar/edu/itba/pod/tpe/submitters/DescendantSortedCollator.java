package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.*;

public class DescendantSortedCollator<T extends Comparable<T>> implements Collator<Map.Entry<String,T>, Set<KeyValue<String, T>>> {
	SortedSet<KeyValue<String, T>> sorted = new TreeSet<>();

	@Override
	public Set<KeyValue<String, T>> collate(Iterable<Map.Entry<String, T>> iterable) {
		for(Map.Entry<String,T> e : iterable){
			sorted.add(new KeyValue<>(e.getKey(),e.getValue()));
		}
		return sorted;
	}
}
