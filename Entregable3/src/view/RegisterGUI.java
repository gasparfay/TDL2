package view;

import controller.Controllers;
import exceptions.RegisterException;
import java.awt.*;
import javax.swing.*;

public class RegisterGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controllers controller;
    private JTextField nombreField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterGUI(Controllers controller) {
        this.controller = controller;

        setTitle("Plataforma de Streaming - Registración");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // Titulo
        JLabel lblTitulo = new JLabel("Plataforma de Streaming - Registración");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
        mainPanel.add(formContainer, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        // Formulario nombre
        JLabel lblNombres = new JLabel("Nombre:");
        lblNombres.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        formContainer.add(lblNombres, c);

        nombreField = new JTextField(20);
        nombreField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.8;
        formContainer.add(nombreField, c);

        // Formulario email
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.2;
        formContainer.add(lblEmail, c);

        emailField = new JTextField(20);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.8;
        formContainer.add(emailField, c);

        // Formulario password
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.2;
        formContainer.add(lblPassword, c);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.8;
        formContainer.add(passwordField, c);

        // Boton de registrar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        registerButton = new JButton("Registrar");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 144, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(180, 40));
        bottomPanel.add(registerButton);

        registerButton.addActionListener(e ->{
               String nombre = getNombre();
               String email = getEmail();
               String password = new String(getPassword());
               
               try {
               	   controller.handleRegister(this,nombre,email,password);
                   mostrarMensaje("Usuario registrado con éxito!");
                   dispose();
               	   controller.showLogin();
               }catch(RegisterException e1) {
               	    mostrarMensaje("Error: "+e1.getMessage());
               }
               
        });
    }

    
    public String getNombre() { return nombreField.getText().trim(); }
    public String getEmail() { return emailField.getText().trim(); }
    public char[] getPassword() { return passwordField.getPassword(); }
    public JButton getRegistrarButton() { return registerButton; }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}