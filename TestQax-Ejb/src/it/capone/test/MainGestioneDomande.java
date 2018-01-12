package it.capone.test;

import java.util.List;

import it.capone.bean.CategoriaBean;
import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.LoginBean;
import it.capone.bean.RispostaBean;
import it.capone.entity.Risposta;
import it.capone.service.CGestioneDomande;
import it.capone.service.CGestioneUtente;

public class MainGestioneDomande {

	public static void main(String[] args) {
		CGestioneDomande cgd = new CGestioneDomande();
        ListaDomandeBean listaDomandeFirst = new ListaDomandeBean();
        
        ListaDomandeBean listaDomande = cgd.getDomande(listaDomandeFirst);  		//OK
        //ListaDomandeBean listaDomande = cgd.getDomande(listaDomandeFirst, "Java");  //OK
        
        //LoginBean utenteBean = cgd.prendiUtente(2);
        //cgd.creaDomanda("Prima domanda di dario", "Questo è la prima domanda sul CSS fatta da Dario", "css", utenteBean);  //OK
        //ListaDomandeBean listaDomande = cgd.getMieDomande("dario", "bayern", listaDomandeFirst);  //OK
        
        //CategoriaBean categoriaBean = cgd.prendiCategoria(9);  //Non serve ora, ma lo lascio per testarne il funzionamento
        //int idDomanda = 18; 
        //modificaDomanda(cgd, categoriaBean, idDomanda, "Update della domanda numero 18", "Update della domanda 18 di Gianni Ghizzo", "tempo libero"); //OK
        
        //cgd.eliminaDomanda(19); //OK
        //ListaDomandeBean listaDomande = cgd.getMieDomande("gianni", "ghizzo", listaDomandeFirst);  //OK  
        
        DomandaBean[] domande = (DomandaBean[]) listaDomande.getListaDomande().
		toArray(new DomandaBean[listaDomande.getDimensione()]);        
        stampaDomande(domande);
	
//        int id = 17;
//        DomandaBean domandaBean = null;
//        ListaRisposteBean listaRisposte = new ListaRisposteBean();
//        DomandaBean domanda = cgd.getDomandaConRisposte(id, domandaBean, listaRisposte);
//        
//        stampaDomandaRisposte(domanda);
        
        //OPERAZIONE DA EFFETTUARE SEMPRE ALLA FINE PER CHIUDERE L'ENTITY MANAGER
        cgd.closeLogicaJPA();
          
	} 

	
	public static void stampaDomande(DomandaBean[] listaDomande) {
		for(int i=0; i<listaDomande.length; i++) {
			System.out.println("Titolo domanda: " +listaDomande[i].getTitolo()
			+"\nDescrizione: " +listaDomande[i].getDescrizione()+""
			+ "\nUtente " +listaDomande[i].getUtente());
		}
	}
	
	
	public static void stampaDomandaRisposte(DomandaBean domanda) {
		System.out.println("Titolo domanda: " +domanda.getTitolo()
		+"\nDescrizione: " +domanda.getDescrizione()+""
		+ "\nUtente " +domanda.getUtente());
		
		if(domanda.getRisposte() != null) {
			if(!domanda.getRisposte().isEmpty()) {
				ListaRisposteBean listaRisposte = domanda.getRisposte();
				
				for(RispostaBean r : listaRisposte.getListaRisposte()) {
					int voto = r.getVoto();
					if( (voto == 0) ) {
						voto=0;
					}
					System.out.println("Risposta: " +r.getDescrizione()+ " in data " +r.getDataCreazione()+ 
							" dell'utente " +r.getUtente().getNome()+ ", ha avuto " +voto+ " voti");
				}
			}
			else 
				System.out.println("La domanda non ha avuto ancora risposte");
		}
	}
	
	
	private static void modificaDomanda(CGestioneDomande cGestDom, CategoriaBean cateBean, int iddomanda, String titolo, String descrizione, String categoria) {
		
		boolean modificato = cGestDom.aggiornaDomanda(cateBean, iddomanda, titolo, descrizione, categoria);
		if(modificato) 
			System.out.println("Utente modificato, OK");
		else
			System.out.println("Modifica NON effettuata...");
		
	}
	
}
