package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.model.Voto;

public class LibrettoDAO {
	/*
	 * Solitamente queste classi sono usati per 
	 * gestire funzionalita di tipo CRUD
	 * Create Read Update (Delete)
	 */
	
	//Struttura ideale per l'aggiunta di voti (o dati in generale)
	public boolean creaVoto(Voto v) {
		//Faccio la INSERT qua
		try {
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO voti (nome, punti) VALUES (?, ?)";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, v.getNome());
		st.setInt(2, v.getPunti());
		
		int res = st.executeUpdate();
		
		st.close();
		conn.close();
		
		return (res==1);
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Voto> readAllVoto() {
		 try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT * FROM voti";
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			
			List<Voto> result = new ArrayList<>();
			
			while(res.next()) {
				String nome = res.getString("nome");
				int punti = res.getInt("punti");
				result.add(new Voto(nome, punti));
			}
			
			//Bisogna ricordarsi di chiudere le connessioni 
			st.close();
			conn.close();
			
			return result;
		 } catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public Voto readVotoByNome(String nome) {
		return null;
	}
	
	
}
