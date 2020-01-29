package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{

	Map<String, Integer> m = new TreeMap<String, Integer>();
	

	public MultiWordCounter(String[] words) {
		for(String w : words) {
			m.put(w, 0);
		}
	}
	
	
	@Override
	public void process(String word) {
		// TODO Auto-generated method stub
		for(String s : m.keySet()) {
			if(s.equals(word)) {
				int i = m.get(s).intValue() + 1;
				m.put(s, i);
			}
		}
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		for(String s : m.keySet()) {
			int i = m.get(s).intValue();
			System.out.println(s + ": " + i);
		}
	}
	
}
