import java.sql.*;

public class DBCon{
	public static void main(String[] args){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			throw new RuntimeException("Mysql driver not found" + e);
		}

		Connection con = null;

		try{
			String url = "jdbc:mysql://127.0.0.1/myecho";
		
			con = DriverManager.getConnection(url,"Anzumana","anzumana");
			System.out.println("Success in connect");
		}
		catch(SQLException e){
			System.err.println("Conn-Exception :" +e);
			System.err.println("SQLState: :" +e.getSQLState());
			System.err.println("VendorError :" +e.getErrorCode());
			
		}
		finally {
			if(con != null){
				try{ con.close();}
				catch(Exception e){}
			}
		}
	}
}
