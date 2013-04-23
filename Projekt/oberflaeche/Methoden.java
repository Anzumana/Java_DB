package oberflaeche;

public class Methoden {
	String daten[][] = new String[][] {
			{"Abschlussarbeit Titel1", "Bachelorarbeit", "Status1", "Dauer1", "Anmeldedatum1", "Note1", "Student Name1", "Matrikelnummer1", "Wima", "Semester1", "Professor Name1", "Professor Personalnummer1", "Professor Titel1", "Professor Institut1", "Professor Name1", "Professor Personalnummer1", "Professor Titel1", "Professor Institut1", "Betreuer Name1", "Betreuer Personalnummer1", "Betreuer Institut1"},
			{"Abschlussarbeit Titel2", "Masterarbeit", "Status2", "Dauer2", "Anmeldedatum2", "Note2", "Student Name2", "Matrikelnummer2", "Studiengang2", "Semester2", "Professor Name2", "Professor Personalnummer2", "Professor Titel2", "Professor Institut2", "Professor Name2", "Professor Personalnummer2", "Professor Titel2", "Professor Institut2", "Betreuer Name2", "Betreuer Personalnummer2", "Betreuer Institut2"}
	};
	String studiengaenge1[] = new String[] {"Wima", "Mathe"};
	String status1[] = new String[] {"angemeldet", "in Bearbeitung", "Verlaengerung", "abgegeben", "begutachtet"};
	String Betreuer[][] = new String[][] {{"Betreuer Name1", "Betreuer Personalnummer1", "Betreuer Institut1"}, {"Betreuer Name2", "Betreuer Personalnummer2", "Betreuer Institut2"}};
	String Prof[][] = new String[][] {{"Professor Name1","Professor Personalnummer1", "Professor Titel1", "Professor Institut1"}, {"Professor Name2","Professor Personalnummer2", "Professor Titel2", "Professor Institut2"}};
	
	int anzahlAbschlussarbeiten() {
		return daten.length;
	}
	String[] datenListe(int zeile) {
		return daten[zeile];
	}
	String[] studiengaenge() {
		return studiengaenge1;
	}
	String[] status(){
		return status1;
	}
	String[][] datenBetreuer(){
		return Betreuer;
	}
	String[][] datenProf(){
		return Prof;
	}
	boolean hinzufuegenStatus(String s) {
		//s: neuer Status
		//return true;
		return false;
	}
	boolean hinzufuegenStudiengang(String s) {
		//s: neuer Studiengang
		//return true;
		return false;
	}
	boolean loeschenStatus(String s) {
		//s Status der zu Loeschen ist
		//return true;
		return false;
	}
	boolean loeschenStudiengang(String s) {
		//s Studiengang der zu Loeschen ist
		//return true;
		return false;
	}
	boolean aendernStatus(String s1, String s2) {
		//s1 wird zu s2 geaendert, s1 und s2: Status 
		return true;
		//return false;
	}
	boolean aendernStudiengang(String s1, String s2) {
		//s1 wird zu s2 geaendert, s1 und s2: Studiengang
		return true;
		//return false;
	}
	boolean hinzufuegenProfessor(String[] daten) {
		return true;
		//return false;
	}
	boolean hinzufuegenBetreuer(String[] daten) {
		 return true;
			//return false;
	}
	boolean loeschenProfessor(String personalnummer) {
		//return true;
		return false;
	}
	boolean loeschenBetreuer(String personalnummer) {
		//return true;
		return false;
	}
	boolean aendernProfessor(String personalnummer, String[] neueDaten) {
		return true;
		//return false;
	}
	boolean aendernBetreuer(String personalnummer, String[] neueDaten) {
		return true;
		//return false;
	}

	String speichernLogik(String[] datensatz) {
		//pruefen, ob die Daten konsistent sind
		return null;//kein Fehler: Speicherbefehl an Datenbankanbindung und Rueckgabe von null
		//return "Fehler";
	}
	
	//TestMain
}
