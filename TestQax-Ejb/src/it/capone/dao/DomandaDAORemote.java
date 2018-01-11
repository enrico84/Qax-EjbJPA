package it.capone.dao;

import java.util.List;

import javax.ejb.Remote;

import it.capone.bean.CategoriaBean;
import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.LoginBean;
import it.capone.entity.Categoria;
import it.capone.entity.Domanda;
import it.capone.entity.Risposta;
import it.capone.entity.Utente;

@Remote
public interface DomandaDAORemote {
	
    List<Domanda> getDomande();
	
	
    List<Domanda> getDomande(String categoria);
	
	
	Domanda getDomanda(int id);
	
	
	List<Domanda> getMieDomande(String username, String password);
	
	
	Domanda creaDomanda(String titolo, String descrizione, String categoria, LoginBean utente);
	
	
	boolean aggiornaDomanda(int iddomanda, String titolo, String descrizione, String categoria);
	
	
	boolean eliminaDomanda(int idDomanda);
	
	Utente prendiUtente(int id);	
	
	Categoria prendiCategoria(int id);
	
	List<Risposta> getRisposteDomanda(int id);
	
	void closeLogicaJPA();

}
