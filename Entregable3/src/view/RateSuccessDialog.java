package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RateSuccessDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JButton btnContinue;

    public RateSuccessDialog(JFrame parent) {
        super(parent, "Información", true); // Modal
        setSize(420, 210);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // =================================================================================
        // 1. HEADER
        // =================================================================================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        mainPanel.add(header, BorderLayout.NORTH);

        // =================================================================================
        // 2. MENSAJE CENTRAL
        // =================================================================================
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);

        JLabel lblMsg = new JLabel(
            "<html><div style='text-align:center;'>Se registró correctamente su Calificación.<br>Muchas gracias.</div></html>"
        );

        lblMsg.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblMsg.setForeground(new Color(70, 70, 70));

        center.add(lblMsg);
        mainPanel.add(center, BorderLayout.CENTER);

        // =================================================================================
        // 3. BOTÓN CONTINUAR
        // =================================================================================
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        bottom.setBackground(Color.WHITE);

        btnContinue = new JButton("Continuar");
        btnContinue.setPreferredSize(new Dimension(120, 32));
        btnContinue.setBackground(Color.WHITE);
        btnContinue.setForeground(Color.DARK_GRAY);
        btnContinue.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnContinue.setFocusPainted(false);

        bottom.add(btnContinue);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        // Cerrar al tocar continuar
       // btnContinue.addActionListener(e -> dispose());
    }

    public JButton getBtnContinue() {
        return btnContinue;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RateSuccessDialog dialog = new RateSuccessDialog(null);
            dialog.setVisible(true);
        });

}
}