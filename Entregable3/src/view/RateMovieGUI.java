package view;

import controller.Controllers;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import model.*;

public class RateMovieGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes para el Controlador
    private JComboBox<String> comboRating;
    private JTextArea txtReview;
    private JButton btnSave;
    private JButton btnCancel;

    public RateMovieGUI(Controllers controller,Film film,Profile profile, int index) {
        setTitle("Calificar Película");
        setSize(500, 400); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        //Header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        JLabel lblTitle = new JLabel("Calificar: " + film.getTitle());
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setForeground(new Color(33, 37, 41));
        
        JLabel lblSub = new JLabel("Comparte tu opinión con la comunidad.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);

        topPanel.add(lblTitle, BorderLayout.NORTH);
        topPanel.add(lblSub, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));


        JLabel lblRating = new JLabel("Puntuación (1 al 5):");
        lblRating.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblRating.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] scores = {"Seleccionar...", "⭐ 1 - Mala", "⭐⭐ 2 - Regular", "⭐⭐⭐ 3 - Buena", "⭐⭐⭐⭐ 4 - Muy Buena", "⭐⭐⭐⭐⭐ 5 - Excelente"};
        comboRating = new JComboBox<>(scores);
        comboRating.setMaximumSize(new Dimension(1000, 35));
        comboRating.setBackground(Color.WHITE);
        comboRating.setFont(new Font("SansSerif", Font.PLAIN, 13));
        comboRating.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblReview = new JLabel("Tu Reseña:");
        lblReview.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblReview.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtReview = new JTextArea(5, 20);
        txtReview.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtReview.setLineWrap(true);
        txtReview.setWrapStyleWord(true);
        txtReview.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scrollReview = new JScrollPane(txtReview);
        scrollReview.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        scrollReview.setAlignmentX(Component.LEFT_ALIGNMENT);


        formPanel.add(lblRating);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(comboRating);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblReview);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(scrollReview);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        //Botones
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

        btnCancel.addActionListener(e -> dispose());

        btnSave = new JButton("Guardar Reseña");
        btnSave.setBackground(new Color(0, 144, 255)); 
        btnSave.setForeground(Color.WHITE);
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSave.setPreferredSize(new Dimension(140, 35));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSave.addActionListener(e -> {
            int selectedIndex = comboRating.getSelectedIndex();
            String reviewText = txtReview.getText().trim();
            if (selectedIndex == 0) {
                JOptionPane.showMessageDialog(RateMovieGUI.this, "Por favor, selecciona una puntuación.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int rating = selectedIndex;
            controller.handleSaveReview(rating, reviewText,profile,film,index);
            JOptionPane.showMessageDialog(RateMovieGUI.this, "Se registro correcatamente tu calificacion. Muchas gracias", "Reseña Guardada", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        bottomPanel.add(btnCancel);
        bottomPanel.add(btnSave);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }




    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnCancel() { return btnCancel; }
    public JComboBox<String> getComboRating() { return comboRating; }
    public JTextArea getTxtReview() { return txtReview; }

}