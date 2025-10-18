package TDL2.Entregable2.dao;

import model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void registrarUsuario(Usuario usuario);
    List<Usuario> listarTodosLosUsuarios();
    Usuario validarUsuario(String nombreUsuario, String contrasenia);
    // ... otros métodos que puedas necesitar
}
