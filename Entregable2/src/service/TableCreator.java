package service;

import java.sql.*;

public class TableCreator {

    public static void createTables(Connection con) throws SQLException {   
        Statement stmt;
        stmt = con.createStatement();
        String sql=  "CREATE TABLE IF NOT EXISTS FILM ("+
                     "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                     "GENRE TEXT(1) NOT NULL,"+
                     "TITLE TEXT(100) NOT NULL,"+
                     "SINOPSIS TEXT(500),"+
                     "DIRECTOR TEXT(100) NOT NULL,"+
                     "DURATION REAL NOT NULL"+
                     ");";
        stmt.executeUpdate(sql);


        sql="CREATE TABLE IF NOT EXISTS REVIEW ("+
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            "RATING INTEGER NOT NULL,"+
            "TEXT TEXT(500),"+
            "STATUS INTEGER DEFAULT (1) NOT NULL,"+
            "CREATION_DATE DATETIME NOT NULL,"+
            "ID_USUARIO INTEGER NOT NULL,"+
            "ID_FILM INTEGER NOT NULL,"+
            "CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),"+
            "CONSTRAINT RESENIA_FILM_FK FOREIGN KEY (ID_FILM) REFERENCES FILM(ID)"+
            ");";
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
