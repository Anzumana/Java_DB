import java.sql.*;

public class Datenbank {
	public static Connection con;
	public static int Anzahlarbeiten = 0;

		public Datenbank(){

			verbinden();
		}

		public static void verbinden(){
			Connection con = null;
			// Connection String fuer MySql-DB
			//JDBC URL : jdbc:mysql://<host>:<port3306>/<database>
			String url = "jdbc:mysql://127.0.0.1/Abschlussarbeiten";

			try{
				// Treiber dynamisch laden
	  			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	  		// Connection holen
				con = DriverManager.getConnection (url, "Anzumana", "anzumana");
			}catch(ClassNotFoundException ce){
				ce.printStackTrace();
				System.exit(1);
			}catch(InstantiationException ie){
				ie.printStackTrace();
				System.exit(1);
			}catch(IllegalAccessException ae){
				ae.printStackTrace();
				System.exit(1);
			}catch(SQLException e){
				e.printStackTrace();
			}
		System.out.println("Verbindung wurde hergestellt");

		}

		public static void datenbankanlegen(){
			String url = "jdbc:mysql://127.0.0.1/Abschlussarbeiten";
			try{
				// Treiber dynamisch laden
	  			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	  		// Connection holen
				con = DriverManager.getConnection (url, "Anzumana", "anzumana");
			}catch(ClassNotFoundException ce){
				ce.printStackTrace();
				System.exit(1);
			}catch(InstantiationException ie){
				ie.printStackTrace();
				System.exit(1);
			}catch(IllegalAccessException ae){
				ae.printStackTrace();
				System.exit(1);
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				// Statement generieren
				Statement stmt = con.createStatement();
				String go = "use Abschlussarbeiten";
				String TabelleStudienangebot = " create table Studienangebot(Studiengang varchar(32) not null primary key,index(Studiengang));";
				String TabelleStudent = "create table Student(Matrikelnummer varchar(10) not null primary key,index(Matrikelnummer),Name varchar(20) not null,Semester varchar(2) not null,Studiengang varchar(32) not null);";
				String TabelleBetreuer = "create table Betreuer(Personalid varchar(10) not null primary key,index(Personalid),Name varchar(20) not null,Institut varchar(50) not null);";
				String TabelleProfessor = "create table Professor(Personalnummer varchar(10) not null primary key,index (Personalnummer),Name varchar(20) not null,Institut varchar(50) not null,Titel varchar(32) not null);";
				String TabelleStatus= "create table Status(Statusbezeichnung varchar(32) not null primary key,index (Statusbezeichnung));";
				String Tabelle6 = "create table Bachelorarbeit(Titel varchar(200) not null primary key,index (Titel),Matrikelnummer varchar(10) not null,Betreuer varchar(10) not null,Professor1 varchar(10) not null,Anmeldedatum varchar(12) not null,Status varchar(30) not null,Note varchar(3) not null);";
				String Tabelle7 = "create table MasterDiplomarbeit(Titel varchar(200) not null primary key,index (Titel),Matrikelnummer varchar(10) not null,Betreuer varchar(10) not null,Professor1 varchar(10) not null,Professor2 varchar(10) not null,Anmeldedatum varchar(12) not null,Status varchar(30) not null,Note varchar(3) not null);";

				// Abfragen ausfuehren
//				stmt.executeUpdate(go);
				stmt.executeUpdate(TabelleStudienangebot);
				stmt.executeUpdate(TabelleStudent);
				stmt.executeUpdate(TabelleBetreuer);
				stmt.executeUpdate(TabelleProfessor);
				stmt.executeUpdate(TabelleStatus);
				stmt.executeUpdate(Tabelle6);
				stmt.executeUpdate(Tabelle7);

				String profsanlegen = "insert into Professor values('1111','Kratz','Ana','Prof.Dr.')";
				String betreueranlegen = "insert into Betreuer values('1112','Liebezeit','Ana')";
				String studienganganlegen = "insert into Studiengang values('Wima')";
				String statusanlegen = "insert into Status values('angemeldet')";

				stmt.executeUpdate(profsanlegen);
				stmt.executeUpdate(betreueranlegen);
				stmt.executeUpdate(studienganganlegen);
				stmt.executeUpdate(statusanlegen);


			}catch(SQLException e){
				System.err.println("Conn-Exception: " + e);
				System.err.println("SQLState: " + e.getSQLState());
				System.err.println("VendorError: " + e.getErrorCode());
			}
		}

