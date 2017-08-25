package it.capone.test;

import it.capone.bean.DomandaBean;
import it.capone.bean.ListaDomandeBean;
import it.capone.bean.ListaRisposteBean;
import it.capone.bean.RispostaBean;
import it.capone.service.CGestioneDomande;

public class MainGestioneDomande {

	public static void main(String[] args) {
		CGestioneDomande cgd = new CGestioneDomande();
        ListaDomandeBean listaDomandeFirst = new ListaDomandeBean();
        
        //ListaDomandeBean listaDomande = cgd.getDomande(listaDomandeFirst);  		//OK
        //ListaDomandeBean listaDomande = cgd.getDomande(listaDomandeFirst, "Java");  //OK
        
        //DomandaBean[] domande = (DomandaBean[]) listaDomande.getListaDomande().
		//toArray(new DomandaBean[listaDomande.getDimensione()]);        
        //stampaDomande(domande);
	
        
        int id = 1;
        DomandaBean domandaBean = null;
        ListaRisposteBean listaRisposte = null;
        DomandaBean domanda = cgd.getDomandaConRisposte(id, domandaBean, listaRisposte);
        
        stampaDomandaRisposte(domanda);
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
		
		if(!domanda.getRisposte().isEmpty()) {
			ListaRisposteBean listaRisposte = domanda.getRisposte();
			
			for(RispostaBean r : listaRisposte.getListaRisposte()) {
				System.out.println("Risposta: " +r.getDescrizione()+ " in data " +r.getDataCreazione()+ 
						"data dall'utente " +r.getUtente().getNome());
			}
		}
		else 
			System.out.println("La domanda non ha avuto ancora risposte");
	}
	
}
