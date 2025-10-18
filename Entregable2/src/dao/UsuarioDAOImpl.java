package TDL2.Entregable2.dao;

import database.DBManager;
import model.DatosPersonales;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void registrarUsuario(Usuario usuario) {
        // Implementación con PreparedStatement para insertar un usuario
        // Primero se insertan los datos personales, se obtiene el ID y luego se inserta el usuario.
    }

    @Override
    public List<Usuario> listarTodosLosUsuarios() {
        // Implementación con PreparedStatement y ResultSet para obtener todos los usuarios
        // Se debe hacer un JOIN para traer también los datos personales.
    }

    @Override
    public Usuario validarUsuario(String nombreUsuario, String contrasenia) {
        // Implementación para validar las credenciales de un usuario.
    }
}