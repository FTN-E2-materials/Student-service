package main;

import model.BazaPredmeta;
import model.BazaProfesor;
import view.GlavniProzor;
import model.BazaStudent;



/**
 * Glavna klasa iz koje se vrsi inicijalno pokretanje aplikacije.
 * @author Pufke i Vaxi
 *
 */
public class MainApp {

	public static void main(String[] args) {
		System.out.println("Inicijalno ispisivanje");
		BazaProfesor.getInstance();
		BazaPredmeta.getInstance();

		GlavniProzor.getInstance();

		BazaStudent.getInstance();

	}

}
