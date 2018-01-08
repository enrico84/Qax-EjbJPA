E' un progetto di tipo JPA
Il progetto non ha bisogno di un Server per funzionare, si collega al db mediante la configurazione descritta nel "persistence.xml".
In un contesto Java SE, per lavorare con JPA bisogna necessariamente passare dall'EntityManagerFactory: una volta creato 
un EntityManager dalla factory, bisogna chiuderlo per liberare le risorse.

*Attenzione: Per ottenere la factory si usa l'annotazione @PersistenceUnit (per l'EntityManager invece era @PersistenceContext!). 

Il codice è molto semplice: viene creato l'EntityManager e nella clausola finally dei vari metodi CRUD viene chiuso, 
mentre la transazione per eseguire il salvataggio della persona è delegata al programmatore(non al Container)