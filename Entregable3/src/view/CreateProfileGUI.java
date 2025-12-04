package view;

import controller.Controllers;
import exceptions.ProfileException;
import java.awt.*;
import javax.swing.*;

public class CreateProfileGUI extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField nombreField;
    private JButton createButton;
    private Controllers controller;

    public CreateProfileGUI(JFrame parent, Controllers controller) {
        super(parent, "Crear nuevo perfil", true); 
        this.controller = controller;

        setSize(350, 180);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);

        // Panel principal
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        add(formPanel, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre del perfil:");
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(lblNombre, c);

        nombreField = new JTextField(18);
        nombreField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(nombreField, c);

        // Botón
        createButton = new JButton("Crear");
        createButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        createButton.setBackground(new Color(0, 144, 255));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(createButton);
        add(bottomPanel, BorderLayout.SOUTH);

        createButton.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            try {
                controller.handleCreateProfile(nombre);  
                dispose(); 
            }catch (ProfileException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
