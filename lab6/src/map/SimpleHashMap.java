package map;

import java.util.HashSet;

public class SimpleHashMap<K, V> implements Map<K, V> {

	Entry<K, V>[] entries;
	double LoadFactor;
	int size;

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
		SimpleHashMap<String, Integer> mapString = new SimpleHashMap<>();
		for (int i = 0; i < 3; i++) {
			map.put(16 * i, 16 * i);
		}

		java.util.Random random = new java.util.Random(123456);
		HashSet<Integer> randNbrs = new HashSet<Integer>();
		
		for (int i = 0; i < 10; i++) { int r = random.nextInt(10000); map.put(r, r);
		randNbrs.add(r); }
		 
		map.remove(0);

		System.out.println(map.show());
		System.out.println(map.size());
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private V value;
		private K key;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.value = value;
			this.key = key;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}

		@Override
		public int hashCode() {
			return key.hashCode();
		}

	}

	public SimpleHashMap() {
		entries = (Entry<K, V>[]) new Entry[16];
		LoadFactor = 0.75 * 16;
		size = 0;
	}

	public SimpleHashMap(int capacity) {
		entries = (Entry<K, V>[]) new Entry[capacity];
		LoadFactor = 0.75 * capacity;
		size = 0;
	}

	@Override
	public V get(Object arg0) {
		try {
			return find(index((K) arg0), ((K) arg0)).getValue();
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	private void rehash() {
		Entry<K, V>[] tempor = entries;
		this.entries = (Entry<K, V>[]) new Entry[entries.length * 2];
		this.LoadFactor = LoadFactor * 2;
		for (Entry<K, V> temp : tempor) {
			if (temp != null) {
				this.put(temp.getKey(), temp.getValue());
				while (temp.next != null) {
					this.put(temp.getKey(), temp.getValue());
					temp = temp.next;
				}
				this.put(temp.getKey(), temp.getValue());
			}
		}

	}

	@Override
	public V put(K arg0, V arg1) {
		Entry<K, V> newEntry = new Entry<>(arg0, arg1);
		V oldValue = null;
		int index = index(arg0);

		if (entries[index] == null) {
			entries[index] = newEntry;
			size++;
			if (size() > this.LoadFactor) {
				size = 0;
				rehash();
			}
		} else {
			oldValue = putRec(entries[index], newEntry, index);
		}

		return oldValue;
	}

	private V putRec(Entry<K, V> entry, Entry<K, V> newEntry, int index) {
		V oldValue = null;
		if (entry.getKey().equals(newEntry.getKey())) {
			oldValue = ((V) entry.getValue());
			entry.setValue(newEntry.getValue());
		} else if (entry.next == null) {
			entry.next = newEntry;
			size++;
			if (size() > this.LoadFactor) {
				size = 0;
				rehash();
			}

		} else {
			oldValue = putRec(entry.next, newEntry, index);
		}
		return oldValue;
	}

	@Override
	public V remove(Object arg0) {
		int index = index((K) arg0);
		Entry<K, V> n = entries[index];
		V v;
		if (n == null) { // listan är null
			return null;
		} else if (n.getKey().equals(arg0)) { // key finns i det första elementet i listan
			v = n.getValue();

			entries[index] = entries[index].next;
			size--;
			return v;
		} else if (n.next != null) { // key finns senare i listan

			while (n.next != null) {
				if (n.next.getKey().equals(arg0)) {
					v = n.next.getValue();
					n.next = n.next.next;
					size--;
					return v;
				}
				n = n.next;
			}

		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	public String show() {
		StringBuilder s = new StringBuilder();
		Entry<K, V> n;
		for (int i = 0; i < entries.length; i++) {
			s.append(i + "     ");
			n = entries[i];
			while (n != null) {
				s.append(n.toString());
				if (n.next != null) {
					s.append(", ");
				}
				n = n.next;
			}
			s.append("\n");
		}
		return s.toString();
	}


	private int index(K key) {
		int hashCode = key.hashCode() % entries.length;
		if (hashCode < 0) {
			hashCode = hashCode + entries.length;
		}
		return hashCode;
	}

	private Entry<K, V> find(int index, K key) {
		if (entries[index] == null) {
			return null;
		} else {
			return findRec(entries[index], key);
		}
	}

	private Entry<K, V> findRec(Entry<K, V> entry, K key) {
		Entry<K, V> temp = null;
		if (entry.getKey().equals(key)) {
			return entry;
		}
		if (entry.next != null) {
			temp = findRec(entry.next, key);
		}
		return temp;

	}

}
