package inteligenca;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import logika.Igra;

import splosno.Koordinati;

import inteligenca.OceniPozicijo;

public class Alphabeta extends Inteligenca {
	
	private static final int ZMAGA = Integer.MAX_VALUE; // vrednost zmage
	private static final int PORAZ = -ZMAGA;  // vrednost izgube
	private static final int REMI = 0;  // vrednost neodloËene igre	
	
	private int globina;
	
	public Alphabeta (int globina) {
		super("alphabeta globina " + globina);
		this.globina = globina;
	}
	
	@Override
	public Koordinati izberiPotezo (Igra igra) {
		// Na zaƒçetku alpha = PORAZ in beta = ZMAGA
		return alphabetaPoteze(igra, this.globina, PORAZ, ZMAGA, igra.naPotezi()).poteza;
	}
	
	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, char jaz) {
		System.out.println("hej");
		int ocena;
		if (igra.naPotezi() == jaz) {ocena = PORAZ;} else {ocena = ZMAGA;}
		List<Koordinati> moznePoteze = igra.moznePoteze;
		Koordinati kandidat = moznePoteze.get(0);
		for (Koordinati p: moznePoteze) {
			Igra kopijaIgre = new Igra(igra);
			System.out.print(p);
			char zmaga = kopijaIgre.preveriZmago();
			int ocenap;
			if (zmaga == jaz) {
				ocena = ZMAGA;}
			else if (zmaga == OceniPozicijo.nasprotnik(jaz)) {
				ocena = PORAZ;}
			else if (moznePoteze.size() == 0) {
				ocena = REMI;}
				// nekdo je na potezi
				if (globina == 1) ocenap = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				else ocenap = alphabetaPoteze (kopijaIgre, globina-1, alpha, beta, jaz).ocena;
			if (igra.naPotezi() == jaz) { // Maksimiramo oceno
				if (ocenap > ocena) { // mora biti > namesto >=
					ocena = ocenap;
					kandidat = p;
					alpha = Math.max(alpha,ocena);
				}
			} else { // igra.naPotezi() != jaz, torej minimiziramo oceno
				if (ocenap < ocena) { // mora biti < namesto <=
					ocena = ocenap;
					kandidat = p;
					beta = Math.min(beta, ocena);					
				}	
			}
			if (alpha >= beta) // Izstopimo iz "for loop", saj ostale poteze ne pomagajo
				return new OcenjenaPoteza (kandidat, ocena);
		}
		return new OcenjenaPoteza (kandidat, ocena);
	}

	public static Koordinati prvi(Set<Koordinati> mn) {
		Koordinati j = new Koordinati(0,0);
		for (Koordinati k : mn) {
			j=k;
			break;
		}
		return j;
		
	}
	
}