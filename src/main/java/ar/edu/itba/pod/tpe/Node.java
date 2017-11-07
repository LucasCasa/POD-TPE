package ar.edu.itba.pod.tpe;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;

public class Node {

	public static void main(String[] args) throws IOException {
		HazelcastInstance hi = Hazelcast.newHazelcastInstance();
		System.in.read();
		System.exit(0);
	}
}
