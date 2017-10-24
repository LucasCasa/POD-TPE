package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TopNFromDescendantSortedCollator extends DescendantSortedCollator {

	private int n;

	public TopNFromDescendantSortedCollator(int n){
		super();
		this.n = n;
	}

	public Set<KeyValue> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		Set<KeyValue> sorted = super.collate(iterable);
		int count = 0;
		Set<KeyValue> target = new TreeSet<>();
		for (KeyValue kv: sorted) {
			if (count >= n) break;

			target.add(kv);
			count++;
		}
		return target;
	}
}
