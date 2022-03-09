package it.polito.tdp.model;

import java.util.*;

public class Libretto {

	private List<Voto> voti;

	public Libretto() {
		this.voti = new ArrayList<Voto>();
	}

	//Delega
	public void add(Voto v) {
		this.voti.add(v);
	}

	@Override
	public String toString() {
		return this.voti.toString();
	}
	
	public Libretto filtraPunti(int punti) {
		Libretto l = new Libretto();
		
		for(Voto i : this.voti) {
			if(i.getPunti()==punti) {
				l.add(i);
			}
		}
		
		return l;
	}
	/**
	 * @param nome Nome dell'esame
	 * @return punteggio numerico oppure eccezione se il corso non esiste
	 */
	public Integer ricercaCorso(String nome) {
		for(Voto i : this.voti) {
			if(i.getNomeCorso().compareTo(nome)==0) {
				return i.getPunti();
			}
		}
		//return -1;
		return null;
		//throw new IllegalArgumentException("Corso non trovato");
	}
	
	public boolean isDuplicato(Voto v) {
		for(Voto i : this.voti) {
			if(i.equals(v))
				return true;
		}
		return false;
	}
	
	public boolean isConflitto(Voto v) {
		Integer punti = this.ricercaCorso(v.getNomeCorso());
		
		if(punti!=null && punti!= v.getPunti()) {
			return false; 
		} else {
			return true;
		}
	}
	
}
