package ar.edu.itba.pod.tpe.submitters;

import ar.edu.itba.pod.tpe.utils.EmploymentStatistics;
import ar.edu.itba.pod.tpe.utils.KeyValue;
import com.hazelcast.mapreduce.Collator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EmploymentRatioCollator implements Collator<Map.Entry<String,EmploymentStatistics>, Set<KeyValue<String,Float>>> {
	Set<KeyValue<String,Float>> ratios = new TreeSet<>();

	@Override
	public Set<KeyValue<String,Float>> collate(Iterable<Map.Entry<String, EmploymentStatistics>> iterable) {
		for(Map.Entry<String,EmploymentStatistics> e : iterable){
			EmploymentStatistics stats = e.getValue();
			double ratio = ((double)stats.getEmployed()/((double)stats.getEmployed()+stats.getUnemployed()));
			NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
			DecimalFormat df = (DecimalFormat)nf;
			df.applyPattern("#.##");
			ratios.add(new KeyValue<>(e.getKey(),Float.valueOf(df.format(ratio))));
		}
		return ratios;
	}
}
