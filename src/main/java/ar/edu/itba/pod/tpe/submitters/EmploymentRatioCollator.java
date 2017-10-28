package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.EmploymentStatistics;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.util.*;

public class EmploymentRatioCollator implements Collator<Map.Entry<String,EmploymentStatistics>, List<KeyValue<Double>>> {
	private List<KeyValue<Double>> ratios = new ArrayList<>();

	@Override
	public List<KeyValue<Double>> collate(Iterable<Map.Entry<String, EmploymentStatistics>> iterable) {
		for(Map.Entry<String,EmploymentStatistics> e : iterable){
			EmploymentStatistics stats = e.getValue();
			double ratio = ((double)stats.getEmployed()/((double)stats.getEmployed()+stats.getUnemployed()));
			ratios.add(new KeyValue<>(e.getKey(),ratio));
		}
		ratios.sort((KeyValue<Double> kv1, KeyValue<Double> kv2) -> {
			if(kv1.getValue()-kv2.getValue()>0)
				return -1;
			else if(kv1.getValue()-kv2.getValue()<0)
				return 1;
			return 0;
		});
		return ratios;
	}
}
