public class A {
	String Matrikelnummer;
	String NameStudent;
	String Studiengang;
	String Semester;
	String TitelArbeit ="Informatik Projekt";
	String Art;
	String PersonalnummerBetreuer;
	String NameBetreuer ="DummyBetreuer";
	String InstitutBetreuer;
	String PersonalnummerProf1;
	String PersonalnummerProf2;
	String NameProf ="DummyProf";
	String InstitutProf;
	String Status;
	int Abgabetermin; // datum wird im Format JahrMontagTag eingegeben z.B. 20110124 fuer 24 januar 2011 
	int Anmeldedatum;
	double Note = 1.3;
	/* Das sind alle Eigenschaften die wir in die Datenbank speichern müssen
	Falls ich eine vergessen haben sollte sagt mir bescheid
	*/
	public A(){
	//Konastruktor
	// habe den leer gelassen koennten aber auch was rein schreiben

	}
	public static boolean ueberpruefeObjekt(A a){



	 /* diese Mehtode führt alle einfachen ueberpruefungen bezogen auf das
	Objekt aus */




	// #### 1 Ueberpruefung bezogen auf die Note ######
		if(a.Note <= 0){
			System.out.println("Die eingegebene Note muss groesser als 0 sein");
			return false;
		}
		if(a.Note > 5){
			System.out.println("Die eingegebene Note muss kleiner als 5 sein");
			return false;
		}
		if(a.Note <= 5 && a.Note >0){
			String bc= Double.toString(a.Note);
			System.out.println("Der zu pruefenden Noten String ist : " + bc);
			if(bc.length() >3){ // muss 3 sein da wir den Punkt mit in den String schreiben
				System.out.println("Die eingegebene Note besitzt mehr als 2 Stellen.");
				return false;
			}
			if(!(bc.endsWith("0") ||bc.endsWith("3") || bc.endsWith("7"))){
				System.out.println("Die eingegebene Note endet nicht mit 0,3 oder 7");
				return false;
			}
		}
//####  2 Ueberpruefung bezogen auf den Anfang und das Ende der Arbeit ####

		if(a.Anmeldedatum <= a.Abgabetermin){
			System.out.println(" Das Anmeldedatum muss echt groesser als der Abgabetermin sein");
			return false;
		}

		// #### 3 Ueberpruefung bezogen auf den Betreuer und den Gutachter ####
		if(a.NameBetreuer.equals(a.NameProf)){
			System.out.println("Der Betreuer und der Gutachter duerfen nicht identisch sein");
			return false;
		}



		// #### 4 Ueberpruefung bezogen auf den Titel der Arbeit ####
		String[] Titelarray = {"Anzumana","Gabriel","Lisanne","Nicole"};  // muesste die Methode sein die aus der DB alle Titel gibt
		// String[] Titelarray = Methoden.vergleich();
		for(int i = 0; i<Titelarray.length; i++){
			if(Titelarray[i].equals(a.TitelArbeit)){
				System.out.println("Der ausgewaehlte Titel ist leider bereits vorhanden");
				return false;
			}
		}



		// wenn alle ueberpruefungen erfogreich durchlaufen werden dann passt das Objekt und es wird true geliefert
		//methode uebernehme das objekt komplett in die Datenbank 
		return true;
	}






	public static boolean speichernObjekt(A a){ // gibt true zurück wenn objekt gespeichert werden konnte da ohne Fehler
		if(ueberpruefeObjekt(a)){ // Objekt wird nur freigegeben, wenn pruefung erfolgreich war
		// hier muss noch das speichern rein mit der dazugehoerigen Methode
			a = null;
			return true;
		}
		return false; // falls preufung des Inhaltes des Objektes auf Fehler zeigt
	}





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
	public String toString(){
		return "Matrikelnummer=  "+ Matrikelnummer +"\nNameStudent= "+ NameStudent;
	}


}
