package pbt.exercise4;

import java.util.*;

/**
 * Example was borrowed from
 * https://github.com/owickstrom/hedgehog-inline-java-testing/blob/master/src/main/java/example/CircularBuffer.java
 */
public class CircularBuffer_BugFixed<T> {
	private final T[] buf;
	private int in;
	private int out;

	public CircularBuffer_BugFixed(int capacity) {
		this.buf = (T[]) new Object[capacity + 1];
	}

	public synchronized void put(T x) {
		buf[in] = x;
		this.in = (this.in + 1) % this.buf.length;
	}

	public synchronized T get() {
		T x = buf[out];
		this.out = (this.out + 1) % this.buf.length;
		return x;
	}

	public synchronized int size() {
		int diff = this.in - this.out + this.buf.length;
		return diff == 0 ? 0 : diff % this.buf.length;
	}

	@Override
	public String toString() {
		return String.format("CircularBuffer(%s)", Arrays.toString(buf));
	}
}

