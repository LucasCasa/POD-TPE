package ar.edu.itba.pod.tpe.reducers;

import ar.edu.itba.pod.tpe.utils.EmploymentStatistics;
import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class Ej3ReducerFactory implements ReducerFactory<String,Boolean,EmploymentStatistics> {

	@Override
	public Reducer<Boolean, EmploymentStatistics> newReducer(String s) {
		return new Ej3_Reducer();
	}

	private class Ej3_Reducer extends Reducer<Boolean, EmploymentStatistics> {

		EmploymentStatistics stats = new EmploymentStatistics();

		@Override
		public void reduce(Boolean employed) {
			if(employed)
				stats.addEmployed();
			else
				stats.addUnemployed();
		}

		@Override
		public EmploymentStatistics finalizeReduce() {
			return stats;
		}
	}
}
