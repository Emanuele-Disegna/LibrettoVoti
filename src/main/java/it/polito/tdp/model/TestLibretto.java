package it.polito.tdp.model;

public class TestLibretto {

	public static void main(String[] args) {
		Libretto lib = new Libretto();
		
		lib.add(new Voto("Analisi 1", 21));
		lib.add(new Voto("PGP", 27));
		lib.add(new Voto("OOP", 28));
		lib.add(new Voto("Informatica", 27));
		lib.add(new Voto("Diritto", 25));
		lib.add(new Voto("Telematici", 21));
		lib.add(new Voto("Fisica 2", 24));
		lib.add(new Voto("Analisi 2", 21));
		lib.add(new Voto("Ricerca Op", 26));
		lib.add(new Voto("JECW", 24));
		
		//System.out.println()
		Libretto lib25 = lib.filtraPunti(25);
		System.out.println(lib25);
		
		
		
	}

}
