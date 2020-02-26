package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import com.sun.glass.events.KeyEvent;

import javafx.scene.control.SelectionMode;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 400, 300));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// deklarerar pane och en ny instans av klassen SortedListModel som använder sig
		// utav
		// GeneralWordCounters ordlista. Deklarerar sedan en JList som använder sig utav
		// får SortedListModel.
		Container pane = frame.getContentPane();
		SortedListModel<Map.Entry<String, Integer>> model = new SortedListModel(counter.getWordList());
		JList<Map.Entry<String, Integer>> jlist = new JList<>(model);

		frame.setPreferredSize(new Dimension(width, height));
		// Lägger till ovanstående i en ny JScrollPane
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollpane = new JScrollPane(jlist);
		pane.add(scrollpane);

		// Skapar en panel och två knappar, lägger till knapparna i panelen, lägger till
		// panelen
		// längst ner i fönstret
		JPanel jpanel = new JPanel();
		JRadioButton alpha = new JRadioButton("Alphabetic");
		JRadioButton freq = new JRadioButton("Frequency");
		jpanel.add(alpha);
		jpanel.add(freq);

		// anger hur man sorterar i storleksordnings
		freq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.sort((word1, word2) -> word2.getValue() - word1.getValue());
				alpha.setSelected(false);
			}

		});
		freq.addActionListener(e -> {
			model.sort((word1, word2) -> word2.getValue() - word1.getValue());
			alpha.setSelected(false);
		});

		// också sortering alfabetsisktsktk
		alpha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg1) {
				model.sort((word1, word2) -> word1.getKey().compareTo(word2.getKey()));
				freq.setSelected(false);
			}
		});

		JTextField jtext = new JTextField();
		jtext.setPreferredSize(new Dimension(100, 25));
		JButton search = new JButton("Search");
		jpanel.add(jtext);
		jpanel.add(search);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean message = false;
				String searchWord = jtext.getText().toLowerCase().replaceAll("\\s+", "");

				for (int i = 0; i < model.getSize(); i++) {
					Map.Entry<String, Integer> temp = model.getElementAt(i);
					if (temp.getKey().equals(searchWord)) {
						jlist.setSelectedIndex(i);
						jlist.ensureIndexIsVisible(i);
						message = true;
						break;
					}
				}
				if (message == false) {
					JOptionPane.showMessageDialog(jpanel, "Ordet finnes icke i listan.......");
				}
			}

		});

		pane.add(jpanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}
}