		public static int AnzahlArbeit(){
	        try{
	            //zaehlt Bachelorarbeiten
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Titel from Bachelorarbeit;";
	            // Anweisung ausfuehren
	            ResultSet rs1 = stmt.executeQuery(select1);

	            while(rs1.next()){
	                Anzahlarbeiten++;
	            }

	            //zaehlt MasterDiplomarbeit
	            // Statement generieren
	            String select2 = "select Titel from MasterDiplomarbeit ;";
	            // Anweisung ausfuehren
	            ResultSet rs2 = stmt.executeQuery(select2);

	            while(rs2.next()){
	                Anzahlarbeiten++;
	            }

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Anzahlarbeiten;

	    }

		public static int Anzahlstudiengaenge(){
	        int Resultat = 0;
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select count(*) from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	                Resultat =rs.getInt(1);

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static int AnzahlStatus(){
	        int Resultat = 0;
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select count(*) from Status;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	                Resultat =rs.getInt(1);

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static int AnzahlProf(){
	        int Resultat = 0;
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select count(*) from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	                Resultat =rs.getInt(1);

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static int AnzahlBetreuer(){
	        int Resultat = 0;
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select count(*) from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	                Resultat =rs.getInt(1);

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static int AnzahlStudent(){
	        int Resultat = 0;
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select count(*) from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	                Resultat =rs.getInt(1);

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static String[] vergleichen(){

	        String[] Resultat = new String[Anzahlarbeiten];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Titel from Bachelorarbeit;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select1);

	            int i=0;
	            while(rs.next()){
	                Resultat[i] = rs.getString("Titel");
	                i++;
	            }
	            String select2 = "select Titel from MasterDiplomarbeit;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select2);

	            while(rs1.next()){
	                Resultat[i] = rs1.getString("Titel");
	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;
	    }

		public static String[] studiengaenge(){
	        String[] Resultat = new String[Anzahlstudiengaenge()];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Studiengang from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            int i=0;
	            while(rs.next()){
	                Resultat[i] = rs.getString("Studiengang");
	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static String[] status(){
	        String[] Resultat = new String[AnzahlStatus()];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Statusbezeichnung from Status;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            int i=0;
	            while(rs.next()){
	                Resultat[i] = rs.getString("Statusbezeichnung");
	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static String[] betreuer(){
	        String[] Resultat = new String[AnzahlBetreuer()];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Name from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            int i=0;
	            while(rs.next()){
	                Resultat[i] = rs.getString("Name");
	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static String[] prof(){
	        String[] Resultat = new String[AnzahlProf()];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Name from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            int i=0;
	            while(rs.next()){
	                Resultat[i] = rs.getString("Name");
	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }

		public static String[][] datenBetreuer(){
	        String[][] Resultat = new String[AnzahlBetreuer()][3];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String Personalid = "select Personalid from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(Personalid);

	            String Name = "select Name from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs2 = stmt.executeQuery(Name);

	            String Institut = "select Institut from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs3 = stmt.executeQuery(Institut);

	            int i=0;
	            while(rs1.next()){
	                Resultat[i][0] = rs1.getString("Personalid");
	                Resultat[i][1] = rs2.getString("Name");
	                Resultat[i][2] = rs3.getString("Institut");

	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;
	    }

		public static String[][] datenProf(){
	        String[][] Resultat = new String[AnzahlProf()][4];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String Personalnummer = "select Personalnummer from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(Personalnummer);

	            String Name = "select Name from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs2 = stmt.executeQuery(Name);

	            String Institut = "select Institut from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs3 = stmt.executeQuery(Institut);

	            String Titel = "select Titel from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs4 = stmt.executeQuery(Titel);

	            int i=0;
	            while(rs1.next()){
	                Resultat[i][0] = rs1.getString("Personalnummer");
	                Resultat[i][1] = rs2.getString("Name");
	                Resultat[i][2] = rs3.getString("Institut");
	                Resultat[i][3] = rs4.getString("Titel");

	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;
	    }

		public static String[][] datenStudent(){
	        String[][] Resultat = new String[AnzahlStudent()][4];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String Matrikelnummer = "select Matrikelnummer from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(Matrikelnummer);

	            String Name = "select Name from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs2 = stmt.executeQuery(Name);

	            String Semester = "select Semester from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs3 = stmt.executeQuery(Semester);

	            String Studiengang = "select Studiengang from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs4 = stmt.executeQuery(Studiengang);

	            int i=0;
	            while(rs1.next()){
	                Resultat[i][0] = rs1.getString("Matrikelnummer");
	                Resultat[i][1] = rs2.getString("Name");
	                Resultat[i][2] = rs3.getString("Semester");
	                Resultat[i][3] = rs4.getString("Studiengang");

	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;
	    }

	    public static String[] datenListe(int zeile){

	        String[] Resultat = new String[8];
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Titel from Bachelorarbeit, MasterDiplomarbeit;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            int i=1;
	            while(rs.next()){

	                if(zeile==i){
	                    Resultat[0] = rs.getString("Titel");
	                    Resultat[1] = rs.getString("Matrikelnummer");
	                    Resultat[2] = rs.getString("Personalid");
	                    Resultat[3] = rs.getString("Personalnummer");
	                    Resultat[4] = rs.getString("Personalnummer");
	                    Resultat[5] = rs.getString("Anmeldedatum");
	                    Resultat[6] = rs.getString("Status");
	                    Resultat[7] = rs.getString("Note");
	                }
	                i++;
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }


	    //Daten hinzufuegen
	    public static void hinzufuegenStatus(String s){
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Status values('" + s  + "') ;";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	    }

	    public static void hinzufuegenStudiengang(String s){
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Studienangebot values('" + s  + "') ;";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	    }

	    public static void hinzufuegenProfessor(String[] daten) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Professor values('" + daten[0]  + "', '"+daten[1]+ "', '" +daten[2]+ "', '" + daten[3]+ "');";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	    }

	    public static void hinzufuegenBetreuer(String[] daten) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Betreuer values('" + daten[0]  + "', '"+daten[1]+ "', '" +daten[2]+ "');";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	    }

	    //!!!!!!!!!!!!!!Obect festlegen, wenn Anzu Name der Objektklasse festgelegt hat
	    //legt auch Student an
	    public static void hinzufuegenAbschlussarbeit(A o){
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();

	            //Unterscheidung ob Uebergebene Arbeit Bachelorarbeit oder Master/Diplomarbeit
	            if(o.Art.equals("BA")){
	                String hinzufuegenarbeit = "insert into Bacherlorarbeit values('" + o.TitelArbeit  + "', '"+o.Matrikelnummer+ "', '" +o.PersonalnummerBetreuer+ "', '"+o.PersonalnummerProf1+ "', '" +o.Anmeldedatum+"', '"+o.Status+ "', '" +o.Note+ "');";
	                // Anweisung ausfuehren
	                int rs = stmt.executeUpdate(hinzufuegenarbeit);

	                //zaehlt anzahl Abschlussarbeiten hoch
	                Anzahlarbeiten ++;
	            }
	            else{
	                String hinzufuegenarbeit = "insert into MasterDiplomarbeit values('" + o.TitelArbeit  + "', '"+o.Matrikelnummer+ "', '" +o.PersonalnummerBetreuer+ "', '"+o.PersonalnummerProf1+ "', '" + "', '"+o.PersonalnummerProf2+ "', '" +o.Anmeldedatum+"', '"+o.Status+ "', '" +o.Note+ "');";
	                // Anweisung ausfuehren
	                int rs = stmt.executeUpdate(hinzufuegenarbeit);

	                //zaehlt anzahl Abschlussarbeiten hoch
	                Anzahlarbeiten ++;
	            }

	            //Student anlegen
	            String hinzufuegenstudent = "insert into Student values('" + o.Matrikelnummer  + "', '"+o.NameStudent+ "', '" +o.Semester+ "', '"+o.Studiengang+ "');";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegenstudent);
	            System.out.println(rs + "Datensaetze betroffen");


	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	    }

	    //loeschen von Daten
	    public static boolean loeschenStatus(String s) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Statusbezeichnung from Status;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Statusbezeichnung").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Status where Statusbezeichnung =" + s + ";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public static boolean loeschenStudiengang(String s) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Studiengang from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Studiengang").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Studienangebot where Studiengang =" + s + ";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public static boolean loeschenBetreuer(String s) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalid from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalid").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Betreuer where Personalid =" + s + ";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public static boolean loeschenProfessor(String s) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalnummer from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalnummer").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Professor where Personalnummer =" + s + ";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    //noch zu bearbeiten
	    public static boolean loeschenAbschlussarbeit(String s){
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String s1;

	                String auswahl = "select Matrikelnummer from Bachelorarbeit where Titel="+s+";";
	                String loeschen = "delete from Bachelorarbeit where Titel = "+ s +";";
	                // Anweisung ausfuehren
	                ResultSet rs2 = stmt.executeQuery(auswahl);
	                s1 = rs2.getString("Matrikelnummer");
	                if(s1 != null){
	                	int rs = stmt.executeUpdate(loeschen);
	                	//zaehlt anzahl Abschlussarbeiten runter
	                    Anzahlarbeiten --;
	                }else{
	                	String auswahl2 = "select Matrikelnummer MasterDiplomarbeit where Titel="+s+";";
	                    String loeschen2 = "delete from MasterDiplomarbeit where Titel = "+ s +";";
	                    // Anweisung ausfuehren
	                    ResultSet rs3 = stmt.executeQuery(auswahl2);
	                    s1 = rs2.getString("Matrikelnummer");
	                    int rs4 = stmt.executeUpdate(loeschen2);
	                  //zaehlt anzahl Abschlussarbeiten runter
	                    Anzahlarbeiten --;


	                }

	            String select = "select count(*) from Bachelorarbeit,MasterDiplomarbeit where Matrikelnummer="+s1+";";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            int a = rs1.getInt(1);
	                if(a==0){
	                    // Statement generieren
	                    String delete = "delete from Student where Matrikelnummer =" + s1 + ";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs5 = stmt.executeUpdate(delete);
	                    System.out.println(rs5 + "Datensaetze betroffen");
	                    return true;
	                }

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    //Stammdaten aendern
	    public static boolean aendernStatusbezeichnung(String alt, String neu) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Statusbezeichnung from Status;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Statusbezeichnung").equals(alt)){

	                    // Statement generieren
	                    String delete = "update Status set Statusbezeichnung = " + neu + " where Statusbezeichnung = "+ alt +";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public static boolean aendernStudiengangbezeichnung(String alt, String neu) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Studiengang from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Studiengang").equals(alt)){

	                    // Statement generieren
	                    String delete = "update Studienangebot set Studiengang = " + neu + " where Studiengang = "+ alt +";";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	        }

	    public static boolean aendernProfessor(String personalnummer, String[] neueDaten) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalnummer from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalnummer").equals(personalnummer)){

	                    if(neueDaten[0]==null){
	                        String neu = "update Professor set Personalnummer = "+ neueDaten[0]+"where Personalnummer = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[1]==null){
	                        String neu = "update Professor set Name = "+ neueDaten[1]+"where Personalnummer = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[2]==null){
	                        String neu = "update Professor set Institut = "+ neueDaten[2]+"where Personalnummer = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[3]==null){
	                        String neu = "update Professor set Titel = "+ neueDaten[3]+"where Personalnummer = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }

	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public static boolean aendernBetreuer(String personalnummer, String[] neueDaten) {
	        try{
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalid from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalid").equals(personalnummer)){

	                    if(neueDaten[0]==null){
	                        String neu = "update Betreuer set Personalid = "+ neueDaten[0]+"where Personalid = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[1]==null){
	                        String neu = "update Betreuer set Name = "+ neueDaten[1]+"where Personalid = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[2]==null){
	                        String neu = "update Betreuer set Institut = "+ neueDaten[2]+"where Personalid = "+personalnummer+";";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                }
	            }
	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }




	}



