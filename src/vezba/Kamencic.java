package vezba;

import java.awt.Color;
import java.awt.Graphics;

public class Kamencic extends Figura {

	public Kamencic(int _x, int _y) {
		this.x=_x;
		this.y=_y;
		this.r=5;
	}

	@Override
	public void nacrtaj(Graphics g) {
		//kako ce crta krug?
		//xk i yk su koordinate gornjeg levog ugla kvadrata u koji crtamo krug
		int xk=x-r;
		int yk=y-r;
		g.setColor(Color.BLACK);
		g.fillOval(xk, yk, r*2, r*2);
		
	}
	
	
}
