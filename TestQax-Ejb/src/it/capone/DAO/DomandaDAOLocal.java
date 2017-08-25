package it.capone.DAO;

import java.util.List;

import javax.ejb.Local;

import it.capone.bean.CategoriaBean;
import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.LoginBean;
import it.capone.entity.Domanda;

@Local
public interface DomandaDAOLocal {
	
    List<Domanda> getDomande();
	
	
    List<Domanda> getDomande(String categoria);
	
	
	Domanda getDomandaConRisposte(int id);
	
	
	Domanda getDomanda(int id);
	
	
	ListaDomandeBean getMieDomande(String username, String password, ListaDomandeBean myListaDomande);
	
	
	void creaDomanda(String titolo, String descrizione, String categoria, LoginBean utente);
	
	void creaDomanda(DomandaBean domanda, String titolo, String descrizione, String categoria, LoginBean utente);
	
	
	void aggiornaDomanda(CategoriaBean categoriaBean, int iddomanda, String titolo, String descrizione, 
			                       String categoria);
	
	
	void eliminaDomanda(int idDomanda);
	
	
	LoginBean prendiUtente(int id);	
	
	
	DomandaBean prendiDomanda(int iddomanda);

}
