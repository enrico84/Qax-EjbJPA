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
		
		
		Domanda d = domandaService.getDomanda(id);
		domanda = prendiDomanda(id);
		
		if(domanda != null) {
			if((domanda.getTitolo() != null) && (domanda.getDescrizione() != null)) {
				
				List<Risposta> answers = (List<Risposta>) domandaService.getRisposteDomanda(id);
				
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
		         
		         if(!answers.isEmpty()) {
						for(Risposta a : answers) {
							Date time = a.getDatacreazione();
							GregorianCalendar gc = new GregorianCalendar();
							gc.setTime(time);
							LoginBean utBean = fromUtenteToBean(a.getUtente());
							listaRisposta.creaRisposta(a.getIdrisposta(), a.getDescrizione(), utBean, 
									new Data(
				           	            gc.get(GregorianCalendar.YEAR),
				           	            gc.get(GregorianCalendar.MONTH) + 1,
				           	            gc.get(GregorianCalendar.DATE)
										), 
									domanda);
						}
					}
		         
		         domanda.setRisposte(listaRisposta);
				
			}
			
		}
		
		
		return domanda;
	}
	
	
	
	public ListaDomandeBean getMieDomande(String username, String password, ListaDomandeBean myListaDomande) {
		
		List<Domanda> domande = domandaService.getMieDomande(username, password);
		
		for(Domanda d : domande) {
            Date time =  d.getDatacreazione();
            
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(time);
            CategoriaBean catBean = fromCategoryToBean(d.getCategoria());
            LoginBean utBean = fromUtenteToBean(d.getUtente());
  
            myListaDomande.creaDomanda(d.getIddomanda(), d.getTitolo(),d.getDescrizione(), 
                    new Data(
	       	            gc.get(GregorianCalendar.YEAR),
	       	            gc.get(GregorianCalendar.MONTH) + 1,
	       	            gc.get(GregorianCalendar.DATE)
       	            		), 
                    catBean, 
                    utBean);
        }
		
		
		return myListaDomande;
		
	}
	
	
	public void creaDomanda(String titolo, String descrizione, String categoria, LoginBean utente) {
		Domanda domanda = domandaService.creaDomanda(titolo, descrizione, categoria, utente);
		DomandaBean domandaBean = new DomandaBean();
		
		Date time =  domanda.getDatacreazione();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(time);		
				
		domandaBean.setIddomanda(domanda.getIddomanda());
		domandaBean.setTitolo(domanda.getTitolo());
		domandaBean.setDescrizione(domanda.getDescrizione());
		CategoriaBean categoriaBean = fromCategoryToBean(domanda.getCategoria()); //Creo la categoria del Bean
		domandaBean.setCategoria(categoriaBean);
		LoginBean utenteBean = fromUtenteToBean(domanda.getUtente());	//Creo l'utente del Bean
		domandaBean.setUtente(utenteBean);
		
		//Setto la data del Bean
		domandaBean.setDatacreazione(
				new Data(
           	            gc.get(GregorianCalendar.YEAR),
           	            gc.get(GregorianCalendar.MONTH) + 1,
           	            gc.get(GregorianCalendar.DATE)
           	            ));
		
	} 
	
	
	
	public boolean aggiornaDomanda(CategoriaBean categoriaBean, int iddomanda, String titolo, String descrizione, 
			                       String categoria) {
		boolean modificato;
		
		modificato = domandaService.aggiornaDomanda(iddomanda, titolo, descrizione, categoria);
		
		return modificato;
		
	}
	
	
	public void eliminaDomanda(int idDomanda) {
		domandaService.eliminaDomanda(idDomanda);
	}
	
	
	
	public LoginBean prendiUtente(int id) {
		Utente utente = domandaService.prendiUtente(id);
		
		LoginBean utBean = fromUtenteToBean(utente);
		return utBean;
	}
	
	
	public DomandaBean prendiDomanda(int iddomanda) {
		Domanda domanda = domandaService.getDomanda(iddomanda);
		DomandaBean domandaBean = fromDomandaToBean(domanda);
		
		return domandaBean;	
	}
	
	
	public void closeLogicaJPA() {
		domandaService.closeLogicaJPA();
	}
	
//////////////Metodi utilità ////////////////////////////////
	
	/**
	* 
	* @param cat
	* @return Converte una Entity Categoria in un bean -> CategoriaBean
	*/
	private CategoriaBean fromCategoryToBean(Categoria cat) {
		CategoriaBean catBean = new CategoriaBean();
		catBean.setIdcategoria(cat.getIdcategoria());
		catBean.setNome(cat.getNome());
		
		return catBean;
	}


	/**
	* 
	* @param ut
	* @return Converte una Entity Utente in un bean -> LoginBean
	*/
	private LoginBean fromUtenteToBean(Utente ut) {
		LoginBean utBean = new LoginBean();
		utBean.setEmail(ut.getEmail());
		utBean.setIdUtente(ut.getIdutente());
		utBean.setNome(ut.getNome());
		utBean.setPassword(ut.getPassword());
		
//		Date time = ut.getDataregistrazione();
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTime(time);
//		
//		utBean.setDataregistrazione(
//		     new Data(gc.get(GregorianCalendar.YEAR),
//			     gc.get(GregorianCalendar.MONTH) + 1,
//			     gc.get(GregorianCalendar.DATE)
//		         )
//		);
	return utBean;
	}


	/**
	* 
	* @param dom
	* @return Converte una Entity Domanda in un bean -> DomandaBean
	*/
	private DomandaBean fromDomandaToBean(Domanda dom) {
		DomandaBean domBean = new DomandaBean();
		if(dom.getTitolo() != null && dom.getDescrizione() != null && 
		   dom.getUtente().getIdutente() != null && dom.getDatacreazione() != null) {
			
			domBean.setTitolo(dom.getTitolo());
			domBean.setDescrizione(dom.getDescrizione());
			domBean.setUtente(prendiUtente(dom.getUtente().getIdutente()));
			
			Date date = dom.getDatacreazione();
			GregorianCalendar gcc = new GregorianCalendar();
			gcc.setTime(date);
			
			domBean.setDatacreazione( new Data(
			 							gcc.get(GregorianCalendar.YEAR),
			  	            		    gcc.get(GregorianCalendar.MONTH) + 1,
			  	            		    gcc.get(GregorianCalendar.DATE)
			  	                 ));
		}
		
		return domBean;
	}
////////////////////////////////////

}
