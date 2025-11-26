package view;
import java.awt.*;
import javax.swing.*;

public class WelcomeGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public WelcomeGUI() {

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
