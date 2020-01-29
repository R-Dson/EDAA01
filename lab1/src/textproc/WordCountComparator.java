package textproc;

import java.util.*;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>>{

	@Override
	public int compare(Map.Entry<String, Integer> word1,Map.Entry<String, Integer> word2) {
		int x = word2.getValue().compareTo(word1.getValue());
		if(x == 0) {
			x = word1.getKey().compareTo(word2.getKey());
		}
		return x;
	}

}
