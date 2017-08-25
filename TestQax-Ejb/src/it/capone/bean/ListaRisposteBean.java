package it.capone.bean;

import java.util.ArrayList;
import java.util.List;

import it.capone.utility.Data;

/**
 * @author Enrico
 *	SEMPLICI METODI PER IL <<CRUD>> DELLA LISTA DELLE RISPOSTE	
 */
public class ListaRisposteBean {

private List<RispostaBean> listaRisposte;
	
	//Costruisce una lista di risposte inizialmente vuota
	public ListaRisposteBean() {
		this.listaRisposte  = new ArrayList<RispostaBean>();
	}
	
	
	public void creaRisposta(int idrisposta, String descrizione, LoginBean utente, Data datacreazione, DomandaBean domanda) {
		RispostaBean risposta = new RispostaBean(idrisposta, descrizione, utente, datacreazione, domanda);
		this.listaRisposte.add(risposta);
	}
	
	
	public boolean rimuoviRisposta(RispostaBean risposta) {
		boolean rimosso = false;
		if(risposta.getIdrisposta() > 0 && risposta.getIdrisposta() < this.listaRisposte.size()){
			this.listaRisposte.remove(risposta);
			rimosso=true;
		}
		
		return rimosso;
	}
	
	
	public List<RispostaBean> getListaRisposte() {
		return this.listaRisposte;
	}
	
	
	public RispostaBean getRisposta(int pos) {
		return this.listaRisposte.get(pos); 
	}
	
	
	
	public boolean updateDescrizioneRisposta(RispostaBean risposta, String newDescrizione) {
		boolean update=false;
		if(risposta.getIdrisposta() > 0 && risposta.getIdrisposta() < this.listaRisposte.size()) {
			risposta.setDescrizione(newDescrizione);
			update=true;
		}
		return update;
	}
	
	
	public boolean pulisciTutto() {
		boolean pulito=false;
		
		if(this.listaRisposte.isEmpty())
			return pulito;
		else{
			this.listaRisposte.clear();
			pulito=true;
		}
		
		return pulito;
	}
	
	
	public boolean isEmpty() {
		boolean vuoto=false;
		if(this.listaRisposte.isEmpty())
			vuoto=true;
		
		return vuoto;
	}
}
