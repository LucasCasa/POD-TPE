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

	public Set<KeyValue<String,Integer>> collate(Iterable<Map.Entry<String, Integer>> iterable) {
		Set<KeyValue<String,Integer>> sorted = super.collate(iterable);
		int count = 0;
		Set<KeyValue<String,Integer>> target = new TreeSet<>((KeyValue<String,Integer> o1, KeyValue<String,Integer> o2) -> o2.getValue()-o1.getValue());
		for (KeyValue<String,Integer> kv: sorted) {
			if (count >= n) break;

			target.add(kv);
			count++;
		}
		return target;
	}
}
