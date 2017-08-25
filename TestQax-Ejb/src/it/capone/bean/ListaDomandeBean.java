package it.capone.bean;

import java.util.ArrayList;
import java.util.List;

import it.capone.utility.Data;

/**
 * @author Enrico
 *	SEMPLICI METODI PER IL CRUD DELLA LISTA DI DOMANDE	
 */
public class ListaDomandeBean {
	
	private List<DomandaBean> listaDomande;
	
	//Costruisce una lista di domande inizialmente vuota
	public ListaDomandeBean() {
		this.listaDomande = new ArrayList<DomandaBean>();
	}
	
	
	public void creaDomanda(int id, String titolo, String descrizione, Data datacreazione, CategoriaBean categoria, LoginBean utente) {
		DomandaBean domanda = new DomandaBean(id, titolo, descrizione, datacreazione, categoria, utente);
		this.listaDomande.add(domanda);
	}
	
	
	public boolean rimuoviDomanda(DomandaBean domanda) {
		boolean rimosso = false;
		if(domanda.getIddomanda() > 0 && domanda.getIddomanda() < this.listaDomande.size()){
			this.listaDomande.remove(domanda);
			rimosso=true;
		}
		
		return rimosso;
	}
	
	
	public List<DomandaBean> getListaDomande() {
		return this.listaDomande;
	}
	
	
	public DomandaBean getDomanda(int pos) {
		return this.listaDomande.get(pos); 
	}
	
	
	public boolean updateTitoloDomanda(DomandaBean domanda, String newTitolo) {
		boolean update=false;
		if(domanda.getIddomanda() > 0 && domanda.getIddomanda() < this.listaDomande.size()) {
			domanda.setTitolo(newTitolo);
			update=true;
		}
		return update;
	}
	
	
	public boolean updateDescrizioneDomanda(DomandaBean domanda, String newDescrizione) {
		boolean update=false;
		if(domanda.getIddomanda() > 0 && domanda.getIddomanda() < this.listaDomande.size()) {
			domanda.setDescrizione(newDescrizione);
			update=true;
		}
		return update;
	}
	
	
	public boolean pulisciTutto() {
		boolean pulito=false;
		
		if(this.listaDomande.isEmpty())
			return pulito;
		else{
			this.listaDomande.clear();
			pulito=true;
		}
		
		return pulito;
	}
	
	
	public int getDimensione(){
		int i=0;
		for(DomandaBean d : this.listaDomande) {
			i++;
		}
		return i;
	}

}
