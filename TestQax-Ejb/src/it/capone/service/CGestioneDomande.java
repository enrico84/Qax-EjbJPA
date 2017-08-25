package it.capone.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.capone.DAO.DomandaDAO;
import it.capone.DAO.DomandaDAOLocal;
import it.capone.DAO.DomandaDAORemote;
import it.capone.bean.CategoriaBean;
import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.LoginBean;
import it.capone.entity.Categoria;
import it.capone.entity.Domanda;
import it.capone.entity.Utente;
import it.capone.entity.Risposta;
import it.capone.utility.Data;

public class CGestioneDomande {
	
	
	//@EJB
	//private DomandaServiceRemote domandaService;
	
	private DomandaDAORemote domandaService = new DomandaDAO();
	
	
//	public CGestioneDomande() throws RuntimeException {
//		try {
//			
//			domandaService = (DomandaDAOLocal) new InitialContext().lookup("java:global/Qax-EJB/DomandaDAO!it.capone.DAO.DomandaDAOLocal");
//			//domandaDAO = (DomandaDAORemote)new InitialContext().lookup("java:global/Qax-EJB/ejbModule/DomandaDAOImpl");
//			
//		}
//		catch(NamingException ex) {
//			throw new RuntimeException(ex);
//		}
//	}
	 
	
	
	public ListaDomandeBean getDomande(ListaDomandeBean listaDomande){
		
		List<Domanda> domande = domandaService.getDomande();
             
                for(Domanda d : domande) {
                    Date time =  d.getDatacreazione();
                    
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(time);
                    CategoriaBean catBean = fromCategoryToBean(d.getCategoria());
                    LoginBean utBean = fromUtenteToBean(d.getUtente());
          
                    listaDomande.creaDomanda(d.getIddomanda(), d.getTitolo(),d.getDescrizione(), 
                            new Data(
	           	            gc.get(GregorianCalendar.YEAR),
	           	            gc.get(GregorianCalendar.MONTH) + 1,
	           	            gc.get(GregorianCalendar.DATE)
	           	            		         ), 
                            catBean, 
                            utBean);
                }
                
		
		return listaDomande;
	}
	
	////////////// Metodi utilità
	
	private CategoriaBean fromCategoryToBean(Categoria cat) {
        CategoriaBean catBean = new CategoriaBean();
        catBean.setIdcategoria(cat.getIdcategoria());
        catBean.setNome(cat.getNome());
        
        return catBean;
    }
    
    
    private LoginBean fromUtenteToBean(Utente ut) {
        LoginBean utBean = new LoginBean();
        utBean.setEmail(ut.getEmail());
        utBean.setIdUtente(ut.getIdutente());
        utBean.setNome(ut.getNome());
        utBean.setPassword(ut.getPassword());
        
        Date time = ut.getDataregistrazione();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(time);
        
        utBean.setDataregistrazione(
                new Data(gc.get(GregorianCalendar.YEAR),
           	     gc.get(GregorianCalendar.MONTH) + 1,
           	     gc.get(GregorianCalendar.DATE)
                    )
        );
        return utBean;
    }
	
	////////////////////////////////////
	
	public ListaDomandeBean getDomande(ListaDomandeBean listaDomande, String categoria) {
		
		List<Domanda> domande = domandaService.getDomande(categoria);
		
		for(Domanda d : domande) {
            Date time =  d.getDatacreazione();
            
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(time);
            CategoriaBean catBean = fromCategoryToBean(d.getCategoria());
            LoginBean utBean = fromUtenteToBean(d.getUtente());
  
            listaDomande.creaDomanda(d.getIddomanda(), d.getTitolo(),d.getDescrizione(), 
                    new Data(
       	            gc.get(GregorianCalendar.YEAR),
       	            gc.get(GregorianCalendar.MONTH) + 1,
       	            gc.get(GregorianCalendar.DATE)
       	            		         ), 
                    catBean, 
                    utBean);
        }
		
		return listaDomande;
	}
	
	
	public DomandaBean getDomandaConRisposte(int id, DomandaBean domanda, ListaRisposteBean listaRisposta) {
		
		Domanda d = domandaService.getDomandaConRisposte(id);
		if(d != null) {
			for(Risposta r: d.getRispostas()) {
				Date time = r.getDatacreazione();
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(time);
				
				LoginBean utBean = fromUtenteToBean(r.getUtente());
				DomandaBean domandaBean = prendiDomanda(id);
				listaRisposta.creaRisposta(r.getIdrisposta(), r.getDescrizione(), utBean, 
											new Data(
						           	            gc.get(GregorianCalendar.YEAR),
						           	            gc.get(GregorianCalendar.MONTH) + 1,
						           	            gc.get(GregorianCalendar.DATE)
												), 
											domandaBean);
				}
			
			Date date = d.getDatacreazione();
			GregorianCalendar gcc = new GregorianCalendar();
			gcc.setTime(date);
			domanda.setTitolo(d.getTitolo());
	        domanda.setDescrizione(d.getDescrizione());
	        domanda.setDatacreazione( new Data(
	            							gcc.get(GregorianCalendar.YEAR),
			     	            		    gcc.get(GregorianCalendar.MONTH) + 1,
			     	            		    gcc.get(GregorianCalendar.DATE)
			     	                 ));
	         domanda.setUtente(prendiUtente(d.getUtente().getIdutente()));
	         domanda.setRisposte(listaRisposta);
			
			
		}
		else { //Nel caso la prima query risulti vuota, ne creo una più semplice con solo le proprietà della Domanda
			Domanda dd = domandaService.getDomanda(id);
			Date date = dd.getDatacreazione();
			GregorianCalendar gcc = new GregorianCalendar();
			gcc.setTime(date);
			domanda.setTitolo(dd.getTitolo());
	        domanda.setDescrizione(dd.getDescrizione());
	        domanda.setDatacreazione( new Data(
	            							gcc.get(GregorianCalendar.YEAR),
			     	            		    gcc.get(GregorianCalendar.MONTH) + 1,
			     	            		    gcc.get(GregorianCalendar.DATE)
			     	                 ));
	         domanda.setUtente(prendiUtente(dd.getUtente().getIdutente()));
		}
		
		return domanda;
	}
	
	
	
	public ListaDomandeBean getMieDomande(String username, String password, ListaDomandeBean myListaDomande) {
		
		domandaService.getMieDomande(username, password, myListaDomande);
		return myListaDomande;
		
	}
	
	
	public void creaDomanda(String titolo, String descrizione, String categoria, LoginBean utente) {
		domandaService.creaDomanda(titolo, descrizione, categoria, utente);	
	}
	
	
	public void creaDomanda(DomandaBean domanda, String titolo, String descrizione, String categoria, LoginBean utente) {
		domandaService.creaDomanda(domanda, titolo, descrizione, categoria, utente);
	}
	
	
	public void aggiornaDomanda(CategoriaBean categoriaBean, int iddomanda, String titolo, String descrizione, 
			                       String categoria) {
		domandaService.aggiornaDomanda(categoriaBean, iddomanda, titolo, descrizione, categoria);
		
	}
	
	
	public void eliminaDomanda(int idDomanda) {
		domandaService.eliminaDomanda(idDomanda);
	}
	
	
	public LoginBean prendiUtente(int id) {
		LoginBean utente = domandaService.prendiUtente(id);
		
		return utente;
	}
	
	
	public DomandaBean prendiDomanda(int iddomanda) {
		DomandaBean domanda = domandaService.prendiDomanda(iddomanda);
	
		return domanda;	
	}
	
	

}
