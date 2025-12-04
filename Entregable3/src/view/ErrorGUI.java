package view;

import controller.Controllers;
import java.awt.*;
import javax.swing.*;

public class ErrorGUI extends JDialog {

    private static final long serialVersionUID = 1L;

    public ErrorGUI(String message, Controllers controller) {

        super((Frame) null, "Error", true); // modal sin ventana padre

        setSize(400, 180);
        setLocationRelativeTo(null);  // centrar en pantalla
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(panel);

        JLabel lblMessage = new JLabel("<html><center>" + message + "</center></html>");
        lblMessage.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMessage, BorderLayout.CENTER);

        JButton btnOk = new JButton("OK");
        btnOk.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnOk.setFocusPainted(false);

        btnOk.addActionListener(e -> {
            dispose();
            controller.showLogin();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnOk);
        panel.add(bottomPanel, BorderLayout.SOUTH);
    }
}
