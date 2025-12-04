package view;

import controller.Controllers;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.*;


public class MovieInfoGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton btnContinue;

    public MovieInfoGUI(Controllers controller,Film film) {
        //Configuracion de ventana 
        setTitle("Detalle de Película");
        setSize(480, 450); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // Encabezado
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 20));

        JLabel lblHeader = new JLabel("Resultado de la Búsqueda");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblHeader.setForeground(Color.GRAY);
        topPanel.add(lblHeader);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Panel central
        JPanel centerPanel = new JPanel(new BorderLayout(10, 0)); 
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Panel derecha (Ahora es el CENTRO)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // Título
        JLabel lblTitle = new JLabel(film.getTitle());
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(33, 37, 41));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Subtítulo (Año, Género, Rating)
        String metaInfo = String.format("<html><span style='color:#666666'>%d</span> &nbsp;|&nbsp; <span style='color:#007ACC'>%s</span> &nbsp;|&nbsp; <b>⭐ %s</b></html>", 
            film.getReleaseYear(), 
            film.getGenre(), 
            film.getAverageRating()
        );
        JLabel lblMeta = new JLabel(metaInfo);
        lblMeta.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblMeta.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Separador
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); 
        sep.setForeground(new Color(230, 230, 230));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Sinopsis Label
        JLabel lblPlot = new JLabel("Sinopsis:");
        lblPlot.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblPlot.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Sinopsis Texto
        JTextArea txtSynopsis = new JTextArea(film.getSynopsis());
        txtSynopsis.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtSynopsis.setForeground(Color.DARK_GRAY);
        txtSynopsis.setLineWrap(true);
        txtSynopsis.setWrapStyleWord(true);
        txtSynopsis.setEditable(false);
        txtSynopsis.setOpaque(false);
        txtSynopsis.setBorder(null);
        txtSynopsis.setAlignmentX(Component.LEFT_ALIGNMENT); 
        txtSynopsis.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150)); 

        // Agregamos elementos al panel vertical
        infoPanel.add(lblTitle);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblMeta);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(sep);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(lblPlot);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(txtSynopsis);
        infoPanel.add(Box.createVerticalGlue()); 

        centerPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //Botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        bottomPanel.setBackground(new Color(250, 250, 250)); // Fondo gris sutil footer
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));

        // Botón continuar
        btnContinue = new JButton("Continuar");
        btnContinue.setBackground(Color.WHITE);
        btnContinue.setForeground(Color.DARK_GRAY);
        btnContinue.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnContinue.setFocusPainted(false);
        btnContinue.setPreferredSize(new Dimension(100, 35));
        btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanel.add(btnContinue);

        btnContinue.addActionListener(e -> {
            dispose();
        });


        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnContinue() { return btnContinue; }
}