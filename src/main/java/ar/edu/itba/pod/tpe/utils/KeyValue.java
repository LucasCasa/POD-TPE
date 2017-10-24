package ar.edu.itba.pod.tpe.utils;

import java.util.Comparator;

public class KeyValue implements Comparable<KeyValue>{
	private int value;
	private String key;

	public KeyValue(String k, int v){
		key = k;
		value = v;
	}

	@Override
	public int compareTo(KeyValue o) {
		return o.value - value;
	}

	@Override
	public boolean equals(Object obj) {
		return key.equals(((KeyValue) obj).key);
	}
	@Override
	public int hashCode(){
		return key.hashCode();
	}

	@Override
	public String toString() {
		return key + ": " + value;
	}
}
