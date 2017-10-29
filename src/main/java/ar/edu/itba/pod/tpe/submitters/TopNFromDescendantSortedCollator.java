package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.KeyValue;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TopNFromDescendantSortedCollator extends DescendantSortedCollator {

	private int n;

	public TopNFromDescendantSortedCollator(int n){
		super();
		this.n = n;
	}

	public Set<KeyValue<Integer>> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		Set<KeyValue<Integer>> sorted = super.collate(iterable);
		int count = 0;
		Set<KeyValue<Integer>> target = new TreeSet<>((KeyValue<Integer> o1, KeyValue<Integer> o2) -> o2.getValue()-o1.getValue());
		for (KeyValue<Integer> kv: sorted) {
			if (count >= n) break;

			target.add(kv);
			count++;
		}
		return target;
	}
}
