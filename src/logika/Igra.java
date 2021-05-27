package logika;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import splosno.Koordinati;

public class Igra {
	public Polje[][] board;
	protected int dim;
	public Set<Koordinati> moznePoteze;
	public Igralec igralec;
	public Stanje stanje;
	public VrstaIgralca vrsta;
	protected final static int M = 5; // velikost zmagovalnega niza
	protected final static char PRAZNO = '.';
	
	
	public Igra(int dim) {
		this.dim = dim;
		board = new Polje[dim][dim];
		moznePoteze = new HashSet<Koordinati>();
		stanje = Stanje.V_TEKU;
		vrsta = VrstaIgralca.C;
		igralec = igralec.X; //na primer da X zacne
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				board[i][j] = Polje.PRAZNO;
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
			board[k.getX()][k.getY()] = igralec.getPolje();
			return true;
		}
		return false;
	}
	
	public Polje preveriZmago(Koordinati k) {
		java.util.List<Polje> polja = Arrays.asList(ZmagaVrstica(k), ZmagaStolpec(k), ZmagaDiagonala(k)); 
		for (Polje i : polja) {
			if (!i.equals(Polje.PRAZNO)) {
				if (igralec.getPolje() == Polje.X) {
					stanje = Stanje.ZMAGA_X;
				}
				else {
					stanje = Stanje.ZMAGA_O;
				}
				return igralec.getPolje();
			}
			if (moznePoteze.size()==0) {
				stanje = Stanje.NEODLOCENO;
			}
		}
		return Polje.PRAZNO;
	}
	
	public Object Neodloceno() {
		// TODO Auto-generated method stub
		return null;
	}

	public Stanje stanje() {
		return this.stanje;
	}

	public VrstaIgralca vrsta() {
		return this.vrsta;
	}

	
	public Polje ZmagaVrstica(Koordinati k) {
		int i = 0;
		for (Polje c : board[k.getX()]) {
			if (c == igralec.getPolje()) {
				i++;
				if (i == M) {
					return igralec.getPolje();
				}
			}
			else {
				i = 0;
			}
		}
		return Polje.PRAZNO;
	}
	
	public Igra(Igra igra) {
		this.board = new Polje[dim][dim];
		for (int i = 0; i < dim; i++) {
		for (int j = 0; j < dim; j++) {
		this.board[i][j] = igra.board[i][j];
		}
		}
		this.igralec = igra.igralec;
		}
	
	public Polje ZmagaStolpec(Koordinati k) {
		int j = 0;
		for (int i = 0; i< dim; i++) {
			if (board[i][k.getY()] == igralec.getPolje()) {
				j++;
				if (j == M) {
					return igralec.getPolje();
				}
			}
			else {
				i = 0;
			}
		}
		return Polje.PRAZNO;
	}
	public Polje ZmagaDiagonala(Koordinati k) {
		int j = 0;
		int zacetek = Math.min(k.getX(),k.getY());
		int konec = dim - Math.max(k.getX(),k.getY()) + 1;
		for (int i = zacetek; i< konec; i++) {
			if (board[k.getX() - i][k.getY() - i] == igralec.getPolje()) {
				j++;
				if (j == M) {
					return igralec.getPolje();
				}
			}
			else {
				i = 0;
			}
		}
		return Polje.PRAZNO;
	}
	
	// odigraj
}