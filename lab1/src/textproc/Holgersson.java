package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };
	
	public static void main(String[] args) throws FileNotFoundException {
		List<TextProcessor> qwerty = new ArrayList<>(); 
		MultiWordCounter counter = new MultiWordCounter(REGIONS);
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor p2 = new SingleWordCounter("norge");
		
		qwerty.add(p);
		qwerty.add(p2);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			counter.process(word);
			
			for(TextProcessor j : qwerty) {
				j.process(word);
			}
		}

		s.close();
		
		counter.report();
		p.report();
		p2.report();
	}
}