package graficni;

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

public class Panel extends JPanel implements MouseListener {
	
	protected Igra igra;
	  int width, height;

	  int rows;

	  int cols;
	
	
	public Panel(int sirina, int visina, int rows, int cols) {
		super(); 
		setPreferredSize(new Dimension(sirina, visina));
		GridsCanvas(sirina, visina, rows, cols);
		
		
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
		    for (i = 0; i < cols; i++)
		      g.drawLine(i * rowWid, 0, i * rowWid, height);
		  }


	@Override
	public void mouseClicked(MouseEvent e) {
		if //je na vrsti clovek) {
			int x = e.getX();
			int y = e.getY();
			double velikost = velikostKvadratka();
			int kvadrat_x = x /(int) velikost;
			int kvadrat_y = y /(int) velikost;
			double dovoljen_x = (x % (int) velikost) / velikost;
			double dovoljen_y = (y % (int) velikost) / velikost;
			if (0 < kvadrat_x && kvadrat_x < Igra.N && (0.5 * ROB) < dovoljen_x && dovoljen_x < (1 - (0.5 * ROB))
					&& 0 < kvadrat_y && kvadrat_y < Igra.N && (0.5 * ROB) < dovoljen_y && dovoljen_y < (1 - (0.5 * ROB))) {
				Vodja.igrajClovekovoPotezo(new Koordinati(kvadrat_x, kvadrat_y));
			}
		}
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
	
	
	
