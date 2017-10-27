package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TopNFromDescendantsWithMinimumSortedCollator<T extends Comparable> extends DescendantSortedWithMinimumCollator<T>{
	int count = 0;

	public TopNFromDescendantsWithMinimumSortedCollator(T minimum, int count){
		super(minimum);
		this.count = count;
	}

	@Override
	public Set<KeyValue> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		Set<KeyValue> sorted = super.collate(iterable);
		Set<KeyValue> filtered = new TreeSet<>();
		int i = 0;
		for(KeyValue kv : sorted){
			if(i++ < count)
				filtered.add(kv);
			else
				break;
		}
		return filtered;
	}
}
