import java.sql.*;
public class MyConnection {
	private static Connection con = null;
	
	private MyConnection() {}
	
	static {	
		try {
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (SQLException e) {
			System.out.print("Error en la conexión con la BD: " + e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
	public static void disconnect() {
		//Cierre de conexión
		try {
			con.close();
		} catch (SQLException e) {
			System.out.print("Error en la desconexión con la BD: " + e.getMessage());
		}
	}
}
