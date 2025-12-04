package service;

import java.sql.*;

public class TableCreator {

    public static void createTables(Connection con) throws SQLException {   
        Statement stmt;
        stmt = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS FILM (" +
                     "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "GENRE INTEGER NOT NULL, " +
                     "TITLE TEXT NOT NULL, " +
                     "SYNOPSIS TEXT DEFAULT '-', " +
                     "DIRECTOR TEXT NOT NULL, " +
                     "DURATION INTEGER NOT NULL, " +
                     "AVERAGE_RATING REAL DEFAULT 0, " +
                     "RELEASE_YEAR INTEGER DEFAULT 0, " +
                     "POSTER TEXT" +
                     ");";
        stmt.executeUpdate(sql);

        sql="CREATE TABLE IF NOT EXISTS ACCOUNT ("+
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            "EMAIL TEXT(100) NOT NULL UNIQUE,"+
            "PASSWORD TEXT(100) NOT NULL"+
            ");";
        stmt.executeUpdate(sql);

        sql="CREATE TABLE IF NOT EXISTS PROFILE ("+
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            "NAME TEXT(50) NOT NULL UNIQUE,"+
            "ACCOUNT_ID INTEGER,"+
            "NEVER_LOG_IN INTEGER DEFAULT (1) NOT NULL,"+
            "CONSTRAINT PROFILE_ACCOUNT_FK FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT(ID)"+
            ");";
        stmt.executeUpdate(sql);

        sql="CREATE TABLE IF NOT EXISTS REVIEW ("+
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            "RATING INTEGER NOT NULL,"+
            "TEXT TEXT(500),"+
            "STATUS INTEGER DEFAULT (1) NOT NULL,"+
            "CREATION_DATE DATETIME NOT NULL,"+
            "PROFILE_ID INTEGER NOT NULL,"+
            "FILM_ID INTEGER NOT NULL,"+
            "CONSTRAINT REVIEW_USUARIO_FK FOREIGN KEY (PROFILE_ID) REFERENCES PROFILE(ID),"+
            "CONSTRAINT REVIEW_FILM_FK FOREIGN KEY (FILM_ID) REFERENCES FILM(ID)"+
            ");";
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
