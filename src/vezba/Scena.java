package vezba;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Scena extends Canvas implements Runnable{

	private boolean aktivna=false;
	private boolean radi=false;
	private Thread nit;
	private Usisivac u;
	private SkupFigura figure;
	
	public Scena(){
		this.setBackground(Color.gray);
		figure=new SkupFigura();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_SPACE==e.getKeyCode()) {
					if(radi()) {pauziraj();}
					else {nastavi();}
				}
				else if(KeyEvent.VK_ESCAPE==e.getKeyCode()) {
					 ((java.awt.Frame) getParent()).dispose();                                    
				}
			}
		});
		
		setFocusable(true);
		requestFocusInWindow();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(u==null) {
			u=new Usisivac(this.getWidth()/2, this.getHeight()/2);
			try {
				figure.dodajFiguru(u);
			} catch (GFiguraVecPostoji e) {
			}
		}
		for(Figura f: figure.toList()) {
			f.nacrtaj(g);
		}
	}
	
	public synchronized void pokreni() {
		if(!aktivna) {
			aktivna=true;
			radi=true;
			nit = new Thread(this);  
			nit.start();
		}
	}
	
	public synchronized void pauziraj() {
		if(radi) radi=false;
	}
	public synchronized void nastavi() {
		radi=true;
		notify();
	}
	public synchronized void zaustavi() {
		radi=false;
		aktivna=false;
		if(nit!=null) {
			nit.interrupt();
		}
	}
	
	public synchronized boolean radi() {
		return radi;
	}
	
	@Override
	public void run() {
		while(aktivna) {
			synchronized(this) {
				while(!radi) {
					try {
						wait();
					}catch(InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}
			}
			
			if(Thread.currentThread().isInterrupted()) {
				return;
			}
			
			pomeriUsisivac();
			repaint();
			
			try {
				Thread.sleep(50);
			}
			catch(InterruptedException e){
				return;
			}
			
		}
		
	}

	private void pomeriUsisivac() {
		if (figure.brojFiguraUSkupu() <= 1) {  
		    pauziraj();                      
		    return;                          
		}                                    
		                                     
		
			//nalazimo figuru koja je najbliza od svih
			int min_rastojanje=Integer.MAX_VALUE;
			Figura najbliza = null;
			
			for(Figura f: figure.toList()) {
				if(f==u) continue;
				if(u.odrediRastojanje(f)<min_rastojanje) {
					min_rastojanje=u.odrediRastojanje(f);
					najbliza=f;
				}
			}
			
			//imamo najblizu figuru, sada pomeramo usisivac horizontalno 
			//sve dok je pomeraj <= od horizontalnog rastojanja
			
			if(najbliza != null) {
				if(u.getPomeraj()< Math.abs(najbliza.getX()-u.getX())) {
					int rastojanje=najbliza.getX()-u.getX();
					if (rastojanje>0) {//znaci da treba da je najbliza figura na desnoj strani, 
						//znaci dodajemo pomeraj
						u.setX((int) (u.getX()+u.getPomeraj()));}
					else {
						u.setX((int) (u.getX()-u.getPomeraj()));
					}
				}
				else if(u.getPomeraj()< Math.abs(najbliza.getY()-u.getY())) {
					int rastojanje=najbliza.getY()-u.getY();
					if(rastojanje>0) {
						//znaci da je figura IZNAD nas pa treba da dodajemo pomeraj
						u.setY((int) (u.getY()+u.getPomeraj()));

					}
					else {
						u.setY((int) (u.getY()-u.getPomeraj()));
					}
				}
				else {
					figure.izbaciFiguru(najbliza);
				}

			}
		
		
	}

	public void dodajFiguru(Figura f) {
		try {
			figure.dodajFiguru(f);
		} catch (GFiguraVecPostoji e) {
			e.printStackTrace();
		}
		if(figure.brojFiguraUSkupu()>1 && !radi) {
			nastavi();
		}
		repaint();
	}

}
