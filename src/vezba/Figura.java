package vezba;

import java.awt.Graphics;

public abstract class Figura {

	protected int x,y; //centar kruga opisanog oko figure
	protected int r;
	
//	public Figura(int x, int y) {
//		super();
//		this.x = x;
//		this.y = y;
//	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int x1) {x=x1;}
	public void setY(int y1) {y=y1;}
	
	public int dohvatiPoluprecnik() {return r;};
	public int odrediRastojanje(Figura f) {
		return (int) Math.sqrt(Math.pow(this.x-f.getX(),2)+Math.pow(this.y-f.getY(), 2));
	}
	public boolean preklapaSe(Figura f) {
		//preklapaju se ako je rastojanje izmedju centara < r1+r2
		if(odrediRastojanje(f)<f.dohvatiPoluprecnik()+r) return true;
		return false;
	}

	public boolean sadrziFiguru(Figura f) {
		if(f.dohvatiPoluprecnik()<r && odrediRastojanje(f)+f.dohvatiPoluprecnik()<r) return true;
		return false;
	}
	
	public abstract void nacrtaj(Graphics g);
	
}
