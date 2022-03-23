package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaVoti {

	public static void main(String[] args) {
		
		ProvaVoti provaVoti = new ProvaVoti();
		provaVoti.aggiungiVoto("basiDati", 23);
		
		//Dobbiamo collegarci al database mysql
		//Questa è la stringa url che bisogna saper scrivere
		//per caricare la classe driver da caricare
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=root";
		
		//Creo una classe che sa quale driver creare per il nostro database
		try {
			//Apro la connessione
			//E' un tubo che unisce il programma al db
			Connection conn = DriverManager.getConnection(url);
		
			//E' il mezzo che va avanti e indietro tra me e il db
			Statement st = conn.createStatement();
			
			String sql = "SELECT * FROM voti"; //Normalmente non si scrive subito la query
			//ma si prova prima a farla nella vista heideSQL e poi la si copia
			
			//Eseguo la stringa query che gli passo come parametro
			//e lo salvo in un oggetto di classe ResultSet
			//E' un meccanismo con cui posso accedere i dati, uno alla volta
			//ResultSet è un oggetto un po' strano perchè
			//punta ai dati risultanti dalla query
			//Il cursore per default si posizione prima della prima riga
			//quindi devo spostarmi  per leggere effettivamente i dati,
			//questo fino alla fine, che non è l'ultima riga ma quella dopo
			//Es: una query con 2 righe risultanti ha 4 righe nel ResultSet
			ResultSet res = st.executeQuery(sql);
			//Un oggetto di questa classe ha a disposizione diversi  metodi di tipo getXXX
			//dove XXX è il tipo di dato (getInt, getString, etc) e accetta diversi parametri
			//come getXXX(int numeroColonna) o getXXX(String nomeColonna)
			//Per spostare il cursore esiste il metodo next(), che ritorna true se si è posizionato 
			//su una riga leggibile e false se si posizione sull'ultima riga fittizia
			
			//Lettura dati
			while(res.next()) {
				String nome = res.getString("nome");
				int voto = res.getInt("punti");
				System.out.println(nome + ": " + voto);
			}
			
			//Altri metodi a disposizione sono: first(), last(), previous(), etc
			
			//Quando non mi serve piu la query è buona norma chiudere lo Statement
			st.close();
			
			//Chiudo la connessione 
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Quando facciamo operazioni di tipo modifica dati,
		 * ovver INSERT, UPDATE o DELETE non si usa executeQuery()
		 * ma bensi executeUPDATE(String sql, metodo che non ritorna nulla
		 */
		
		
		
	}
	
	//Prova di INSERT
	public void aggiungiVoto (String nome, int punti) {
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=root";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			
			/*
			Statement st = conn.createStatement();
			String sql = "INSERT INTO voti (nome, punti) "+ 
			"VALUES ('"+nome+"', "+punti+");";
			
			 * Questo sistema è molto insicuro e vulnerabile ad 
			 * attacchi di tipo SQL Injection.
			 * La corretta query invece è "preparata" e poi infine 
			 * spedita la db
			 */
			
			String sql = "INSERT INTO voti (nome, punti) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			
			//Inserisco i valori corretti attraverso dei metodi appositi
			st.setString(1, nome);
			st.setInt(2, punti);
			//1 e 2 rappresentano i punti ?
			
			int res = st.executeUpdate(sql);
			st.close();
			conn.close(); 
			 
			if(res==1) {
				System.out.println("Dato inserito");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
