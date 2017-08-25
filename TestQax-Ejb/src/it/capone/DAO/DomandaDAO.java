package it.capone.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import it.capone.bean.CategoriaBean;
import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.LoginBean;
import it.capone.db.LogicaJPA;
import it.capone.entity.Domanda;
import it.capone.utility.Data;

/**
 * Session Bean implementation class DomandaDAO
 */
@Stateless
public class DomandaDAO implements DomandaDAORemote, DomandaDAOLocal {

//	@PersistenceContext(unitName="Qax-EJB")
//	protected EntityManager em;	
	
	LogicaJPA logica = new LogicaJPA("TestQax-Ejb");

	@Override
	public List<Domanda> getDomande() {
		
		List<Domanda> domande = logica.readNameQuery("Domanda.findAll").setMaxResults(10).getResultList();
		
		return domande;
	}


	@Override
	public List<Domanda> getDomande(String categoria) {
		Query q = logica.readNameQuery("Domanda.findByCategory");
		q.setParameter("categoria", categoria);
		List<Domanda> domande = (List<Domanda>) q.getResultList(); 

		//logica.closeLogicaJPA();
		return domande;
	}

	@Override
	public Domanda getDomandaConRisposte(int id) {
  	
		Query q = logica.readNameQuery("Domanda.findDomandaRisposte");
		q.setParameter("iddomanda", id);
		Domanda domanda = (Domanda) q.getSingleResult();

	    return domanda;	
	}
	
	
	@Override
	public Domanda getDomanda(int id) {
		Query q =  logica.readNameQuery("Domanda.findById");
    	q.setParameter("iddomanda", id);
    	Domanda domanda = (Domanda) q.getSingleResult();
    	
    	return domanda;
	}

	
	@Override
	public ListaDomandeBean getMieDomande(String username, String password, ListaDomandeBean myListaDomande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creaDomanda(String titolo, String descrizione, String categoria, LoginBean utente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creaDomanda(DomandaBean domanda, String titolo, String descrizione, String categoria,
			LoginBean utente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornaDomanda(CategoriaBean categoriaBean, int iddomanda, String titolo, String descrizione,
			String categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaDomanda(int idDomanda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LoginBean prendiUtente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomandaBean prendiDomanda(int iddomanda) {
		// TODO Auto-generated method stub
		return null;
	}

}
