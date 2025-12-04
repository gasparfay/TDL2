package view;

import controller.Controllers;
import exceptions.RegisterException;
import java.awt.*;
import javax.swing.*;

public class RegisterGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controllers controller;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterGUI(Controllers controller) {
        this.controller = controller;

        setTitle("Plataforma de Streaming - Registración");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        JLabel lblTitulo = new JLabel("Plataforma de Streaming - Registración", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        //Boton volver
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnVolver = new JButton("← Volver");
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(220, 220, 220));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(120, 35));

        btnVolver.addActionListener(e -> {
            dispose();
            controller.showLogin();
        });

        topPanel.add(btnVolver);
        mainPanel.add(topPanel, BorderLayout.WEST);

        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        mainPanel.add(formContainer, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        formContainer.add(lblEmail, c);

        emailField = new JTextField(22);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        emailField.setPreferredSize(new Dimension(350, 40));
        c.gridx = 1;
        c.gridy = 0;
        formContainer.add(emailField, c);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 1;
        formContainer.add(lblPassword, c);

        passwordField = new JPasswordField(22);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordField.setPreferredSize(new Dimension(350, 40));
        c.gridx = 1;
        c.gridy = 1;
        formContainer.add(passwordField, c);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 40, 10));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        registerButton = new JButton("Registrar");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        registerButton.setBackground(new Color(0, 144, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(200, 45));
        bottomPanel.add(registerButton);

        registerButton.addActionListener(e -> {
            String email = getEmail();
            String password = new String(getPassword());

            try {
                controller.handleRegister(this, email, password);
                mostrarMensaje("Usuario registrado con éxito!");
                dispose();
                controller.showLogin();
            } catch (RegisterException e1) {
                mostrarMensaje("Error: " + e1.getMessage());
            }
        });
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public JButton getRegistrarButton() {
        return registerButton;
    }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
