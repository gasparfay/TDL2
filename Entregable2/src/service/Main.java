package service;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
	    Connection con = MyConnection.getConnection(); 
		MyConnection.disconnect();
    }
}   
