package view;

import java.awt.*;
import javax.swing.*;

public class LoadingGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public LoadingGUI() {
        setTitle("Plataforma de Streaming - Bienvenida");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // Título
        JLabel lblTitulo = new JLabel("Bienvenido a la Plataforma de Streaming");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(Box.createVerticalGlue());

    
        // Imagen de carga
        JLabel lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(200, 200));
        lblImagen.setMaximumSize(new Dimension(200, 200));
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon img = new ImageIcon(getClass().getResource("/media/loading.gif"));
        lblImagen.setIcon(img);
        centerPanel.add(lblImagen);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Texto inferior
        JLabel lblMensaje = new JLabel("Un momento por favor......");
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblMensaje);

        centerPanel.add(Box.createVerticalGlue());
    }
}
