package ar.edu.itba.pod.tpe.combiner;

import ar.edu.itba.pod.tpe.utils.EmploymentStatistics;
import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

/**
 * Created by nlopez on 11/5/17.
 */
public class Ej3CombinerFactory implements CombinerFactory<String, EmploymentStatistics, EmploymentStatistics> {

    @Override
    public Combiner<EmploymentStatistics, EmploymentStatistics> newCombiner(String s) {
        return new Ej3Combiner();
    }

    private class Ej3Combiner extends Combiner<EmploymentStatistics,EmploymentStatistics> {
        EmploymentStatistics employmentStatistics;

        public Ej3Combiner() {
            this.employmentStatistics = new EmploymentStatistics();
        }

        @Override
        public void combine(EmploymentStatistics employmentStatistics) {
            this.employmentStatistics.addEmployed(employmentStatistics.getEmployed());
            this.employmentStatistics.addUnemployed(employmentStatistics.getUnemployed());
        }

        @Override
        public EmploymentStatistics finalizeChunk() {
            return employmentStatistics;
        }

        @Override
        public void reset() {
            this.employmentStatistics = new EmploymentStatistics();
        }
    }
}
