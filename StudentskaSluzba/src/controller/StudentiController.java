package controller;

import java.util.ArrayList;
import java.util.List;

import model.BazaStudent;
import model.Predmet;
import model.Student;
import view.Tabovi;

/**
 * Klasa koja rukuje reakcijama na dogadjaje entiteta/modela: Student.
 * 
 * @author Vaxi i Pufke
 *
 */

public class StudentiController {
	public static StudentiController getInstance() {
		if (instance == null) {
			instance = new StudentiController();
		}
		return instance;
	}

	private static StudentiController instance = null;
	//List<Student> listaStudenataZaSerijalizaciju = SerijalizacijaStudenta.getInstance().deserijalizacijaObjekta();// Lista
																													// studenata
																													// za
																													// serijalizaciju
	List<Student> listaStudenataZaSerijalizaciju = BazaStudent.getInstance().getStudenti();
	
	private StudentiController() {

	}

	// Metoda koja vraca studenta za prosledjeni index reda
	public Student getListaStudenata(int i) {
		return BazaStudent.getInstance().getStudenti().get(i);
	}

	// Metoda koja vraca sve studente
	public List<Student> getListaSvihStudenata() {
		return BazaStudent.getInstance().getStudenti();
	}

	// Metoda koja vraca studenta za prosledjen index, u suprotnom vraca null
	public Student getStudentByIndex(String index) {
		List<Student> listaSvihStudenata = getListaSvihStudenata();

		for (Student student : listaSvihStudenata) {
			if (student.getBrIndexa().equals(index)) {
				return student;
			}
		}
		return null;
	}

	/**
	 * Metoda koja za prosledjenog studenta brise sa predmet, tj u listi predmeta
	 * 
	 * @param entitet studenta i premdet
	 * 
	 */
	public void izbrisiPredmetProsledjenomStudentu(Student student, Predmet predmet) {
		BazaStudent.getInstance().izbrisiPredmetProsledjenomStudentu(student, predmet);
	}

	/**
	 * Metoda koja za prosledjenog studenta dodaje predmet, tj u listi predmeta
	 * 
	 * @param entitet studenta i premdet
	 * 
	 */
	public void dodajPredmetStudentu(Student student, Predmet predmet) {
		
			for(Student studentZaSerijalizaciju: listaStudenataZaSerijalizaciju) {
				if(studentZaSerijalizaciju.getBrIndexa().equals(student.getBrIndexa())) {
					BazaStudent.getInstance().addStudentNaPredmet(student, predmet);
					return;
				}
			}
	}

	public List<Student> getStudenti() {
		return BazaStudent.getInstance().getStudenti();
	}

	/**
	 * Metoda koja za prosledjenog studenta dodaje studenta
	 * 
	 * @param entitet studenta
	 */
	public void dodajStudenta(Student student) {
		BazaStudent.getInstance().dodajStudentaString(student.getIme(), student.getPrezime(),
				student.getDatumRodjenja(), student.getAdresaStanovanja(), student.getTelefon(), student.getEmail(),
				student.getBrIndexa(), student.getDatumUpisa(), student.getGodinaStudija(), student.getProsecnaOcena(),
				student.getStatusStudenta(), student.getPredmeti());
		// Dodajemo studenta u listu za serijalizaciju
		//listaStudenataZaSerijalizaciju.add(student);
	}

	public List<Student> getListaStudenataZaSerijalizaciju() {
		return listaStudenataZaSerijalizaciju;
	}

	public void setListaStudenataZaSerijalizaciju(List<Student> listaStudenataZaSerijalizaciju) {
		this.listaStudenataZaSerijalizaciju = listaStudenataZaSerijalizaciju;
	}

	/*
	 * *Metoda koja u zavisnosti u kom smo tabu a kliknemo na izbrisi, izbrise
	 * odredjeni red
	 */

	public void izbrisiStudenta(int rowIndex) {
		int indikatorBreak=-1;
		// proverimo da li ima predmet, ako imamo, nadjemo taj predmet, i tom predmetu
		// skinemo ovog studenta
		Student student = new Student();
		student = BazaStudent.getInstance().getStudenti().get(rowIndex);

		List<Predmet> listaPredmetaStudenta = new ArrayList<Predmet>();
		listaPredmetaStudenta = student.getPredmeti();

		// prolazim kroz sve predmete u tabu Predmeti
		for (Predmet p : PredmetiController.getInstance().getListaSvihPredmeta()) {
			// prolazim kroz svaki predmetStudenta i proveravam da li na nekom predmetu
			// postoji predmetStudenta,ako postoji,brisem tog studenta iz liste studenta tog
			// predmeta
			for (Predmet pStudenta : listaPredmetaStudenta) {
				indikatorBreak=-1;
				if(p.getSifraPredmeta().equals(pStudenta.getSifraPredmeta())) {
					for(Student s : p.getListaStudenata()) {
						if(s.getBrIndexa().equals(student.getBrIndexa())) {
							p.getListaStudenata().remove(s);
							//BazaStudent.getInstance().getStudenti().remove(rowIndex);
							indikatorBreak=1;
							break;
						}
					}
					if(indikatorBreak==1) {
						break;
					}
					
				}
			}
			//System.out.println("spoljasnji for");
		}

		BazaStudent.getInstance().getStudenti().remove(rowIndex);
		Tabovi.getModelStudenti().fireTableDataChanged();

	}

	
	public void izbrisiStudentaAliNeIPredmete(int rowIndex) {

		BazaStudent.getInstance().getStudenti().remove(rowIndex);
		Tabovi.getModelStudenti().fireTableDataChanged();

	}

}
