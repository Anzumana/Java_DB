package oberflaeche;
public class A {
	String Matrikelnummer="0";
	String NameStudent="0";
	String Studiengang="0";
	String Semester="0";
	String TitelArbeit="0";
	String Art="0";
	String PersonalnummerBetreuer="0";
	String NameBetreuer="0";
	String InstitutBetreuer="0";
	String PersonalnummerProf1="0";
	String PersonalnummerProf2="0";
	String NameProf="0";
	String InstitutProf="0";
	String Status="0";
	String Abgabetermin="0";
	String Anmeldedatum ="0";
	double Note =0;
	String Notetmp="0";
	String Fehlerausgabe="Daher war ein speichern leider nicht moeglich."; // auf diesem String wird der Fehler ausgegeben
	/* Das sind alle Eigenschaften die wir in die Datenbank speichern müssen.
	Falls ich eine vergessen haben sollte sagt mir bescheid
	*/
	public A(String[] c){
		//Konstuktor
		TitelArbeit =c[0];
		Art=c[1];
		Status=c[2];
		Anmeldedatum =c[4];
		Notetmp = c[5];
		NameStudent=c[6];
		Matrikelnummer=c[7];
		Studiengang=c[8];
		Semester=c[9];
		PersonalnummerProf1 =c[11];
		if(c[15]!=null){
		PersonalnummerProf2=c[15];
		}
		PersonalnummerBetreuer =c[18];
		
	
	}
	public A(){
	}
	public static boolean ueberpruefeObjekt(A a, Datenbank db){
		a.Note= Double.parseDouble(a.Notetmp); // macht die Note zu einem Double


	 /* diese Mehtode führt alle einfachen ueberpruefungen bezogen auf das
	Objekt aus */

		// #### 2 Ueberpruefung bezogen auf den Betreuer und den Gutachter ####
		if(a.PersonalnummerProf1.equals(a.PersonalnummerProf2)){
			a.Fehlerausgabe="Die beiden ausgewaehlten Gutachter duerfen nicht identisch seien";
			return false;
		}


		

		// #### 3 Ueberpruefung bezogen auf den Titel der Arbeit ####
		String[] Titelarray = db.vergleichen();  // muesste die Methode sein die aus der DB alle Titel gibt
		// String[] Titelarray = Methoden.vergleich();
		for(int i = 0; i<Titelarray.length; i++){
			if(Titelarray[i].equals(a.TitelArbeit)){
				a.Fehlerausgabe="Der ausgewaehlte Titel ist leider bereits vorhanden";
				return false;
			}
		}
		//#### 4 Ueberpruefung bezogen auf Bachelor Master Diplomarbeit #####
		if(a.Art.equals("Bachelorarbeit")){
			a.Art="BA";
		}
		if(a.Art.equals("Masterarbeit")|| a.Art.equals("Diplomarbeit")){
			a.Art="MA";
		
		}
		
		//#### 5 Ueberpruefung bezogen auf das Anmeldedatum ####
		if(a.Anmeldedatum.length() <10){
			a.Fehlerausgabe = "Das angegebene Anmeldedatum entspricht nicht der Form TT.MM.JJJJ";
			return false;
		}
		int tag 		=	Integer.parseInt(a.Anmeldedatum.substring(0,2));
		if(tag>31){
			a.Fehlerausgabe = "Das angegebene Anmeldedatum entspricht nicht der Form TT.MM.JJJJ";
			return false;
		}
	
			String punkt1 	=	a.Anmeldedatum.substring(2,3);
		if(!(punkt1.equals("."))){
			a.Fehlerausgabe = "Das angegebene Anmeldedatum entspricht nicht der Form TT.MM.JJJJ";
			return false;
		}
		int monat 		=	Integer.parseInt(a.Anmeldedatum.substring(3,5));
		if(monat >12){
			a.Fehlerausgabe = "Das angegebene Anmeldedatum entspricht nicht der Form TT.MM.JJJJ";
			return false;
		}
		String punkt2 	=	a.Anmeldedatum.substring(5,6);
		if(!(punkt2.equals("."))){
			a.Fehlerausgabe = "Das angegebene Anmeldedatum entspricht nicht der Form TT.MM.JJJJ";
			return false;
		}
		int jahr 		=	Integer.parseInt(a.Anmeldedatum.substring(6,10));
		if(jahr>2015|| jahr<1950){
				a.Fehlerausgabe = "Das angegebene Anmeldedatum entspricht nicht der Form TT.MM.JJJJ";
				return false;
		}

		//#### 6 Ueberpruefung bezogen auf die Matrikelnummer
		if(a.Matrikelnummer.length() != 6){
			a.Fehlerausgabe = "Eine angegebene Matrikelnummer muss 6 Stellen haben";
			return false;
		}

		if(a.Semester.length() != 1){
			a.Fehlerausgabe = "Das Semester muss 1-stellig sein.";
			return false;
		}
		if(!(a.Semester.equals("1")||a.Semester.equals("2")||a.Semester.equals("3")||a.Semester.equals("4")||a.Semester.equals("5")||a.Semester.equals("6")||a.Semester.equals("7")||a.Semester.equals("8")||a.Semester.equals("9"))){
			a.Fehlerausgabe = "Das angegebene Semester ist keine gueltige Zahl";
			return false;


		}

		// wenn alle ueberpruefungen erfogreich durchlaufen werden dann passt das Objekt und es wird true geliefert
		return true;
	}




//#	public static String speichernObjekt(String[] c,Datenbank db){ // gibt true zurück wenn objekt gespeichert werden konnte da ohne Fehler
	public static String hinzufuegenAbschlussarbeit(String[] c,Datenbank db){ // gibt true zurück wenn objekt gespeichert werden konnte da ohne Fehler
		A a = new A(c);
		printA(a);
		if(ueberpruefeObjekt(a,db)){
			if(db.hinzufuegenAbschlussarbeit(a)==true){
			
			a = null; // Objekt wird freigegen da in die DB uebenommen.
			return null;//#"Speichern war erfolgreich";
			}
			a.Fehlerausgabe ="auf Datenbank schreiben hat nicht funktioniert";
		}
	
		return a.Fehlerausgabe;
	}

	public static String aendernAbschlussarbeit(String[] c, String alterTitel, Datenbank db) {
		db.aendernAbschlussarbeit(new A(c), alterTitel); return null;
	/*# */
	}
	
	public static String loeschenAbschlussarbeit(String titel, Datenbank db) {
		db.loeschenAbschlussarbeit(titel); return null; 
	}
	/* #*/

	public static void printA(A a){
		System.out.println("Matrikelnummer= "+ a.Matrikelnummer);
		System.out.println("NameStudent= "+ a.NameStudent);
		System.out.println("Studiengang= "+ a.Studiengang);
		System.out.println("Semester= "+ a.Semester);
		System.out.println("TitelArbeit= "+ a.TitelArbeit);
		System.out.println("Status= "+ a.Status);
		System.out.println("Abgabetermin= "+ a.Abgabetermin);
		System.out.println("Anmeldedatum= "+ a.Anmeldedatum);
		System.out.println("Note= "+ a.Note);
		System.out.println("Art= "+ a.Art);
		System.out.println("PersonalnummerBetreuer= "+ a.PersonalnummerBetreuer);
		System.out.println("NameBetreuer= "+ a.NameBetreuer);
		System.out.println("PersonalnummerProf = "+ a.InstitutBetreuer);
		System.out.println("PersonalnummerProf = "+ a.PersonalnummerProf1);
		System.out.println("NameProf = "+ a.NameProf);
	}
}
