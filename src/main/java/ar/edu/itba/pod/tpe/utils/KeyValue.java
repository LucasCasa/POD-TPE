package ar.edu.itba.pod.tpe.utils;

public class KeyValue<V extends Comparable<V>, T extends Comparable<T>> implements Comparable<KeyValue<V, T>>{
	private T value;
	private V key;

	public KeyValue(V k, T v){
		key = k;
		value = v;
	}

	public T getValue(){
		return this.value;
	}

	public V getKey(){
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
	public int compareTo(KeyValue<V, T> o) {
		int res = o.value.compareTo(this.value);
		if(res == 0)
			res = this.key.compareTo(o.key);
		return res;
	}
}
