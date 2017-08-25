package it.capone.DAO;

import javax.ejb.Local;

import it.capone.bean.LoginBean;
import it.capone.entity.Utente;

@Local
public interface UtenteDAOLocal {
	
	
	public Utente verifyUtente(String userId, String password);
	
	
	public Utente registraUtente(String nome, String password, String email);
		
		
	public boolean aggiornaUtente(int idutente, String password, String email);
	

}
