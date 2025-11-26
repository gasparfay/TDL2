package view;

import controller.Controllers;
import exceptions.LoginException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Controllers controller;

    public LoginGUI(Controllers controller) {
        this.controller = controller;

        // Configuración de la ventana
        setTitle("Bienvendio a la Plataforma de Streaming");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Configuracion del panel principio 
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // Panel central
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBackground(Color.WHITE);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel izquierdo
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);

        ImageIcon img = new ImageIcon(getClass().getResource("/media/perrito.png"));

        JLabel lblImagen = new JLabel(img);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(lblImagen, BorderLayout.CENTER);

        centerPanel.add(imagePanel);

        // Panel derecho
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        centerPanel.add(formContainer);
            
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
            
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
            
        // Formulario de email
        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        formPanel.add(emailLabel, c);
            
        emailField = new JTextField(20);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        formPanel.add(emailField, c);
            
        // Formulario de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.2;
        formPanel.add(passwordLabel, c);
            
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        formPanel.add(passwordField, c);
            
        // Boton ingresar
        loginButton = new JButton("Ingresar");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 17));
        loginButton.setBackground(new Color(0, 140, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
            
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 1;
        formPanel.add(loginButton, c);
            
        // Texto "¿Aún no sos usuario?"
        JLabel noUserLabel = new JLabel("¿Aún no sos usuario?");
        noUserLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        noUserLabel.setHorizontalAlignment(SwingConstants.CENTER); 

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;          
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL; // 
        formPanel.add(noUserLabel, c);

        c.gridwidth = 1;
            
        // Boton registrarse
        registerButton = new JButton("Registrate");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 140, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
            
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        formPanel.add(registerButton, c);
            
        // Agregar formulario al contenedor
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        formContainer.add(formPanel, gbc);

        // Listeners
        loginButton.addActionListener(e -> {
            String email = getEmail();
            String password = new String(getPassword());

            try {
                this.controller.handleLogin(this, email, password);
                mostrarMensaje("¡Inicio de sesión exitoso!");
                dispose();
                // controller.abrirVentanaPrincipal();
            } catch (LoginException e1) {
                showError("Error: " + e1.getMessage());
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // controller.openRegisterWindow();
            }
        });
    }

    // Getters de campos
    public String getEmail() {
        return emailField.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void showError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
