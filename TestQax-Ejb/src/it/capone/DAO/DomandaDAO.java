package it.capone.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import it.capone.bean.CategoriaBean;
import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.LoginBean;
import it.capone.db.LogicaJPA;
import it.capone.entity.Categoria;
import it.capone.entity.Domanda;
import it.capone.entity.Risposta;
import it.capone.entity.Utente;
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
	public Domanda getDomanda(int id) {
		Query q =  logica.readNameQuery("Domanda.findById");
    	q.setParameter("iddomanda", id);
    	Domanda domanda = (Domanda) q.getSingleResult();
    	
    	return domanda;
	}
	
	
	@Override
	public List<Risposta> getRisposteDomanda(int id) {
		Query q =  logica.readNameQuery("Risposta.trovaRisposte");
    	q.setParameter("iddomanda", id);
    	List<Risposta> risposte = (ArrayList<Risposta>) q.getResultList();
    	
    	return risposte;
	}
	
	
	@Override
	public List<Domanda> getMieDomande(String username, String password) {
		Query q = logica.readNameQuery("Domanda.trovaMieDomande");
		q.setParameter("nome", username);
		q.setParameter("password", password);
		List<Domanda> domande = (ArrayList<Domanda>) q.getResultList(); 
		return domande;
	}

	
	@Override
	public Domanda creaDomanda(String titolo, String descrizione, String nomeCat, LoginBean utente) {
		
		/* Controllo se la categoria esiste, SE NON esiste la aggiungo nella tabella qax.Categoria */
		//int idCat = Integer.parseInt(categoria);
		Categoria categ = prendiCategoriaByNome(nomeCat);
		if(categ == null) {
			categ = new Categoria();
			
			// ERRATO INSERIRE L'ID MANUALMENTE, POICHE' LE ENTITA' HANNO COME ATTRIBUTO DI ID -> @GeneratedValue(strategy = IDENTITY)
			//int maxidCategoria = getMaxId("qax.categoria", "idcategoria");
			//maxidCategoria++;
			//newCategoria.setIdcategoria(maxidCategoria);
			categ.setNome(nomeCat);
			logica.create(categ);		 
		}
		
		Domanda domanda = new Domanda();
		domanda.setTitolo(titolo);
		domanda.setDescrizione(descrizione);
		domanda.setCategoria(categ);
		
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		int anno = calendar.get(Calendar.YEAR);
		int mese = calendar.get(Calendar.MONTH) + 1;
		int giorno = calendar.get(Calendar.DAY_OF_MONTH);
	    int ore = calendar.get(Calendar.HOUR_OF_DAY);
	    int minuti = calendar.get(Calendar.MINUTE);
	    int secondi = calendar.get(Calendar.SECOND);
	    date.setYear(anno);
	    date.setMonth(mese);
	    date.setDate(giorno);
	    date.setHours(ore);
	    date.setMinutes(minuti);
	    date.setSeconds(secondi);
		domanda.setDatacreazione(date);
		
		Utente ut = prendiUtente(utente.getIdutente());
		domanda.setUtente(ut);
		
		logica.create(domanda);
		return domanda;
		
	}


	@Override
	public boolean aggiornaDomanda(int iddomanda, String titolo, String descrizione, String nomeCat) {
		/* Controllo se la categoria esiste, SE NON esiste la aggiungo nella tabella qax.Categoria */
		Categoria categ = prendiCategoriaByNome(nomeCat);
		if(categ == null) {
			categ = new Categoria();
			// ERRATO INSERIRE L'ID MANUALMENTE, POICHE' LE ENTITA' HANNO COME ATTRIBUTO DI ID -> @GeneratedValue(strategy = IDENTITY)
			//int maxidCategoria = getMaxId("qax.categoria", "idcategoria");
			//maxidCategoria++;
			//newCategoria.setIdcategoria(maxidCategoria);
			categ.setNome(nomeCat);
			logica.create(categ);		 
		}
		boolean modificato = false;
		Domanda dom = new Domanda();
		Domanda domanda = (Domanda) logica.find(dom, iddomanda);
		domanda.setTitolo(titolo);
		domanda.setDescrizione(descrizione);
		domanda.setCategoria(categ);
		
		modificato = logica.update(domanda);
		
		return modificato;
	}

	
	@Override
	public boolean eliminaDomanda(int idDomanda) {
		boolean cancellata = false;
		List<Risposta> risposte = (List<Risposta>) getRisposteDomanda(idDomanda);
		Domanda domandaDaEliminare = (Domanda) prendiDomanda(idDomanda);
		if(risposte.size() > 0) {
			for(Risposta r : risposte)
				logica.delete(r);
		}
		cancellata = logica.delete(domandaDaEliminare);
		
		return cancellata;
	}

	
	
	@Override
	public Utente prendiUtente(int id) {
		
		Query q =  logica.readNameQuery("Utente.findById");
    	q.setParameter("idutente", id);
    	Utente utente = (Utente) q.getSingleResult();
    	
    	return utente;	
	}
	
	@Override
	public Categoria prendiCategoria(int id) {
		
		Query q =  logica.readNameQuery("Categoria.findById");
    	q.setParameter("idcategoria", id);
    	Categoria categoria = null;
    	try {
    		categoria = (Categoria) q.getSingleResult();
    	}
    	catch(NoResultException nores) {
    		categoria = null;
    	}
    	
    	return categoria;	
	}
	
	
	public Categoria prendiCategoriaByNome(String nomeCat) {
		Query q =  logica.readNameQuery("Categoria.findByNome");
    	q.setParameter("nome", nomeCat);
    	Categoria categoria = null;
    	try {
    		categoria = (Categoria) q.getSingleResult();
    	}
    	catch(NoResultException nores) {
    		categoria = null;
    	}
    	
    	return categoria;	
		
	}
	

	@Override
	public Domanda prendiDomanda(int iddomanda) {
		Domanda domanda = getDomanda(iddomanda);
		
		return domanda;
	}
	
	
	
	//RITORNA IL MAX ID, DATA UNA TABELLA
	public int getMaxId(String tab, String id){
		String query = "SELECT MAX("+id+") FROM " +tab;
		Query q = logica.readSimpleQuery(query);
		Integer idMax = (Integer) q.getSingleResult();
		if(idMax == null) {
			idMax = -1;
		}
		return idMax;
	}
	
	
	public void closeLogicaJPA() {
		logica.closeLogicaJPA();
	}


	@Override
	public List<Object[]> getDomandaConRisposte(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
