package vezba;

import java.awt.Color;
import java.awt.Graphics;

public class Usisivac extends Figura {

	private double pomeraj; //broj piksela za koji usisivac moze da se pomeri u jednom koraku
	
	public Usisivac(int x1, int y1) {
		x=x1;
		y=y1;
		r=15;
		pomeraj=r/2;
	}

	public double getPomeraj() {
		return pomeraj;
	}
	@Override
	public void nacrtaj(Graphics g) {
		//crtamo jednakostranicni trougao 
		//trougao je poligon od 3 tacke
		//treba da nadjemo te tri tacke trougla
		
		 int xA = x - (int) (r * Math.sqrt(3) / 2);
		 int yA = y + r / 2;
		    
		 int xB = x + (int) (r * Math.sqrt(3) / 2);
		 int yB = y + r / 2;
		    
		 int xC = x;
		 int yC = y - (int) (r * Math.sqrt(3) / 2);
		 
		 int[] xtacke= {xA, xB, xC};
		 int[] ytacke= {yA, yB, yC};
		 
		 g.setColor(Color.red);
		 g.fillPolygon(xtacke, ytacke, 3);
		
	}
}
