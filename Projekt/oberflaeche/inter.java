package oberflaeche;
public interface inter {
		public void verbinden();
		public void datenbankanlegen();
		public int AnzahlArbeit();
		public int AnzahlStudiengaenge();
		public int AnzahlStatus();
		public int AnzahlProf();
		public int AnzahlBetreuer();
		public int AnzahlStudent();
		public String[] vergleichen();
		public String[] studiengaenge();
		public String[] status();
		public String[] betreuer();
		public String[] prof();
		public String[][] datenBetreuer();
		public String[][] datenProf();
		public String[][] datenStudent();
	    public String[] datenListe(int zeile);
	    public boolean hinzufuegenStatus(String s);
	    public boolean hinzufuegenStudiengang(String s);
	    public boolean hinzufuegenProfessor(String[] daten);
	    public boolean hinzufuegenBetreuer(String[] daten);
	    public boolean hinzufuegenAbschlussarbeit(A o);
	    public boolean loeschenStatus(String s);
	    public boolean loeschenStudiengang(String s);
	    public boolean loeschenBetreuer(String s);
	    public boolean loeschenProfessor(String s);
	    public boolean loeschenAbschlussarbeit(String s);
	    public boolean aendernStatusbezeichnung(String alt, String neu);
	    public boolean aendernStudiengangbezeichnung(String alt, String neu);
	    public boolean aendernProfessor(String personalnummer, String[] neueDaten);
	    public boolean aendernBetreuer(String personalnummer, String[] neueDaten);

}
