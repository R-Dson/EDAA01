package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };
	
	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		//List<TextProcessor> qwerty = new ArrayList<>(); 
		//MultiWordCounter counter = new MultiWordCounter(REGIONS);
		
		//TextProcessor p = new SingleWordCounter("nils");
		//TextProcessor p2 = new SingleWordCounter("norge");
		
		//qwerty.add(p);
		//qwerty.add(p2);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		TextProcessor r = new GeneralWordCounter(stopwords);
		
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r.process(word);
			//counter.process(word);
			
			/*for(TextProcessor j : qwerty) {
				j.process(word);
			}*/
		}

		s.close();
		scan.close();
		r.report();
		//counter.report();
		//p.report();
		//p2.report();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1-t0)/1000000.0 + " ms");
	}
}