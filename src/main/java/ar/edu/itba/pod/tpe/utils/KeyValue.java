package ar.edu.itba.pod.tpe.utils;

public class KeyValue<T extends Comparable<T>> implements Comparable<KeyValue<T>>{
	private T value;
	private String key;

	public KeyValue(String k, T v){
		key = k;
		value = v;
	}

	public T getValue(){
		return this.value;
	}

	public String getKey(){
		return this.key;
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

	@Override
	public int compareTo(KeyValue<T> o) {
		int res = o.value.compareTo(this.value);
		if(res == 0)
			res = this.key.compareTo(o.key);
		return res;
	}
}
