package map;

public class SimpleHashMap<K, V> implements Map<K, V> {

	Entry<K, V>[] entries;
	double LoadFactor;
	int size;

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
		entries = (Entry<K, V>[]) new Entry[entries.length * 2];
		this.LoadFactor = LoadFactor * 2;
		for (Entry<K, V> temp : tempor) {
			if (temp == null) {
			} else {
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
		if (entry.getKey().equals( newEntry.getKey())) {
/*		if ( ((Comparable<K>) entry.getKey() ).compareTo( newEntry.getKey()) == 0) {*/
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	
	/*public int size() {
		int size = 0;
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] != null) {
				size = size + sizeRec(entries[i]);
			}
		}
		return size;
	}*/

	public String show() {
		String string = "";
		for (int i = 0; i < entries.length; i++) {
			string = string + recShow(entries[i]);
			string = string + "\n";
		}
		return string;
	}

	private String recShow(Entry<K, V> entry) {
		if (entry.next == null) {
			return entry.toString() + "  ";
		} else {
			return recShow(entry.next);
		}
	}

	/*private int sizeRec(Entry<K, V> entry) {
		if (entry.next == null) {
			return 1;
		} else {
			return sizeRec(entry.next);
		}
	}*/

	private int index(K key) {
		int hashCode = key.hashCode() % entries.length;
		if (key.hashCode() == Integer.MIN_VALUE) {
			hashCode = (-entries.length - ((Integer) Integer.MIN_VALUE)) % entries.length;
		}
		if (hashCode < 0) {
			hashCode = -key.hashCode() % entries.length;
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
		if (entry.getKey() == key) {
			return entry;
		}
		if (entry.next != null) {
			temp = findRec(entry.next, key);
		}
		return temp;

	}

}
