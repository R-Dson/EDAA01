package textproc;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.FileNotFoundException;

public class BookReaderApplication {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("nilsholg.txt"));
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		GeneralWordCounter r = new GeneralWordCounter(stopwords);
		
		
		
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r.process(word);
		}

		s.close();
		scan.close();
		BookReaderController cont = new BookReaderController(r);
	}
	
}
