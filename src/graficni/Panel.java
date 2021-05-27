package graficni;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Stanje;
import logika.Vrsta;
import logika.VrstaIgralca;
import splosno.Koordinati;
import vodja.Vodja;

public class Panel extends JPanel implements MouseListener {
	
	protected static Igra igra;
	public static VrstaIgralca vrsta;
	public static Koordinati clovekPoteza;
	
	
	
	protected int dim = 15;
	public static Igralec igralec;
	  int width, height;

	  int rows;

	  int cols;
	
	  public int x, y;
	  
	boolean mustDraw = false;
	
	public Panel(int sirina, int visina, int rows, int cols) {
		super(); 
		setPreferredSize(new Dimension(sirina, visina));
		GridsCanvas(sirina, visina, rows, cols);
		
		addMouseListener(this);
		
		
	}
	
	public static void setIgra() {
		igra = Vodja.igra;
		igralec = igra.igralec;
		vrsta = igra.vrsta;
		clovekPoteza = Vodja.clovekPoteza;
	}
	
	
	
	
	 public void GridsCanvas(int w, int h, int r, int c) {
		    setSize(width = w, height = h);
		    rows = r;
		    cols = c;
		    
		  }

	
	  public void paint(Graphics g) {
		    int i;
		    width = getSize().width;
		    height = getSize().height;
		    
		    // narisi vrtice
		    int rowHt = height / (rows);
		    for (i = 0; i < rows; i++)
		      g.drawLine(0, i * rowHt, width, i * rowHt);

		    // narisi stolpce
		    int rowWid = width / (cols);
		    for (i = 0; i < cols; i++) {
		      g.drawLine(i * rowWid, 0, i * rowWid, height);
		    }
		    
		    
		    Graphics2D g2 = (Graphics2D) g;
		    for (int k = 0; k < dim; k++) {
		    	for (int l = 0; l < dim; l++) {
		    		if (igra.board[k][l] == Polje.X) {
		    			 g2.setColor(Color.red);
		    			 g2.fillOval(CentralizirajX(x, 15, 500), CentralizirajY(y, 15, 500), 10, 10);
		    			 igra.igralec = igralec.O;
		    			 igra.vrsta = VrstaIgralca.R;

		    		}
		    		else if (igra.board[k][l] == Polje.O) {
		    			g2.setColor(Color.blue);
		    			g2.fillOval(pretvoriRacunalnik(k, 15, 500), pretvoriRacunalnik(l, 15, 500), 10, 10);
		    			igra.igralec = igralec.X;
		    			igra.vrsta = VrstaIgralca.C;
		    		}
		    	}
		    }
		    
		    
/*
		    
		    //narisi "O"
		    if(!mustDraw) return;
		    Graphics2D g2 = (Graphics2D) g;
		    g.setColor(Color.red);
		    g.fillOval(CentralizirajX(x, 15, 500), CentralizirajY(y, 15, 500), 10, 10);
		    mustDraw = false;
		    */

		  }
	  //postavi zeton na sredino kvadratka
	public int CentralizirajX(int x, int rows, int h) {
		for (int i = 0; i < h; i = i + (h / rows)){
			if (x < i) {
				x = (int)Math.round((i + (i - (h / rows) )) / 2); //x premaknemo v sredino

				return x;
		}
			
	}
		return 0;
		}
	
	public int CentralizirajY(int y, int cols, int w) {
		for (int i = 0; i < w; i = i + (int)Math.round(w / cols)){
			if (y < i) {
				y = (int)Math.round((i + (i - (w / cols) )) / 2); //x premaknemo v sredino
				return y;

		}
	}
		return 0;
		}
	
	//pretvori koordinate v koordinate od 0 do dim
	
	public int pretvoriKoordinatoX(int x, int rows, int h) {
		int j = -2;
		for (int i = 0; i < h; i = i + (h / rows)){
			j = j + 1;
			if (x < i) {
				return j;
				
		}
		
	}
		return 0;
		
}
	
	
	public int pretvoriKoordinatoY(int y, int cols, int w) {
		int j = -2;
		for (int i = 0; i < w; i = i + (w / cols)){
			j = j + 1;
			if (y < i) {
				return j;
				
		}
		
	}
		return 0;
		
}
	
	public int pretvoriRacunalnik(int x, int cols, int w) {
		for (int i = 0; i < cols; i++) {
			if (x < i) {
				x = ((2*i - 1) * (w / cols)) / 2;
				return x;
			}
		}
		return 0;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();	
		clovekPoteza = (new Koordinati((pretvoriKoordinatoX(x, 15, 500)), pretvoriKoordinatoY(y, 15, 500)));
		//Vodja.clovekPoteza(new Koordinati((pretvoriKoordinatoX(x, 15, 500)), pretvoriKoordinatoY(y, 15, 500)));
		//mustDraw = true;
		Vodja.igra();
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		}
	
	