package vezba;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkupFigura {
	
	private List<Figura> figure=new ArrayList<>();
	private Figura tekuca;
	private Iterator<Figura> iterator;
	
	public SkupFigura() {};
	
	public void dodajFiguru(Figura f) throws GFiguraVecPostoji {
		if(figure.contains(f)) throw new GFiguraVecPostoji();
		figure.add(f);
		if(figure.size()==1) {
			iterator=figure.iterator();
			tekuca=f;
		}
	}
	
	public Figura dohvatiTekucu() {
		return tekuca;
	}
	
	public void predjiNaSledecu() throws GNePostojiSledecaFigura {
		if(iterator==null || !iterator.hasNext()) throw new GNePostojiSledecaFigura();
		tekuca=iterator.next();
	}
	
	public boolean postojiSledeca() {
		return iterator.hasNext();
	}
	
	public void isprazniSkup() {
		figure.clear();
		tekuca=null;
		iterator=null;
	}
	
	public boolean nalaziSe(Figura f) {
		return figure.contains(f);
	}
	
	public void izbaciFiguru(Figura f) {
		figure.remove(f);
	}
	
	public int brojFiguraUSkupu() {
		return figure.size();
	}
	
	public List<Figura> toList(){
		return figure;
	}
}
