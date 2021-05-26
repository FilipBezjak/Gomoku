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
import splosno.Koordinati;
import vodja.Vodja;

public class Panel extends JPanel implements MouseListener {
	
	protected Igra igra;
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
		      	System.out.println(i * rowWid + " " + 0 + " " + i * rowWid + " " + height);
		    }
		    
		    

		    
		    //narisi "O"
		    if(!mustDraw) return;
		    Graphics2D g2 = (Graphics2D) g;
		    g.setColor(Color.red);
		    g.fillOval(CentralizirajX(x, 15, 500), CentralizirajY(y, 15, 500), 10, 10);
		    //for neke v polje if polje[i][j]==x narises rdecega
		    //else modrega
		    mustDraw = false;
		    

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


	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();	
		Vodja.clovekPoteza(new Koordinati((pretvoriKoordinatoX(x, 15, 500)), pretvoriKoordinatoY(y, 15, 500)));
		mustDraw = true;
		System.out.println(x + " " + y);
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
	
	