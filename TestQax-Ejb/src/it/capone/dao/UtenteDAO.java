package it.capone.dao;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;
import javax.persistence.Query;

import it.capone.db.LogicaJPA;
import it.capone.entity.Utente;
import it.capone.utility.Data;


/**
 * Session Bean implementation class UtenteDAO
 */
@Stateless
public class UtenteDAO implements UtenteDAORemote, UtenteDAOLocal {

	LogicaJPA logica = new LogicaJPA("TestQax-Ejb");
	
	
	/**
	 * @return VERIFICA se un dato Utente esiste
	 */
	public Utente verifyUtente(String userId, String password) {
		
			Query q =  logica.readNameQuery("Utente.findByNomePass");
			q.setParameter("nome", userId);
			q.setParameter("password", password);
			//restituisce un solo risultato sottoforma di un Object Utente
			Utente u = (Utente) q.getSingleResult();
			
			return u;
	}
	
	
	/**
	 * INSERT UTENTE
	 */
	public Utente registraUtente(String nome, String password, String email) {

			Utente ut = new Utente();
			ut.setNome(nome);
			ut.setPassword(password);
			ut.setEmail(email);
		
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
		    
			ut.setDataregistrazione(date);
	        
			// ERRATO INSERIRE L'ID MANUALMENTE, POICHE' LE ENTITA' HANNO COME ATTRIBUTO DI ID -> @GeneratedValue(strategy = IDENTITY)
			//Integer id =(Integer) logica.readSimpleQuery("select max(u.id) from Utente u").getSingleResult();
			//int maxId = id++;
			//ut.setIdutente(maxId);
			
			logica.create(ut);
			
			
			return ut;
		
	}
	
	
	/**
	 * @return UPDATE UTENTE
	 */
	public boolean aggiornaUtente(int idutente, String password, String email) {
		
		boolean modificato = false;
		Utente ut = new Utente();
		Utente utente = (Utente) logica.find(ut, idutente);
		utente.setPassword(password);
		utente.setEmail(email);
		modificato = logica.update(utente);
		
		return modificato;

	}
	
	
	public void closeLogicaJPA() {
		logica.closeLogicaJPA();
	}


}
