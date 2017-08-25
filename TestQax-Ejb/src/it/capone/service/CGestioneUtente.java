package it.capone.service;

import java.util.Date;
import java.util.GregorianCalendar;

import it.capone.DAO.UtenteDAO;
import it.capone.DAO.UtenteDAORemote;
import it.capone.bean.LoginBean;
import it.capone.entity.Utente;
import it.capone.utility.Data;

public class CGestioneUtente {

	private UtenteDAORemote utenteDAO = new UtenteDAO();
	
	public boolean verifyUtente(LoginBean loginBean, String nome, String password) {
		boolean exists = false;
		
		Utente utente = utenteDAO.verifyUtente(nome, password);
		
		if(utente != null)
		{
			loginBean.setIdUtente(utente.getIdutente());
			loginBean.setNome(utente.getNome());
			loginBean.setPassword(utente.getPassword());
			loginBean.setEmail(utente.getEmail());
			exists = true;
		}
		
		return exists;
	}

	
	public void registraUtente(LoginBean loginBean, String nome, String password, String email) {
		
		Utente ut = utenteDAO.registraUtente(nome, password, email);
		
		Date time =  ut.getDataregistrazione();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(time);
        
		loginBean = new LoginBean(ut.getIdutente(), ut.getNome(), ut.getPassword(), ut.getEmail(),
				new Data(
           	            gc.get(GregorianCalendar.YEAR),
           	            gc.get(GregorianCalendar.MONTH) + 1,
           	            gc.get(GregorianCalendar.DATE)
           	            )
				);
	}
	
	
	public boolean aggiornaUtente(int idutente, String password, String email) {
		boolean modificato;
		
		modificato = utenteDAO.aggiornaUtente(idutente, password, email);
		
		return modificato;
	}
}
