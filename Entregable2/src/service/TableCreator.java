package service;

import java.sql.*;

public class TableCreator {

    public static void createTables(Connection con) throws SQLException {   
        Statement stmt;
        stmt = con.createStatement();
        String sql=  "CREATE TABLE IF NOT EXISTS PELICULA ("+
                     "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                     "GENERO TEXT(1) NOT NULL,"+
                     "TITULO TEXT(100) NOT NULL,"+
                     "RESUMEN TEXT(500),"+
                     "DIRECTOR TEXT(100) NOT NULL,"+
                     "DURACION REAL NOT NULL"+
                     ");";
        stmt.executeUpdate(sql);


        sql="CREATE TABLE IF NOT EXISTS RESENIA ("+
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            "CALIFICACION INTEGER NOT NULL,"+
            "COMENTARIO TEXT(500),"+
            "APROBADO INTEGER DEFAULT (1) NOT NULL,"+
            "FECHA_HORA DATETIME NOT NULL,"+
            "ID_USUARIO INTEGER NOT NULL,"+
            "ID_PELICULA INTEGER NOT NULL,"+
            "CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),"+
            "CONSTRAINT RESENIA_PELICULA_FK FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)"+
            ");";
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
