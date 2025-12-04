package view;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import model.*;
import controller.*;

public class WelcomeGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public WelcomeGUI(Controllers controllers, java.util.List<Film> filmsToDisplay) {

        setTitle("Plataforma de Streaming - Bienvenida");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);
    }
}
