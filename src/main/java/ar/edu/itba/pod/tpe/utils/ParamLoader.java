package ar.edu.itba.pod.tpe.utils;

import ar.edu.itba.pod.tpe.combiner.*;
import ar.edu.itba.pod.tpe.mappers.*;
import ar.edu.itba.pod.tpe.reducers.*;
import ar.edu.itba.pod.tpe.submitters.*;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.mapreduce.*;
import com.hazelcast.security.UsernamePasswordCredentials;

public class ParamLoader {
		private String address, data, outPath, timeOutPath;
		private int query;
	public ParamLoader(){
		address = System.getProperty("address");
		query = Integer.parseInt(System.getProperty("query"));
		data = System.getProperty("inPath");
		outPath = System.getProperty("outPath");
		timeOutPath = System.getProperty("timeOutPath");
	}

	public ClientConfig getConfig(){
		ClientConfig config = new ClientConfig();
		String[] addresses = address.split(";");
		for(String addr : addresses) {
			config.getNetworkConfig().addAddress(addr);
			config.getNetworkConfig().setConnectionAttemptLimit(5);
		}
		config.getGroupConfig().setName("55165-55302-55206-53774").setPassword("pass");
		return config;
	}

	public Mapper getMapper(){
		switch(query){
			case 1:
				return new Ej1Mapper();
			case 2:
				String prov = System.getProperty("prov");
				if(prov != null)
					return new Ej2Mapper(System.getProperty("prov"));
				else
					throw new IllegalArgumentException("prov argument missing");
			case 3:
				return new Ej3Mapper();
			case 4:
				return new Ej4Mapper();
			case 5:
				return new Ej5Mapper();
			case 6:
				return new Ej6Mapper();
			case 7:
				return new Ej7Mapper();
			default:
				throw new IllegalArgumentException("Invalid query number");

		}
	}

	public ReducerFactory getReducer(){
		switch(query){
			case 1:
				return new Ej1ReducerFactory();
			case 2:
				return new Ej2ReducerFactory();
			case 3:
				return new Ej3ReducerFactory();
			case 4:
				return new Ej4ReducerFactory();
			case 5:
				return new Ej5ReducerFactory();
			case 6:
				return new Ej6ReducerFactory();
			case 7:
				return new Ej7ReducerFactory();
			default:
				throw new IllegalArgumentException("Invalid query number");

		}
	}

	public Collator getCollator(){
		int n = 0;
		if(query == 2 || query == 6 || query == 7)
			n = Integer.parseInt(System.getProperty("n"));

		switch(query){
			case 1:
				return new DescendantSortedCollator();
			case 2:
				return new TopNFromDescendantSortedCollator(n);
			case 3:
				return new EmploymentRatioCollator();
			case 4:
				return new DescendantSortedCollator();
			case 5:
				return new DescendantSortedCollator();
			case 6:
				return new DescendantSortedWithMinimumCollator<>(n);
			case 7:
				return new ProvincePairCounterCollator(n);
			default:
				throw new IllegalArgumentException("Invalid query number");

		}
	}

	public boolean hasCombiner(){
		 return query == 1 || query == 2;
	}

	public CombinerFactory getCombiner(){
		switch (query){
			case 1:
				return new Ej1CombinerFactory();
			case 2:
				return new Ej2CombinerFactory();
			case 3:
				return new Ej3CombinerFactory();
            case 4:
                return new Ej4CombinerFactory();
            case 6:
                return new Ej6CombinerFactory();
			default:
				throw new IllegalArgumentException("This query has no combiner");
		}
	}

	public String getDataPath() {
		return data;
	}

	public String getLogFile() {
		return timeOutPath;
	}

	public String getOutPath() {
		return outPath;
	}
}
