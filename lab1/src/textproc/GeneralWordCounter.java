package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;

public class GeneralWordCounter implements TextProcessor {
	
	Set<String> stopwords = new HashSet<String>();
	Map<String, Integer> m = new HashMap<String, Integer>();

	public GeneralWordCounter(Set<String> set) {
		stopwords = set;
		
	}
	
	@Override
	public void process(String word) {
		if(stopwords.contains(word)) 
			return;
		if(m.containsKey(word) == false) {
			m.put(word, 0);
		}
		m.put(word, m.get(word).intValue() + 1);
		
		/*for(String s : m.keySet()) {
			if(s.equals(word)) {
				int i = m.get(s).intValue() + 1;
				m.put(s, i);
			}
		}*/
	}

	@Override
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i = 0; i < 25; i++) {
			System.out.println(wordList.get(i));
		}
		/*for(String k : m.keySet()) {
			if(m.get(k).intValue() > 199){
				System.out.println(k + ": " + m.get(k).intValue());
			}
				
		}*/
	}

	
}
