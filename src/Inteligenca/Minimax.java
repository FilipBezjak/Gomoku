package Inteligenca;

import java.util.Set;

import logika.Igra;
import logika.Igralec;
import splosno.Koordinati;

public class Minimax extends Inteligenca{
	
	private static final int ZMAGA = Integer.MAX_VALUE;
	private static final int PORAZ = -ZMAGA; 
	private static final int REMI = 0; 

	private int globina;
	
	public Minimax (int globina) {
		super("minimax globina " + globina);
		this.globina = globina;
	}
	
	@Override
	public Koordinati izberiPotezo (Igra igra) {
		OcenjenaPoteza najboljsaPoteza = minimax(igra, this.globina, igra.naPotezi());
		return najboljsaPoteza.poteza;	
	}
	
	// vrne najboljso ocenjeno potezo z vidike igralca jaz
	public OcenjenaPoteza minimax(Igra igra, int globina, Igralec jaz) {
		OcenjenaPoteza najboljsaPoteza = null;
		Set<Koordinati> moznePoteze = igra.moznePoteze;
		for (Koordinati p: moznePoteze) {
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigrajPotezo (p);
			int ocena;
			// odigra v kopiji igre.
			switch (kopijaIgre.stanje()) {
			// ce je jaz igralec.O, vrne Zmaga, drugace poraz
			case ZMAGA_O: ocena = (jaz == Igralec.O ? ZMAGA : PORAZ); break;
			case ZMAGA_X: ocena = (jaz == Igralec.X ? ZMAGA : PORAZ); break;
			case NEODLOCENO: ocena = REMI; break;
			default:
				// nekdo je na potezi
				if (globina == 1) ocena = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				// globina > 1
				else ocena = minimax(kopijaIgre, globina-1, jaz).ocena;	
			}
			if (najboljsaPoteza == null 
					// max, če je p moja poteza
					|| jaz == igra.igralec && ocena > najboljsaPoteza.ocena
					// sicer min 
					|| jaz != igra.igralec && ocena < najboljsaPoteza.ocena)
				najboljsaPoteza = new OcenjenaPoteza (p, ocena);		
		}
		return najboljsaPoteza;
	}
}