package vodja;

import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.util.List;

import javax.swing.SwingWorker;
import java.util.concurrent.TimeUnit;

import graficni.Frame;
import graficni.Panel;
import logika.Igra;
import logika.Stanje;
import logika.Igralec;
import splosno.Koordinati;

public class Vodja {
	
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;
	public static Panel panel;
	public static Igra igra = null;
	public static boolean clovekNaVrsti = false;
	private static Random random = new Random ();
	
	public static void zacni () {
		igra = new Igra ();
		igra ();
	}

	public static void igra () {
		switch (igra.stanje()) {
		case NEODLOCENO:
		case ZMAGA_X: 
		case ZMAGA_O: 
			return; 
		case V_TEKU: 
			switch (igra.vrsta) {
			case C: 
				clovekPoteza();
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
		Koordinati poteza;
		for(Koordinati k : moznePoteze)
		{
			if (i == j)
				poteza = k;
			i++;
		}
		igra.odigrajPotezo(poteza); 
	}
		
	public static void clovekPoteza(Koordinati poteza) {


}