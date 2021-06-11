  
package inteligenca;


import java.util.List;
import java.util.Random;
import java.util.Set;

import logika.Igra;

import splosno.Koordinati;

public class RandomMinimax extends Inteligenca {
	
	private static final Random RANDOM = new Random();
	
	private static final int ZMAGA = Integer.MAX_VALUE; // vrednost zmage, veË kot vsaka druga ocena pozicije
	private static final int REMI = 0;  // vrednost neodloƒçene igre	

	private int globina;
	
	public RandomMinimax (int globina) {
		super("nakljuËni minimax globina " + globina);
		this.globina = globina;
	}
	
	@Override
	public Koordinati izberiPotezo (Igra igra) {
		List<OcenjenaPoteza> ocenjenePoteze = najboljsePoteze(igra, globina);
		System.out.println(ocenjenePoteze.size() + " potez z vrednostjo " + ocenjenePoteze.get(0).ocena);
		int i = RANDOM.nextInt(ocenjenePoteze.size());	
		return ocenjenePoteze.get(i).poteza;		
	}
	
	static void s(String a) {
		System.out.print(a);
	}
	static void sln(String a) {
		System.out.println(a);
	}
	static void i(int a) {
		System.out.print(a);
	}
	static void iln(int a) {
		System.out.println(a);
	}
	
	// vrne seznam vseh potez, ki imajo najveƒçjo vrednost z vidike trenutnega igralca na potezi
	public static List<OcenjenaPoteza> najboljsePoteze(Igra igra, int globina) {
		char jaz = igra.naPotezi();
		NajboljseOcenjenePoteze najboljsePoteze = new NajboljseOcenjenePoteze();
		Set<Koordinati> moznePoteze = igra.moznePoteze;
		for (Koordinati p: moznePoteze) {
			System.out.println(p);
			Igra kopijaIgre = new Igra(igra); 
			kopijaIgre.odigrajPotezo (p);	//poskusimo vsako potezo v novi kopiji igre
			char zmaga = kopijaIgre.preveriZmago();
			int ocena;
			if (zmaga == jaz) {
				s("zmaga");
				ocena = ZMAGA;
				break;}
			else if (zmaga == OceniPozicijo.nasprotnik(jaz)) {
				ocena = -ZMAGA;
				s("poraz");
				break;}
			else if (moznePoteze.size() == 0) {
				s("remi");
				ocena = REMI;
				break;}
			else {
				s("ena");
				if (globina==1) ocena = OceniPozicijo.oceniPozicijo(kopijaIgre,igra.naPotezi());
				else ocena = -najboljsePoteze(kopijaIgre,globina-1).get(0).ocena; // - ker je drug igralec 
			}
			najboljsePoteze.addIfBest(new OcenjenaPoteza(p, ocena));			
		}
		return najboljsePoteze.list();
	}

	
}