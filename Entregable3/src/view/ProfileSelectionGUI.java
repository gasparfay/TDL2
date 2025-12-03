package view;

import controller.Controllers;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Profile;

public class ProfileSelectionGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private Controllers controller;
    private JPanel profilesPanel;
    private JButton createProfileButton;

    public ProfileSelectionGUI(Controllers controller) {
        this.controller = controller;

        setTitle("Plataforma de Streaming - Elegir perfil");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        JLabel lblTitulo = new JLabel("¿Quién está viendo?");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        profilesPanel = new JPanel();
        profilesPanel.setBackground(Color.WHITE);
        profilesPanel.setLayout(new GridBagLayout());
        mainPanel.add(profilesPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        createProfileButton = new JButton("Crear perfil");
        createProfileButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        createProfileButton.setBackground(new Color(0, 144, 255));
        createProfileButton.setForeground(Color.WHITE);
        createProfileButton.setFocusPainted(false);
        createProfileButton.setPreferredSize(new Dimension(180, 40));
        bottomPanel.add(createProfileButton);

        createProfileButton.addActionListener(e -> controller.showCreateProfile(this));

        loadProfilesFromDB();
    }

    private void loadProfilesFromDB() {
        List<Profile> perfiles = controller.getProfiles();
        profilesPanel.removeAll();

        if (perfiles == null || perfiles.isEmpty()) {
            JLabel noProfiles = new JLabel("No hay perfiles creados.");
            noProfiles.setFont(new Font("SansSerif", Font.PLAIN, 18));
            profilesPanel.add(noProfiles);
        } else {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, 20, 20, 20);

            int col = 0;
            int row = 0;

            for (Profile profile : perfiles) {
                JButton profileButton = createProfileItemButton(profile.getName());

                gbc.gridx = col;
                gbc.gridy = row;
                profilesPanel.add(profileButton, gbc);

                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }
            }
        }
        profilesPanel.revalidate();
        profilesPanel.repaint();
    }

    private JButton createProfileItemButton(String name) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout());
        btn.setPreferredSize(new Dimension(180, 180));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel avatar = new JLabel(name.substring(0, 1).toUpperCase(), SwingConstants.CENTER);
        avatar.setFont(new Font("SansSerif", Font.BOLD, 40));
        avatar.setOpaque(true);
        avatar.setBackground(new Color(0, 140, 255));
        avatar.setForeground(Color.WHITE);
        avatar.setPreferredSize(new Dimension(180, 130));
        btn.add(avatar, BorderLayout.CENTER);

        JLabel lbl = new JLabel(name, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.add(lbl, BorderLayout.SOUTH);

        btn.addActionListener(e -> {
            controller.enterWithProfile(name);
            dispose(); // acá sí corresponde
        });
        return btn;
    }

    public void reloadProfiles() {
        loadProfilesFromDB();
    }
}
