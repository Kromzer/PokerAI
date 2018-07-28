package fr.kromzer.pokerai.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CombinationIterator<T> implements Iterable<List<T>>, Iterator<List<T>> {
	private final List<T> items;
	private final int choose;
	private boolean finished;
	private int[] current;

	public CombinationIterator(List<T> items, int choose) {
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("items");
		}
		if (choose <= 0 || choose > items.size()) {
			throw new IllegalArgumentException("choose");
		}
		this.items = items;
		this.choose = choose;
		this.finished = false;
	}

	@Override
	public Iterator<List<T>> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return !this.finished;
	}

	@Override
	public List<T> next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		if (this.current == null) {
			this.current = new int[this.choose];
			for (int i = 0; i < this.choose; i++) {
				this.current[i] = i;
			}
		}

		final List<T> result = new ArrayList<>(this.choose);
		for (int i = 0; i < this.choose; i++) {
			result.add(this.items.get(this.current[i]));
		}

		final int n = this.items.size();
		this.finished = true;
		for (int i = this.choose - 1; i >= 0; i--) {
			if (this.current[i] < n - this.choose + i) {
				this.current[i]++;
				for (int j = i + 1; j < this.choose; j++) {
					this.current[j] = this.current[i] - i + j;
				}
				this.finished = false;
				break;
			}
		}

		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}