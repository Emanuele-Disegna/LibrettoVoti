package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.db.LibrettoDAO;

public class Libretto {

	private List<Voto> voti ;
	
	public Libretto() {
		this.voti = new ArrayList<Voto>() ;
	}
	
	public boolean add(Voto v) {
		LibrettoDAO dao = new LibrettoDAO();
		
		if(dao.creaVoto(v)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public List<Voto> getVoti() {
		LibrettoDAO dao = new LibrettoDAO();
		return dao.readAllVoto();
	}
	
	
	public Libretto votiMigliorati() {
		Libretto nuovo = new Libretto() ;
		for(Voto v: this.voti) {
			int punti = v.getPunti() ;
			if(punti>=24)
				punti +=2 ;
			else 
				punti++ ;
			if (punti>30)
				punti=30 ;

			// NOOOO va a modificare l'oggetto nel libretto originale
			//			v.setPunti(punti);
			//			nuovo.add(v) ;
			
			nuovo.add(new Voto(v.getNome(), punti)) ;
		}
		return nuovo ;
	}
	
	
	// FUNZIONERÀ COSÌ??? Proviamo... e capiamo perché
	public void cancellaVotiMinori(int punti) {
		for(Voto v: this.voti) {
			if(v.getPunti()<punti)
				this.voti.remove(v) ;
		}
	}
	
	public String toString() {
		return this.voti.toString() ;
	}
}