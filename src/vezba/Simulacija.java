package vezba;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Simulacija extends Frame{

	private Scena scena;
	
	public Simulacija() {
		scena=new Scena();
		setBounds(200, 200, 400, 300);
		add(scena);
		setVisible(true);
		this.setResizable(false);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		scena.pokreni();

		scena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scena.dodajFiguru(new Kamencic(e.getX(), e.getY()));
				scena.repaint();
			}
		});
		
		//scena.requestFocusInWindow();
	}
	

	public static void main(String[] args) {
		new Simulacija();
	}
}
