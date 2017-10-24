package ar.edu.itba.pod.tpe.utils;

public enum Employment_Status {
	NO_DATA(0),
	EMPLOYED(1),
	UNEMPLOYED(2),
	INACTIVE(3);

	private final int value;

	Employment_Status(final int newValue) {
		value = newValue;
	}

	public int getValue() { return value; }
}
