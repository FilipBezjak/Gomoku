package graficni;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener {
	
	
	protected Panel panel;
	private JMenuItem menuVelikost;
	private JMenuItem menuIme;
	private JMenuItem menuClovek;
	private JMenuItem menuBarva;
	private JMenuItem menuCas;
	
	
	public Frame() {
		super();
		setTitle("Gomoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new Panel(800, 800, panel.cols, panel.rows);
		add(panel);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuIgra = dodajMenu(menubar, "Lastnosti igre");
		JMenu menuIgralec = dodajMenu(menubar, "Lastnosti igralca");
		JMenu menuNastavitve = dodajMenu(menubar, "Lastnosti grafiènega vmesnika");
		
		menuVelikost = dodajMenuItem(menuIgra,"Velikost");
		menuIme = dodajMenuItem(menuIgralec,"Ime");
		menuClovek = dodajMenuItem(menuIgralec,"Èlovek ali raèunalnik");
		menuBarva = dodajMenuItem(menuNastavitve,"Barva kovanca");
		menuCas = dodajMenuItem(menuNastavitve,"Èas poteze");
		
		
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
		Object source = e.getSource();
		if (source == menuVelikost) {
			String stolpci = JOptionPane.showInputDialog(this, "Stolpci: ");
			String vrstice = JOptionPane.showInputDialog(this, "Vrstice: ");
			panel.setSize(Integer.parseInt(stolpci), Integer.parseInt(vrstice));
		}
	
	
	}
}