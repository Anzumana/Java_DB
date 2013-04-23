
public class Test {

	public static void main(String[] args){
		Datenbank db = new Datenbank();
		A erstes = new A();
		//db.datenbankanlegen();

		db.hinzufuegenStatus("in_Bearbeitung");
		db.hinzufuegenStudiengang("WiWi");
		String[] prof = {"1113","Schweigert","AIV","Prof.Dr."};
		db.hinzufuegenProfessor(prof);
		String[] bet = {"1114","Just","AIV"};
		db.hinzufuegenBetreuer(bet);
		db.hinzufuegenAbschlussarbeit(erstes);
		int i = db.AnzahlArbeit();
		System.out.println(i);

		int j = db.AnzahlStudiengaenge();
		System.out.println(j);

		int k = db.AnzahlStatus();
		System.out.println(k);

		int l = db.AnzahlProf();
		System.out.println(l);

		int m = db.AnzahlBetreuer();
		System.out.println(m);

		int n = db.AnzahlStudent();
		System.out.println(n);

		String[] nr1 = db.vergleichen();
		for(String o: nr1){
			System.out.println(o);
		}
		String[] nr2 = db.studiengaenge();
		for(String o: nr2){
			System.out.println(o);
		}
		String[] nr3 = db.status();
		for(String o: nr3){
			System.out.println(o);
		}
		String[] nr4 = db.betreuer();
		for(String o: nr4){
			System.out.println(o);
		}
		String[] nr5 = db.prof();
		for(String o: nr5){
			System.out.println(o);
		}
		String[][] nr6 =db.datenBetreuer();
		System.out.println(nr6[0][0]);

		String[][] nr7 = db.datenProf();
		System.out.println(nr7[0][0]);

		String[][] nr8 = db.datenStudent();
		System.out.println(nr8[0][0]);

		boolean a1 = db.loeschenStatus("in_Bearbeitung");
		boolean a2 = db.loeschenStudiengang("WiWi");
		boolean a3 = db.loeschenBetreuer("1114");
		boolean a4 = db.loeschenProfessor("1113");
		//boolean a5 = db.loeschenAbschlussarbeit("Informatik Projekt");
		boolean a6 = db.aendernStatusbezeichnung("angemeldet", "anmelden");
		boolean a7 = db.aendernStudiengangbezeichnung("Wima", "WiMa");
		String[] prof1 = {"1111","Kratz","Angewandte_Ana","Prof.Dr."};
		boolean a8 = db.aendernProfessor("1111", prof1);
		String[] betreuer1 = {"1112","Ehre","OR1"};
		boolean a9 = db.aendernBetreuer("1112",betreuer1);

	}

}
