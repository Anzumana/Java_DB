package oberflaeche;
import java.sql.*;

public class Datenbank implements inter{
	public static Connection con;
	//public static int Anzahlarbeiten = 0;

		public Datenbank(){
			verbinden();
			System.out.println("Datenbank erstellt");
		}

		public void verbinden(){
			// Connection String fuer MySql-DB
			//JDBC URL : jdbc:mysql://<host>:<port3306>/<database>
			String url = "jdbc:mysql://theseus:22456/projekt";

			try{
				// Treiber dynamisch laden
	  			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	  		// Connection holen
				con = DriverManager.getConnection (url,"root", "12345678");
				
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

		public void datenbankanlegen(){
			try{
				//Connection con = null;
				// Statement generieren
				Statement stmt = con.createStatement();
				String TabelleStudienangebot = " create table Studienangebot(Studiengang varchar(32) not null primary key,index(Studiengang));";
				String TabelleStudent = "create table Student(Matrikelnummer varchar(10) not null primary key,index(Matrikelnummer),Name varchar(20) not null,Semester varchar(10) not null,Studiengang varchar(32) not null);";
				String TabelleBetreuer = "create table Betreuer(Personalid varchar(10) not null primary key,index(Personalid),Name varchar(20) not null,Institut varchar(50) not null);";
				String TabelleProfessor = "create table Professor(Personalnummer varchar(10) not null primary key,index (Personalnummer),Name varchar(20) not null,Institut varchar(50) not null,Titel varchar(32) not null);";
				String TabelleStatus= "create table Status(Statusbezeichnung varchar(32) not null primary key,index (Statusbezeichnung));";
				String Tabelle6 = "create table Bachelorarbeit(Titel varchar(200) not null primary key,index (Titel),Matrikelnummer varchar(10) not null,Personalid varchar(10) not null,Personalnummer varchar(10) not null,Personalnummer2 varchar(10),Anmeldedatum varchar(12) not null,Status varchar(30) not null,Note varchar(3) not null);";
				String Tabelle7 = "create table MasterDiplomarbeit(Titel varchar(200) not null primary key,index (Titel),Matrikelnummer varchar(10) not null,Personalid varchar(10) not null,Personalnummer varchar(10) not null,Personalnummer2 varchar(10) not null,Anmeldedatum varchar(12) not null,Status varchar(30) not null,Note varchar(3) not null);";

				// Abfragen ausfuehren
				stmt.executeUpdate(TabelleStudienangebot);
				stmt.executeUpdate(TabelleStudent);
				stmt.executeUpdate(TabelleBetreuer);
				stmt.executeUpdate(TabelleProfessor);
				stmt.executeUpdate(TabelleStatus);
				stmt.executeUpdate(Tabelle6);
				stmt.executeUpdate(Tabelle7);

				String profsanlegen = "insert into Professor values('1111','Kratz','Ana','Prof.Dr.');";
				String betreueranlegen = "insert into Betreuer values('1112','Liebezeit','Ana');";
				String studienganganlegen = "insert into Studienangebot values('Wima');";
				String statusanlegen = "insert into Status values('angemeldet');";

				stmt.executeUpdate(profsanlegen);
				stmt.executeUpdate(betreueranlegen);
				stmt.executeUpdate(studienganganlegen);
				stmt.executeUpdate(statusanlegen);

				// Statement schliessen!
                stmt.close();

			}catch(SQLException e){
				System.err.println("Conn-Exception: " + e);
				System.err.println("SQLState: " + e.getSQLState());
				System.err.println("VendorError: " + e.getErrorCode());
			}
		}

		public int anzahlAbschlussarbeiten(){
			int Anzahlarbeiten = 0;
	        try{
	        	//Connection con = null;
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
	            String select2 = "select Titel from MasterDiplomarbeit;";
	            // Anweisung ausfuehren
	            ResultSet rs2 = stmt.executeQuery(select2);

	            while(rs2.next()){
	                Anzahlarbeiten++;
	            }
	         // ResultSet und Statement schliessen!
                rs2.close();
                stmt.close();


	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Anzahlarbeiten;

	    }

		public int AnzahlStudiengaenge(){
			int Anzahl=0;
	        try{
	        	//Connection con = null;
	            //zaehlt Studiengaenge
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Studiengang from Studienangebot;";
	            // Anweisung ausfuehren
	            ResultSet rs1 = stmt.executeQuery(select1);

	            while(rs1.next()){
	                Anzahl++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Anzahl;

	    }

		public int AnzahlStatus(){
			int Anzahl=0;
	        try{
	        	//Connection con = null;
	            //zaehlt Studiengaenge
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Statusbezeichnung from Status;";
	            // Anweisung ausfuehren
	            ResultSet rs1 = stmt.executeQuery(select1);

	            while(rs1.next()){
	                Anzahl++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Anzahl;

	    }

		public int AnzahlProf(){
			int Anzahl=0;
	        try{
	        	//Connection con = null;
	            //zaehlt Studiengaenge
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Personalnummer from Professor;";
	            // Anweisung ausfuehren
	            ResultSet rs1 = stmt.executeQuery(select1);

	            while(rs1.next()){
	                Anzahl++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Anzahl;

	    }

		public int AnzahlBetreuer(){
			int Anzahl=0;
	        try{
	        	//Connection con = null;
	            //zaehlt Studiengaenge
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Personalid from Betreuer;";
	            // Anweisung ausfuehren
	            ResultSet rs1 = stmt.executeQuery(select1);

	            while(rs1.next()){
	                Anzahl++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Anzahl;

	    }

		public int AnzahlStudent(){
			int Anzahl=0;
	        try{
	        	//Connection con = null;
	            //zaehlt Studiengaenge
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select1 = "select Matrikelnummer from Student;";
	            // Anweisung ausfuehren
	            ResultSet rs1 = stmt.executeQuery(select1);

	            while(rs1.next()){
	                Anzahl++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Anzahl;

	    }

	/*	public int Anzahlstudiengaenge(){
	        int Resultat = 0;
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select count(*) from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            String temp =rs.getString("count(*)");
	            Resultat = Integer.parseInt(temp);

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }

	        return Resultat;

	    }*/

		/*public int AnzahlStatus(){
	        int Resultat = 0;
	        try{
	        	//Connection con = null;
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

	    }*/

		/*public int AnzahlProf(){
	        int Resultat = 0;
	        try{
	        	//Connection con = null;
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

	    }*/

		/*public int AnzahlBetreuer(){
	        int Resultat = 0;
	        try{
	        	//Connection con = null;
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

	    }*/

		/*public int AnzahlStudent(){
	        int Resultat = 0;
	        try{
	        	//Connection con = null;
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

	    }*/

		public String[] vergleichen(){

	        String[] Resultat = new String[anzahlAbschlussarbeiten()];
	        try{
	        	//Connection con = null;
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
	         // ResultSet und Statement schliessen!
                rs.close();
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;
	    }

		public String[] studiengaenge(){
	        String[] Resultat = new String[AnzahlStudiengaenge()];
	        try{
	        	//Connection con = null;
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
	         // ResultSet und Statement schliessen!
                rs.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;

	    }

		public String[] status(){
	        String[] Resultat = new String[AnzahlStatus()];
	        try{
	        	//Connection con = null;
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
	         // ResultSet und Statement schliessen!
                rs.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;

	    }

		public String[] betreuer(){
	        String[] Resultat = new String[AnzahlBetreuer()];
	        try{
	        	//Connection con = null;
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
	         // ResultSet und Statement schliessen!
                rs.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;

	    }

		public String[] prof(){
	        String[] Resultat = new String[AnzahlProf()];
	        try{
	        	//Connection con = null;
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
	         // ResultSet und Statement schliessen!
                rs.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;

	    }

		public String[][] datenBetreuer(){
	        String[][] Resultat = new String[AnzahlBetreuer()][3];
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String Personalid = "select * from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(Personalid);

	            /*String Name = "select Name from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs2 = stmt.executeQuery(Name);

	            String Institut = "select Institut from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten

	            ResultSet rs3 = stmt.executeQuery(Institut);
	             */
	            int i=0;
	            int j=0;
	            while(rs1.next()){

	                Resultat[i][j] = rs1.getString("Personalid");
	                Resultat[i][j+1] = rs1.getString("Name");
	                Resultat[i][j+2] = rs1.getString("Institut");

	                i++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
		    
	        }

	        return Resultat;
	    }

		public String[][] datenProf(){
	        String[][] Resultat = new String[AnzahlProf()][4];
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String Personalnummer = "select * from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(Personalnummer);

	            /*String Name = "select Name from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs2 = stmt.executeQuery(Name);

	            String Institut = "select Institut from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs3 = stmt.executeQuery(Institut);

	            String Titel = "select Titel from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs4 = stmt.executeQuery(Titel);
				*/
	            int i=0;
	            int j=0;
	            while(rs1.next()){
	                Resultat[i][j] = rs1.getString("Personalnummer");
	                Resultat[i][j+1] = rs1.getString("Name");
	                Resultat[i][j+2] = rs1.getString("Institut");
	                Resultat[i][j+3] = rs1.getString("Titel");

	                i++;
	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;
	    }

		public String[] datenStudent(String s){
	        String[] Resultat = new String[4];
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String Matrikelnummer = "select * from Student where Matrikelnummer = '"+s+"';";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(Matrikelnummer);

	            /*String Name = "select Name from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs2 = stmt.executeQuery(Name);

	            String Semester = "select Semester from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs3 = stmt.executeQuery(Semester);

	            String Studiengang = "select Studiengang from Student;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs4 = stmt.executeQuery(Studiengang);
				*/
	            int j=0;
	            while(rs1.next()){
	                Resultat[j] = rs1.getString("Matrikelnummer");
	                Resultat[j+1] = rs1.getString("Name");
	                Resultat[j+2] = rs1.getString("Semester");
	                Resultat[j+3] = rs1.getString("Studiengang");

	            }
	         // ResultSet und Statement schliessen!
                rs1.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;
	    }

	    public String[] datenListe(int zeile){

	        String[] Resultat = new String[8];
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select * from Bachelorarbeit union select * from MasterDiplomarbeit;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs = stmt.executeQuery(select);

	            int i=1;
	            while(rs.next()){

	                if(zeile==i){
	                    Resultat[0] = rs.getString("Titel");
	                    Resultat[1] = rs.getString("Matrikelnummer");
	                    Resultat[2] = rs.getString("Personalid");
	                    Resultat[3] = rs.getString("Personalnummer");
	                    Resultat[4] = rs.getString("Personalnummer2");
	                    Resultat[5] = rs.getString("Anmeldedatum");
	                    Resultat[6] = rs.getString("Status");
	                    Resultat[7] = rs.getString("Note");
	                }
	                i++;
	            }
	         // ResultSet und Statement schliessen!
                rs.close();
                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }

	        return Resultat;

	    }


	    //Daten hinzufuegen
	    public boolean hinzufuegenStatus(String s){
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Status values('" + s  + "') ;";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");
	         // Statement schliessen!
                stmt.close();
                
                return true;

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
		    e.printStackTrace();
	        }
	        
	        return false;

	    }

	    public boolean hinzufuegenStudiengang(String s){
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Studienangebot values('" + s  + "') ;";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");
	         // Statement schliessen!
                stmt.close();
                
                return true;

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;

	    }

	    public boolean hinzufuegenProfessor(String[] daten) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Professor values('" + daten[0]  + "', '"+daten[1]+ "', '" +daten[2]+ "', '" + daten[3]+ "');";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");
	         //Statement schliessen!

                stmt.close();
                
                return true;

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        
	        return false;

	    }

	    public boolean hinzufuegenBetreuer(String[] daten) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String hinzufuegen = "insert into Betreuer values('" + daten[0]  + "', '"+daten[1]+ "', '" +daten[2]+ "');";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegen);
	            System.out.println(rs + "Datensaetze betroffen");
	         // ResultSet und Statement schliessen!

                stmt.close();
                
                return true;

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        
	        return false;
	    }

	    //!!!!!!!!!!!!!!Obect festlegen, wenn Anzu Name der Objektklasse festgelegt hat
	    //legt auch Student an
	    public boolean hinzufuegenAbschlussarbeit(A o){
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();

	            //Unterscheidung ob Uebergebene Arbeit Bachelorarbeit oder Master/Diplomarbeit
	            if(o.Art.equals("BA")){
	                String hinzufuegenarbeit = "insert into Bachelorarbeit values('" + o.TitelArbeit  + "', '"+o.Matrikelnummer+ "', '" +o.PersonalnummerBetreuer+ "', '"+o.PersonalnummerProf1+ "', '" +o.PersonalnummerProf2 + "','"+o.Anmeldedatum+"', '"+o.Status+ "', '" +o.Note+ "');";
	                // Anweisung ausfuehren
	                int rs = stmt.executeUpdate(hinzufuegenarbeit);
	            }
	            else{
	                String hinzufuegenarbeit = "insert into MasterDiplomarbeit values('" + o.TitelArbeit  + "', '"+o.Matrikelnummer+ "', '" +o.PersonalnummerBetreuer+ "', '"+o.PersonalnummerProf1+  "', '"+o.PersonalnummerProf2+ "', '" +o.Anmeldedatum+"', '"+o.Status+ "', '" +o.Note+ "');";
	                // Anweisung ausfuehren
	                int rs = stmt.executeUpdate(hinzufuegenarbeit);
	            }

	            //Student anlegen
	            String hinzufuegenstudent = "insert into Student values('" + o.Matrikelnummer  + "', '"+o.NameStudent+ "', '" +o.Semester+ "', '"+o.Studiengang+ "');";
	            // Anweisung ausfuehren
	            int rs = stmt.executeUpdate(hinzufuegenstudent);
	            System.out.println(rs + "Datensaetze betroffen");
	         // ResultSet und Statement schliessen!

                stmt.close();
                
                return true;


	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        
	        return false;

	    }
	    public boolean aendernAbschlussarbeit(A o, String s){
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();

	            //Unterscheidung ob Uebergebene Arbeit Bachelorarbeit oder Master/Diplomarbeit
	            if(o.Art.equals("BA")){
	                String hinzufuegenarbeit = "update Bachelorarbeit set Titel='" + o.TitelArbeit  + "',Matrikelnummer= '"+o.Matrikelnummer+ "',Personalid= '" +o.PersonalnummerBetreuer+ "',Personalnummer= '"+o.PersonalnummerProf1+ "',Personalnummer2= '" +o.PersonalnummerProf2 + "',Anmeldedatum='"+o.Anmeldedatum+"',Status= '"+o.Status+ "',Note= '" +o.Note+ "' where Titel='"+s+"';";
	                // Anweisung ausfuehren
	                int rs = stmt.executeUpdate(hinzufuegenarbeit);
	            }
	            else{
	                String hinzufuegenarbeit = "update MasterDiplomarbeit set Titel='" + o.TitelArbeit  + "',Matrikelnummer= '"+o.Matrikelnummer+ "',Personalid= '" +o.PersonalnummerBetreuer+ "',Personalnummer= '"+o.PersonalnummerProf1+  "',Personalnummer2= '"+o.PersonalnummerProf2+ "',Anmeldedatum= '" +o.Anmeldedatum+"',Status= '"+o.Status+ "',Note= '" +o.Note+ "' where Titel='"+s+"';";
	                // Anweisung ausfuehren
	                int rs = stmt.executeUpdate(hinzufuegenarbeit);
	            }

	            //Student anlegen
		   String ueberpruefung = "select Matrikelnummer from Student;";
		    ResultSet rs1= stmt.executeQuery(ueberpruefung);
		

		int i=1;
		  while(rs1.next()){
		  	if(rs1.getString("Matrikelnummer").equals(o.Matrikelnummer)) i=0;
		  } 
		  
	          if(i==1){	
		String hinzufuegenstudent = "insert into Student values('" + o.Matrikelnummer  + "', '"+o.NameStudent+ "', '" +o.Semester+ "', '"+o.Studiengang+ "');";
	          	  // Anweisung ausfuehren
	          	  int rs = stmt.executeUpdate(hinzufuegenstudent);
	          	  System.out.println(rs + "Datensaetze betroffen");
	        }
		 // ResultSet und Statement schliessen!

                stmt.close();
                
                return true;


	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        
	        return false;

	    
	    }
	    
	    //loeschen von Daten
	    public boolean loeschenStatus(String s) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Statusbezeichnung from Status;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Statusbezeichnung").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Status where Statusbezeichnung='" + s + "';";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public boolean loeschenStudiengang(String s) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Studiengang from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
						System.out.println("im while x881");
	                if(rs1.getString("Studiengang").equals(s)){
						System.out.println("im If z885");

	                    // Statement generieren
	                    String delete = "delete from Studienangebot where Studiengang='" + s + "';";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public boolean loeschenBetreuer(String s) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalid from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalid").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Betreuer where Personalid ='" + s + "';";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public boolean loeschenProfessor(String s) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalnummer from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalnummer").equals(s)){

	                    // Statement generieren
	                    String delete = "delete from Professor where Personalnummer ='" + s + "';";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    //noch zu bearbeiten
	    public boolean loeschenAbschlussarbeit(String s){
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String s1;

	                String auswahl = "select Matrikelnummer from Bachelorarbeit where Titel='"+s+"';";
	                String loeschen = "delete from Bachelorarbeit where Titel = '"+ s +"';";
	                // Anweisung ausfuehren
	                ResultSet rs2 = stmt.executeQuery(auswahl);
	                s1 = rs2.getString("Matrikelnummer");
	                if(s1 != null){
	                	int rs = stmt.executeUpdate(loeschen);

	                }else{
	                	String auswahl2 = "select Matrikelnummer MasterDiplomarbeit where Titel='"+s+"';";
	                    String loeschen2 = "delete from MasterDiplomarbeit where Titel = '"+ s +"';";
	                    // Anweisung ausfuehren
	                    ResultSet rs3 = stmt.executeQuery(auswahl2);
	                    s1 = rs2.getString("Matrikelnummer");
	                    int rs4 = stmt.executeUpdate(loeschen2);

	                }

	            String select = "select count(*) from Bachelorarbeit,MasterDiplomarbeit where Matrikelnummer='"+s1+"';";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            int a = rs1.getInt(1);
	            	if(a==0){
	                    // Statement generieren
	                    String delete = "delete from Student where Matrikelnummer ='" + s1 + "';";
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
	    public boolean aendernStatus(String alt, String neu) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Statusbezeichnung from Status;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Statusbezeichnung").equals(alt)){

	                    // Statement generieren
	                    String delete = "update Status set Statusbezeichnung = '" + neu + "' where Statusbezeichnung = '"+ alt +"';";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public boolean aendernStudiengangbezeichnung(String alt, String neu) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Studiengang from Studienangebot;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Studiengang").equals(alt)){

	                    // Statement generieren
	                    String delete = "update Studienangebot set Studiengang='" + neu + "' where Studiengang='"+ alt +"';";
	                    // Abfrage ausfuehren und ResultSet erhalten
	                    int rs = stmt.executeUpdate(delete);
	                    System.out.println(rs + "Datensaetze betroffen");
	                    return true;
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	        }

	    public boolean aendernProfessor(String personalnummer, String[] neueDaten) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalnummer from Professor;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalnummer").equals(personalnummer)){

	                    if(neueDaten[0]!=null){
	                        String neu = "update Professor set Personalnummer='"+ neueDaten[0]+"' where Personalnummer='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[1]!=null){
	                        String neu = "update Professor set Name='"+ neueDaten[1]+"' where Personalnummer='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[2]!=null){
	                        String neu = "update Professor set Institut='"+ neueDaten[2]+"' where Personalnummer='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[3]!=null){
	                        String neu = "update Professor set Titel='"+ neueDaten[3]+"' where Personalnummer='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }

	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	    public boolean aendernBetreuer(String personalnummer, String[] neueDaten) {
	        try{
	        	//Connection con = null;
	            // Statement generieren
	            Statement stmt = con.createStatement();
	            String select = "select Personalid from Betreuer;";
	            // Abfrage ausfuehren und ResultSet erhalten
	            ResultSet rs1 = stmt.executeQuery(select);

	            while(rs1.next()){
	                if(rs1.getString("Personalid").equals(personalnummer)){

	                    if(neueDaten[0]!=null){
	                        String neu = "update Betreuer set Personalid='"+ neueDaten[0]+"' where Personalid='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[1]!=null){
	                        String neu = "update Betreuer set Name='"+ neueDaten[1]+"' where Personalid='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                    if(neueDaten[2]!=null){
	                        String neu = "update Betreuer set Institut='"+ neueDaten[2]+"' where Personalid='"+personalnummer+"';";
	                        int rs = stmt.executeUpdate(neu);
	                        System.out.println(rs + "Datensaetze betroffen");
	                        return true;
	                    }
	                }
	            }
	         // ResultSet und Statement schliessen!

                stmt.close();

	        }catch(SQLException e){
	            System.err.println("Conn-Exception: " + e);
	            System.err.println("SQLState: " + e.getSQLState());
	            System.err.println("VendorError: " + e.getErrorCode());
	        }
	        return false;
	    }

	}
