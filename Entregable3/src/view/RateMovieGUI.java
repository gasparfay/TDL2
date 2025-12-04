package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RateMovieGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes para el Controlador
    private JComboBox<String> comboRating;
    private JTextArea txtReview;
    private JButton btnSave;
    private JButton btnCancel;

    public RateMovieGUI(String movieTitle) {
        setTitle("Calificar Película");
        setSize(500, 400); // Tamaño compacto
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // No cierra la app, solo esta ventana
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // =================================================================================
        // 1. HEADER
        // =================================================================================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        JLabel lblTitle = new JLabel("Calificar: " + movieTitle);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setForeground(new Color(33, 37, 41));
        
        JLabel lblSub = new JLabel("Comparte tu opinión con la comunidad.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);

        topPanel.add(lblTitle, BorderLayout.NORTH);
        topPanel.add(lblSub, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // =================================================================================
        // 2. FORMULARIO (Centro)
        // =================================================================================
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // -- Selector de Puntaje --
        JLabel lblRating = new JLabel("Puntuación (1 al 5):");
        lblRating.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblRating.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] scores = {"Seleccionar...", "⭐ 1 - Mala", "⭐⭐ 2 - Regular", "⭐⭐⭐ 3 - Buena", "⭐⭐⭐⭐ 4 - Muy Buena", "⭐⭐⭐⭐⭐ 5 - Excelente"};
        comboRating = new JComboBox<>(scores);
        comboRating.setMaximumSize(new Dimension(1000, 35));
        comboRating.setBackground(Color.WHITE);
        comboRating.setFont(new Font("SansSerif", Font.PLAIN, 13));
        comboRating.setAlignmentX(Component.LEFT_ALIGNMENT);

        // -- Área de Reseña --
        JLabel lblReview = new JLabel("Tu Reseña:");
        lblReview.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblReview.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtReview = new JTextArea(5, 20);
        txtReview.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtReview.setLineWrap(true);
        txtReview.setWrapStyleWord(true);
        txtReview.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Scroll con borde gris fino
        JScrollPane scrollReview = new JScrollPane(txtReview);
        scrollReview.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        scrollReview.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Agregamos con espaciadores
        formPanel.add(lblRating);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(comboRating);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblReview);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(scrollReview);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // =================================================================================
        // 3. BOTONES (Sur)
        // =================================================================================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        bottomPanel.setBackground(new Color(250, 250, 250));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));

        btnCancel = new JButton("Cancelar");
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnCancel.setFocusPainted(false);
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSave = new JButton("Guardar Reseña");
        btnSave.setBackground(new Color(40, 167, 69)); // Verde para guardar
        btnSave.setForeground(Color.WHITE);
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSave.setPreferredSize(new Dimension(140, 35));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(btnCancel);
        bottomPanel.add(btnSave);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // --- GETTERS PARA EL CONTROLADOR ---
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnCancel() { return btnCancel; }
    public JComboBox<String> getComboRating() { return comboRating; }
    public JTextArea getTxtReview() { return txtReview; }

}