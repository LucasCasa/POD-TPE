package ar.edu.itba.pod.tpe;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Node {

	public static void main(String[] args) {
		HazelcastInstance hi = Hazelcast.newHazelcastInstance();
	}
}
