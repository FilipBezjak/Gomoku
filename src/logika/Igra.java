package logika;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import splosno.Koordinati;

public class Igra {
	protected char[][] board;
	protected int dim;
	public Set<Koordinati> moznePoteze;
	public char igralec;
	public Stanje stanje;
	public vrstaIgralca vrsta;
	protected final static int M = 5; // velikost zmagovalnega niza
	protected final static char PRAZNO = '.';
	
	
	public Igra(int dim) {
		this.dim = dim;
		board = new char[dim][dim];
		moznePoteze = new HashSet<Koordinati> ();
		this.stanje = Stanje.V_TEKU;
		this.igralec = igralec;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				board[i][j] = PRAZNO;
				Koordinati k = new Koordinati(i,j);
				moznePoteze.add(k);
			}
		}
	}

	public Igra() {
		this(15);
	}
	
	
	public boolean odigrajPotezo(Koordinati k) {
		if (moznePoteze.contains(k)) {
			moznePoteze.remove(k);
			board[k.getX()][k.getY()] = igralec;
			return true;
		}
		return false;
	}
	
	public char preveriZmago(Koordinati k) {
		java.util.List<Character> polja = Arrays.asList(ZmagaVrstica(k), ZmagaStolpec(k), ZmagaDiagonala(k)); 
		for (Character i : polja) {
			if (!i.equals(PRAZNO)) {
				return igralec;
			}
		}
		return PRAZNO;
	}
	
	public Stanje stanje() {
		return this.stanje;
	}

	public vrstaIgralca vrsta() {
		return this.vrsta;
	}
	
	
	public char ZmagaVrstica(Koordinati k) {
		int i = 0;
		for (char c : board[k.getX()]) {
			if (c == igralec) {
				i++;
				if (i == M) {
					return igralec;
				}
			}
			else {
				i = 0;
			}
		}
		return PRAZNO;
	}
	
	public char ZmagaStolpec(Koordinati k) {
		int j = 0;
		for (int i = 0; i< dim; i++) {
			if (board[i][k.getY()] == igralec) {
				j++;
				if (j == M) {
					return igralec;
				}
			}
			else {
				i = 0;
			}
		}
		return PRAZNO;
	}
	public char ZmagaDiagonala(Koordinati k) {
		int j = 0;
		int zacetek = Math.min(k.getX(),k.getY());
		int konec = dim - Math.max(k.getX(),k.getY()) + 1;
		for (int i = zacetek; i< konec; i++) {
			if (board[k.getX() - i][k.getY() - i] == igralec) {
				j++;
				if (j == M) {
					return igralec;
				}
			}
			else {
				i = 0;
			}
		}
		return PRAZNO;
	}
	
	// odigraj
}

