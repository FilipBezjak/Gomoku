package graficni;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logika.Igralec;
import logika.VrstaIgralca;
import vodja.Vodja;


@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener {
	
	
	protected Panel panel;
	private JMenuItem menuVelikost;
	private JMenuItem menuIme;
	private JMenuItem menuCR, menuClovek, menuRC, menuCC, menuRR;
	private JMenuItem menuBarva;
	private JMenuItem menuCas;
	private JButton izberiBarvo;
	private JLabel aktivnaBarvaLabel;
	private Color barva;
	
	public Frame() {
		
		super();
		setTitle("Gomoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new Panel(500, 500, 15, 15); //panel = new Panel(800, 800, panel.cols, panel.rows)
		add(panel);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuIgra = dodajMenu(menubar, "Lastnosti igre");
		JMenu menuIgralec = dodajMenu(menubar, "Lastnosti igralca");
		JMenu menuNastavitve = dodajMenu(menubar, "Lastnosti grafičnega vmesnika");
		
		menuVelikost = dodajMenuItem(menuIgra,"Velikost");
		menuIme = dodajMenuItem(menuIgralec,"Ime");
		menuCR = dodajMenuItem(menuIgralec,"1. človek, 2. računalnik");
		menuRC = dodajMenuItem(menuIgralec,"1. računalnik, 2. človek");
		menuCC = dodajMenuItem(menuIgralec,"človek - človek");
		menuRR = dodajMenuItem(menuIgralec,"računalnik - računalnik");
		menuBarva = dodajMenuItem(menuNastavitve,"Barva kovanca");
		menuCas = dodajMenuItem(menuNastavitve,"Čas poteze");
		
		
		Color aktivnaBarva = Color.BLACK;
		aktivnaBarvaLabel = new JLabel(" ");
		aktivnaBarvaLabel.setOpaque(true);
		aktivnaBarvaLabel.setBackground(aktivnaBarva);
		setBarva(aktivnaBarva);
		
		
		

		

	}
	
	public void setBarva(Color barva) {
		this.barva = barva;
		}
	
	public Color getBarva(Color barva) {
		this.barva = barva;
		return this.barva;
		}

	
	public JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}

	public JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem = new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == menuVelikost) {
			String stolpci = JOptionPane.showInputDialog(this, "Stolpci: ");
			String vrstice = JOptionPane.showInputDialog(this, "Vrstice: ");
			panel.setSize(Integer.parseInt(stolpci), Integer.parseInt(vrstice));
		}
		if (s == menuBarva) {
			Color novaBarva = JColorChooser.showDialog(this,
					"Izberite barvo", getBarva(barva));
					if (novaBarva != null) {
					setBarva(novaBarva);
					aktivnaBarvaLabel.setBackground(novaBarva);
			
		}
		// nastavimo ali igramo clovek vs racunalnik ipd... kopirano s predavanj
		if (s.equals("menuCR")) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			//Vodja.Vodja.vrstaIgralca.put(Igralec.X, VrstaIgralca.C);
			Vodja.vrstaIgralca.put(Igralec.O, VrstaIgralca.R);			
	}
		if (s.equals("menuCC")) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.X, VrstaIgralca.C);
			Vodja.vrstaIgralca.put(Igralec.O, VrstaIgralca.C);			
	}		
		if (s.equals("menuRR")) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.X, VrstaIgralca.R);
			Vodja.vrstaIgralca.put(Igralec.O, VrstaIgralca.C);			
}
		if (s.equals("menuRC")) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.X, VrstaIgralca.R);
			Vodja.vrstaIgralca.put(Igralec.O, VrstaIgralca.C);			
	}//v vodji nastavi igralca in vrsto
		
		
	
	
}}}