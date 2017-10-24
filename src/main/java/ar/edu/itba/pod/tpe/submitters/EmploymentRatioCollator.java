package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.EmploymentStatistics;
import ar.edu.itba.pod.tpe.utils.Employment_Status;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EmploymentRatioCollator implements Collator<Map.Entry<String,EmploymentStatistics>, Set<KeyValue<Double>>> {
	Set<KeyValue<Double>> ratios = new TreeSet<>(new Comparator<KeyValue<Double>>() {
		@Override
		public int compare(KeyValue<Double> o1, KeyValue<Double> o2) {
			if(o2.getValue()-o1.getValue()>0)
				return 1;
			else if(o2.getValue()-o1.getValue()<0)
				return -1;
			return 0;
		}
	});

	@Override
	public Set<KeyValue<Double>> collate(Iterable<Map.Entry<String, EmploymentStatistics>> iterable) {
		for(Map.Entry<String,EmploymentStatistics> e : iterable){
			EmploymentStatistics stats = e.getValue();
			double ratio = ((double)stats.getEmployed()/((double)stats.getEmployed()+stats.getUnemployed()));
			ratios.add(new KeyValue<Double>(e.getKey(),ratio));
		}
		return ratios;
	}
}
