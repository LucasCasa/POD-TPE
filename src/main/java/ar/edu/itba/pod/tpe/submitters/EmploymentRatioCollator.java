package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.EmploymentStatistics;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EmploymentRatioCollator implements Collator<Map.Entry<String,EmploymentStatistics>, Set<KeyValue<Double>>> {
	Set<KeyValue<Double>> ratios = new TreeSet<>();

	@Override
	public Set<KeyValue<Double>> collate(Iterable<Map.Entry<String, EmploymentStatistics>> iterable) {
		for(Map.Entry<String,EmploymentStatistics> e : iterable){
			EmploymentStatistics stats = e.getValue();
			double ratio = ((double)stats.getEmployed()/((double)stats.getEmployed()+stats.getUnemployed()));
			ratios.add(new KeyValue<>(e.getKey(),ratio));
		}
		return ratios;
	}
}
