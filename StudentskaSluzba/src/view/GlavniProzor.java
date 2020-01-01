package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Klasa koja nam sluzi kao podloga za nas GUI.
 * 
 * @author Vaxi i Pufke
 *
 */
public class GlavniProzor extends JFrame {

	private static final long serialVersionUID = -4307414094787209222L;
	private static GlavniProzor instance = null;

	public static GlavniProzor getInstance() {
		if (instance == null) {
			instance = new GlavniProzor();
		}
		return instance;
	}

	public GlavniProzor() {
		setTitle("Studentska slu\u017Eba");

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dimenzijeEkrana = kit.getScreenSize(); // uzimamo dimenzije ekrana kako bi namestili velicinu
															// aplikacije

		setSize((dimenzijeEkrana.width * 3) / 4, (dimenzijeEkrana.height * 3) / 4); // podesavanje na 3/4 ekrana
		setLocationRelativeTo(null); // u odnosu na centar ekrana postavljamo
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // gasenje aplikacije na x

		MojToolbar toolbar = new MojToolbar();
		add(toolbar, BorderLayout.NORTH);
		validate(); // osvezavanje dodanog na frejm

		// Postavljena ikonice aplikacije
		Image img = kit.getImage("img/ikonica.png");
		setIconImage(img);

		// kreiranje naslednika klase JMenuBar i njeno postavljanje u glavni prozor
		// aplikacije
		MojMenyBar meny = new MojMenyBar();
		this.setJMenuBar(meny);
		validate(); // osvezavanje dodanog na frejm

		// Podesavanje status bara na donji deo aplikacije.

		add(MojStatusBar.getInstance().getStatusPanel(), BorderLayout.SOUTH);
		MojStatusBar.getInstance().prikaziStatusBar(MojStatusBar.getInstance().getStatusPanel());
		setVisible(true);

		add((Tabovi.getInstance()).getScrollPane(), BorderLayout.CENTER);// prikaz tabova
		validate();

	}

}