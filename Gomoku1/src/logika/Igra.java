package logika;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import graficni.Frame;
import graficni.Panel;
import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
import inteligenca.Minimax;
import inteligenca.RandomMinimax;
import splosno.Koordinati;

public class Igra {
	
	/*
	public static char[][] board;
	public static int dim;
	public static Set<Koordinati> moznePoteze;
	public final static char PRAZNO = '.';
	public static Igra igra;
	public static char zmagovalec;
	*/
	
	public  char[][] board;
	public  int dim;
	//public List<Koordinati> moznePoteze;
	public Set<Koordinati> moznePoteze;
	public static final  char PRAZNO = '.';
	public static  Igra igra;
	public char zmagovalec;
	public int poteza ;
	protected Panel panel;
	
	public static Koordinati zacetek;
	public static Koordinati konec;
	public static Koordinati konec_stolpec, konec_diagonala;
	public static Koordinati zacetek_stolpec, zacetek_diagonala;
	
	
	
	 public boolean racunalnikRacunalnik = false;

	public char[] igralci = {'X', 'O'};
	
	public static final int ZMAGA_ST = 5;
	
	public Igra(int dim) {
		
		this.poteza = 0;
		this.dim = dim;
		zmagovalec = PRAZNO;
		board = new char[dim][dim];
		/*moznePoteze = new LinkedList<Koordinati>();*/
		moznePoteze = new HashSet<Koordinati>();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				board[i][j] = PRAZNO;
				Koordinati k = new Koordinati(i,j);
				moznePoteze.add(k);
			}
		}
	}
	
	public Igra(Igra igra) {
		
		this.poteza = igra.poteza;
		this.dim = igra.dim;
		this.zmagovalec = PRAZNO;
		this.board = new char[dim][dim];
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				
				this.board[i][j] = igra.board[i][j] ;
			}
		}

		moznePoteze = new HashSet<Koordinati>();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				board[i][j] = PRAZNO;
				Koordinati k = new Koordinati(i,j);
				moznePoteze.add(k);
			}
		}

	}

	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				
				sb.append(this.board[i][j]);
			}
			sb.append("\n");
		}
		
		return sb.toString();

	}
	
	public void nastaviIgralce(char[] igralci) {
		
		this.igralci = igralci;
	}
	
	public Igra() {
		this(15);
		
	}
	
	public char naPotezi() {
		
		return igralci[this.poteza % 2];
	}
	
	
	public  void zacni () {
		igra = new Igra ();
		panel = Frame.panel;
	}
	
	public  boolean odigrajPotezo(Koordinati k) {
		if ( this.moznePoteze.contains(k)) {
			
			//this.moznePoteze.remove(k);
			this.moznePoteze.remove(k);
			 
			this.board[k.getX()][k.getY()] = naPotezi() ;
			
			this.poteza++;
			
			return true;
		}
		return false;
	}
	
	public  char preveriZmago() {

		java.util.List<Character> polja = Arrays.asList(ZmagaVrstica(), ZmagaStolpec(), ZmagaDiagonala()); 
		for (Character i : polja) {
			if (!i.equals(Igra.PRAZNO)) {
				igra.zmagovalec = i;
				if (i == 'X') System.out.println("Zmagovalec je PRVI igralec");
				else if (i == 'O') System.out.println("Zmagovalec je DRUGI igralec");
				else System.out.println("NEODLOÈENO");

				return igra.zmagovalec;
			}
		}
		
		
		if(moznePoteze.size() == 0) {
			
			return  'N';
		}
		else {

			return  Igra.PRAZNO;			
		}
		
	}
	
	public static Inteligenca racunalnikovaInteligenca = new Minimax(1);
	//public static Inteligenca racunalnikovaInteligenca = new RandomMinimax(1);
	/*public static void racunalnikPoteza(Igra igra) {
		Igra zacetkaIgra = igra;
		SwingWorker<Koordinati, Void> worker = new SwingWorker<Koordinati, Void> () {
			@Override
			protected Koordinati doInBackground() {
				Koordinati poteza = racunalnikovaInteligenca.izberiPotezo(igra);
				try {TimeUnit.MILLISECONDS.sleep(200);} catch (Exception e) {};
				return poteza;
			}
			@Override
			protected void done () {
				Koordinati poteza = null;
				try {poteza = get();} catch (Exception e) {};
				if (igra == zacetkaIgra) {
					igra.odigrajPotezo(poteza);
				}
			}
		};
		worker.execute();
	}*/
	
	public static  Koordinati racunalnikPoteza(Igra igra) {
		Koordinati poteza = racunalnikovaInteligenca.izberiPotezo(igra);
		/*Set<Koordinati> moznePoteze = igra.moznePoteze;
		int size = moznePoteze.size();
		int j = new Random().nextInt(size);
		int i = 0;
		Koordinati poteza = null; //na nekaj nastavimo
		for(Koordinati k : moznePoteze)
		{
			if (i == j) {
				poteza = k;
				igra.odigrajPotezo(poteza);
				return poteza;

				
			}
				
			i++;
		}
		igra.odigrajPotezo(poteza);
	*/	
		return poteza;

	}
	
	// igra racunalnik proti racunalniku
	public void racunalnikIgra(Igra igra, Panel panel) {
		if (igra.preveriZmago() == Igra.PRAZNO) {
		racunalnikPoteza(igra);
		panel.repaint();
		}
		
	}
	
	
	
	public char ZmagaStolpec() {
	
		char zmagovalec = Igra.PRAZNO;
		
		for (int i = 0; i < dim; i++) {
		
			int zaporednih = 0;
			char trenutni = Igra.PRAZNO;
			
			
			for (int j = 0; j < dim; j++) {
				
				if(this.board[i][j] == Igra.PRAZNO) {
		
					zaporednih = 0;
					trenutni = Igra.PRAZNO;
				}
				else if(this.board[i][j] == trenutni) {
					
					zaporednih++;
				}
				else {
					
					trenutni = this.board[i][j];
					zacetek_stolpec = new Koordinati(i, j);
					zaporednih = 1;
					
				}
				
				
				if(zaporednih == ZMAGA_ST) {
					konec_stolpec = new Koordinati(i, j);
					zmagovalec = trenutni;
					System.out.println(zacetek + "    " + konec);
					return zmagovalec;
				}
			}
		}		
		return  zmagovalec;
	}
	public char ZmagaVrstica() {
	
		
		char zmagovalec = Igra.PRAZNO;
		
		for (int j = 0; j < dim; j++) {

		
			int zaporednih = 0;
			char trenutni = Igra.PRAZNO;
			for (int i = 0; i < dim; i++) {

				
				if(this.board[i][j] == Igra.PRAZNO) {
		
					zaporednih = 0;
					trenutni = Igra.PRAZNO;
				}
				else if(this.board[i][j] == trenutni) {
					
					zaporednih++;
				}
				else {
					
					trenutni = this.board[i][j];
					zacetek = new Koordinati(i, j);
					zaporednih = 1;
				}
				
				
				if(zaporednih == ZMAGA_ST) {
					
					konec = new Koordinati(i, j);
					zmagovalec = trenutni;
					return zmagovalec;
				}
			}
		}		
		return  zmagovalec;
	}
	public char ZmagaDiagonala() {
		
		char zmagovalec = Igra.PRAZNO;

		int limit = dim - ZMAGA_ST + 1;
		int start = ZMAGA_ST - 1;
		
		for(int i = 0; i < limit; i++) {
			
			for(int j = 0; j < dim; j++) {
				
			
				if(this.board[i][j] != Igra.PRAZNO) {
					zacetek_diagonala = new Koordinati(i, j);

					if(j >= start ) {
						
						char trenutni = this.board[i][j];
						boolean ujemanje = true;
						
						for(int k = 1; k < ZMAGA_ST; k++) {
							
							if(this.board[i+k][j-k] != trenutni) {
								
								ujemanje = false;
							}
						}
						
						if(ujemanje) {
							
							zmagovalec = trenutni;
							
							konec_diagonala = new Koordinati(i + 4, j - 4);
							System.out.println(zacetek_diagonala + "zacetek   " + konec_diagonala + " KONEC");
							return zmagovalec;
						}
						
					}
					if(j  < limit ) {
						
						char trenutni = this.board[i][j];
						boolean ujemanje = true;

						for(int k = 1; k < ZMAGA_ST; k++) {
							
							if(this.board[i+k][j+k] != trenutni) {
								
								ujemanje = false;
							}
						}
						
						if(ujemanje) {
							
							zmagovalec = trenutni;
							konec_diagonala = new Koordinati(i +4 , j + 4);
							System.out.println(zacetek_diagonala + "zacetek   " + konec_diagonala + " KONEC" + "TUKAAAAJ");
							return zmagovalec;
						}
					}
					
				}
			}
		}

		return  zmagovalec;
	}
	
	public List<Koordinati> poteze() {
		LinkedList<Koordinati> ps = new LinkedList<Koordinati>();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (board[i][j] == PRAZNO) {
					ps.add(new Koordinati(i, j));
				}
			}
		}
		return ps;
	}
	
	public boolean odigrajPotezo(){
		
		
		return true;
	}
	

}
