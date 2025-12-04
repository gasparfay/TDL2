package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.Film;
import model.Genre;


public class MovieInfoGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Botones para que el Controlador los escuche
    private JButton btnAdd;
    private JButton btnCancel;

    public MovieInfoGUI(Film film) {
        // Configuración de ventana (Tipo Pop-up)
        setTitle("Detalle de Película");
        setSize(600, 450);
        setLocationRelativeTo(null); // Centrado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // =================================================================================
        // 1. ENCABEZADO
        // =================================================================================
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 20));

        JLabel lblHeader = new JLabel("Resultado de la Búsqueda");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblHeader.setForeground(Color.GRAY);
        topPanel.add(lblHeader);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // =================================================================================
        // 2. CENTRO (Poster + Info)
        // =================================================================================
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0));
centerPanel.setBackground(Color.WHITE);
centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

// -- IZQUIERDA: Placeholder del Poster --
JLabel lblPoster = new JLabel("SIN IMAGEN", SwingConstants.CENTER);
lblPoster.setPreferredSize(new Dimension(140, 200));
lblPoster.setOpaque(true);
lblPoster.setBackground(new Color(240, 240, 240));
lblPoster.setForeground(Color.GRAY);
lblPoster.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

centerPanel.add(lblPoster, BorderLayout.WEST);

// -- DERECHA: Datos de la Película --
JPanel infoPanel = new JPanel();
infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
infoPanel.setBackground(Color.WHITE);
infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ Alinear a la izquierda

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
sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // ✅ Ajustado para llenar ancho
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
txtSynopsis.setAlignmentX(Component.LEFT_ALIGNMENT); // ✅ Alinear a la izquierda
txtSynopsis.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // ✅ Limitar altura

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
infoPanel.add(Box.createVerticalGlue()); // ✅ Relleno al final

centerPanel.add(infoPanel, BorderLayout.CENTER);
mainPanel.add(centerPanel, BorderLayout.CENTER);

        // =================================================================================
        // 3. SUR (Botones de Acción)
        // =================================================================================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        bottomPanel.setBackground(new Color(250, 250, 250)); // Fondo gris sutil footer
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));

        // Botón Cancelar
        btnCancel = new JButton("Continuar");
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnCancel.setFocusPainted(false);
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(btnCancel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // --- GETTERS PARA EL CONTROLADOR (Comunicación) ---
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnCancel() { return btnCancel; }
}
