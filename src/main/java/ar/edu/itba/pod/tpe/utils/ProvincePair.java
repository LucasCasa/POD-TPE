package ar.edu.itba.pod.tpe.utils;

import java.io.Serializable;

public class ProvincePair implements Serializable, Comparable<ProvincePair> {
	private long serialVersionUID = 1L;

	private String lower;
	private String higher;

	public ProvincePair(String p1, String p2){
		lower = p1.compareToIgnoreCase(p2)<0?p1:p2;
		higher = p1.compareToIgnoreCase(p2)>=0?p1:p2;
		if(p1.equals(p2))
			throw new RuntimeException("Provinces can't be the same in ProvincePair");
	}

	public int hashCode(){
		return lower.hashCode()+higher.hashCode();
	}

	public boolean equals(Object other){
		if(other.getClass() != this.getClass())
			return false;
		ProvincePair o = (ProvincePair)other;
		return(this.lower.equals(o.lower) && this.higher.equals(o.higher));
	}

	public String toString(){
		return lower + " + " + higher;
	}

	@Override
	public int compareTo(ProvincePair o) {
		int c1 = this.lower.compareTo(o.lower);
		if(c1!=0)
			return c1;
		return this.higher.compareTo(o.higher);
	}
}
