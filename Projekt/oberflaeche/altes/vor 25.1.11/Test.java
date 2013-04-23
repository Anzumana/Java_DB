
public class Test {

	public static void main(String[] args){
		Datenbank.verbinden();

		Datenbank db = new Datenbank();
		A erstes = new A();
		db.datenbankanlegen();

		db.hinzufuegenStatus("in_Bearbeitung");
		db.hinzufuegenStudiengang("WiWi");
		String[] prof = {"1113","Schweigert","AIV","Prof.Dr."};
		db.hinzufuegenProfessor(prof);
		String[] bet = {"1114","Just","AIV"};
		db.hinzufuegenBetreuer(bet);
		db.hinzufuegenAbschlussarbeit(erstes);
		int i = db.AnzahlArbeit();
		int j = db.Anzahlstudiengaenge();
		int k = db.AnzahlStatus();
		int l = db.AnzahlProf();
		int m = db.AnzahlBetreuer();
		int n = db.AnzahlStudent();
		String[] nr1 = db.vergleichen();
		String[] nr2 = db.studiengaenge();
		String[] nr3 = db.status();
		String[] nr4 = db.betreuer();
		String[] nr5 = db.prof();
		String[][] nr6 =db.datenBetreuer();
		String[][] nr7 = db.datenProf();
		String[][] nr8 = db.datenStudent();
		boolean a1 = db.loeschenStatus("in_Bearbeitung");
		boolean a2 = db.loeschenStudiengang("WiWi");
		boolean a3 = db.loeschenBetreuer("1114");
		boolean a4 = db.loeschenProfessor("1113");
		boolean a5 = db.loeschenAbschlussarbeit("Informatik Projekt");
		boolean a6 = db.aendernStatusbezeichnung("angemeldet", "anmelden");
		boolean a7 = db.aendernStudiengangbezeichnung("Wima", "WiMa");
		String[] prof1 = {"1111","Kratz","Angewandte_Ana","Prof.Dr."};
		boolean a8 = db.aendernProfessor("1111", prof1);
		String[] betreuer1 = {"1112","Ehre","OR1"};
		boolean a9 = db.aendernBetreuer("1112",betreuer1);

	}

}
