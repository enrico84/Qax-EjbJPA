package it.capone.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import it.capone.bean.LoginBean;
import it.capone.service.CGestioneUtente;

public class MainGestioneUtente {
	
	public static void main(String[] args) {
		
		CGestioneUtente cUt = new CGestioneUtente();
		String userId = "gianni";
		String password = "ghizzo";
		//verificaUtente(cUt, userId, password);  		    //OK
		
		String userId2 = "utente Test";
		String password2 = "testtest";
		String email2 = "utente_test@gmail.com";
		//registraUtente(cUt, userId2, password2, email2);   //OK
		//verificaUtente(cUt, userId2, password2);    //OK
		
		int idUtenteDaModificare = 4;
		String nome = "test44";
		String newPassword = "test4";
		String newMail = "email_test4@hotmail.it";
		//modificaUtente(cUt, idUtenteDaModificare, newPassword, newMail);  //OK
		//verificaUtente(cUt, nome, newPassword);  //OK
		
		
		cUt.closeLogicaJPA();
		
	}
	
	private static void verificaUtente(CGestioneUtente cUt, String nome, String password) {
		LoginBean loginBean = new LoginBean();
		
		boolean result = cUt.verifyUtente(loginBean, nome, password);
		
		if(result)
			System.out.println("Utente registrato: Nome:" +loginBean.getNome()+  ", email: " +loginBean.getEmail());
		else {
			System.out.println("Utente NON registrato");
		}
	}
	
	private static void registraUtente(CGestioneUtente cUt, String userId, String password, String email) {
		LoginBean loginBean = new LoginBean();
		
		cUt.registraUtente(loginBean, userId, password, email);
		
	}
	
	
	private static void modificaUtente(CGestioneUtente cUt, int idutente, String password, String email) {
		
		boolean modificato = cUt.aggiornaUtente(idutente, password, email);
		if(modificato) 
			System.out.println("Utente modificato, OK");
		else
			System.out.println("Modifica NON effettuata...");
		
	}

}
