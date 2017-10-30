package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TopNFromDescendantSortedCollator<T extends Comparable<T>> extends DescendantSortedCollator<T> {

	private int n;

	public TopNFromDescendantSortedCollator(int n){
		super();
		this.n = n;
	}

	public Set<KeyValue<String, T>> collate(Iterable<Map.Entry<String, T>> iterable) {
		Set<KeyValue<String, T>> sorted = super.collate(iterable);
		int count = 0;
		Set<KeyValue<String, T>> target = new TreeSet<>();
		for (KeyValue<String, T> kv: sorted) {
			if (count >= n) break;

			target.add(kv);
			count++;
		}
		return target;
	}
}
