package vodja;
import logika.VrstaIgralca;

import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.util.List;

import javax.swing.SwingWorker;

import Inteligenca.Inteligenca;
import Inteligenca.Minimax;

import java.util.concurrent.TimeUnit;

import graficni.Frame;
import graficni.Panel;
import logika.Igra;
import logika.Stanje;
import logika.VrstaIgralca;
import logika.Igralec;
import splosno.Koordinati;

public class Vodja {
	
	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
	public static Panel panel;
	public static Igra igra;
	public static boolean clovekNaVrsti = false;
	private static Random random = new Random ();
	public static Koordinati clovekPoteza;
	
	
		public static void zacni () {
		igra = new Igra ();
		igra ();
	}
// vedno bo zacel X, nato v graficnem nastavimo, ali je x clovek ali racunalnik
	public static void igra () {
		switch (igra.stanje()) {
			case NEODLOCENO:
				System.out.println("Neodloceno");
				break;
			case ZMAGA_X: 
				System.out.println("Zmagal je igralec X");
				break;
			case ZMAGA_O: 
				System.out.println("Zmagal je igralec O");
				break; 
		case V_TEKU: 
			Igralec igralec = igra.igralec;
			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
			Koordinati poteza = null;
			switch (vrstaNaPotezi) {
			case C: 
				clovekPoteza(clovekPoteza);
				break;
			case R:
				racunalnikPoteza();
				break;
			}
		}
	}
	

	public static void racunalnikPoteza() {
		Set<Koordinati> moznePoteze = igra.moznePoteze;
		int size = moznePoteze.size();
		int j = new Random().nextInt(size);
		int i = 0;
		Koordinati poteza = null; //na nekaj nastavimo
		for(Koordinati k : moznePoteze)
		{
			if (i == j)
				poteza = k;
			i++;
		}
		igra.odigrajPotezo(poteza);

	}
	
	public static Inteligenca pametnaPoteza = new Minimax(5);
	
	//public static void igrajPametno()
		
	public static void clovekPoteza(Koordinati poteza) {
		Set<Koordinati> moznePoteze = igra.moznePoteze;
		if (moznePoteze.contains(poteza)) {
			igra.odigrajPotezo(poteza);
		}

	}
	
}