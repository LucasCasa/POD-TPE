package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DescendantSortedWithMinimumCollator<T extends Comparable> implements Collator<Map.Entry<String,Integer>, Set<KeyValue>> {
	SortedSet<KeyValue> sorted = new TreeSet<>();
	T minimum;

	public DescendantSortedWithMinimumCollator(T minimum){
		this.minimum = minimum;
	}

	@Override
	public Set<KeyValue> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		for(Map.Entry<String,Integer> e : iterable){
			if(minimum.compareTo(e.getValue()) < 0) {
				sorted.add(new KeyValue(e.getKey(), e.getValue()));
			}
		}
		return sorted;
	}
}
