package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MenuWindowGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tableFilms;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnLogout;
    private JButton btnSortTitle;
    private JButton btnSortGenre;

    public MenuWindowGUI() {
        // Configuración de ventana
        setTitle("Plataforma de Streaming - Bienvenida");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // =================================================================================
        // 1. BARRA SUPERIOR
        // =================================================================================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));

        // -- Panel IZQUIERDO: Usuario --
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        userPanel.setBackground(Color.WHITE);
        JLabel lblUser = new JLabel("Juan Perez");
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUser.setForeground(Color.DARK_GRAY);
        userPanel.add(lblUser);

        // -- Panel DERECHO: Buscador + Salir --
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionsPanel.setBackground(Color.WHITE);

        txtSearch = new JTextField(20);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        btnSearch = new JButton("🔍");
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnSearch.setPreferredSize(new Dimension(40, 28));

        btnLogout = new JButton("Salir");
        btnLogout.setBackground(new Color(220, 53, 69)); 
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setPreferredSize(new Dimension(80, 28));

        actionsPanel.add(txtSearch);
        actionsPanel.add(btnSearch);
        actionsPanel.add(btnLogout);

        topPanel.add(userPanel, BorderLayout.WEST);
        topPanel.add(actionsPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // =================================================================================
        // 2. CENTRO
        // =================================================================================
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        // -- Header Títulos --
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel lblTitulo = new JLabel("Películas Recomendadas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(33, 37, 41));

        JLabel lblSub = new JLabel("Selecciona una película para calificarla.", SwingConstants.CENTER);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSub.setForeground(Color.GRAY);

        headerPanel.add(lblTitulo);
        headerPanel.add(lblSub);

        // -- Botones de Ordenamiento (Arriba de la tabla) --
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        sortPanel.setBackground(Color.WHITE);
        sortPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JLabel lblSort = new JLabel("Ordenar por:");
        lblSort.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        btnSortTitle = new JButton("Título ↕");
        styleSortButton(btnSortTitle);

        btnSortGenre = new JButton("Género ↕");
        styleSortButton(btnSortGenre);

        sortPanel.add(lblSort);
        sortPanel.add(btnSortTitle);
        sortPanel.add(btnSortGenre);

        JPanel northCenterPanel = new JPanel(new BorderLayout());
        northCenterPanel.setBackground(Color.WHITE);
        northCenterPanel.add(headerPanel, BorderLayout.NORTH);
        northCenterPanel.add(sortPanel, BorderLayout.SOUTH);
        
        centerContainer.add(northCenterPanel, BorderLayout.NORTH);

        // -- TABLA --
        String[] columnas = {"Poster", "Título", "Género", "Resumen"};
        
        // Datos de ejemplo para que veas cómo queda el texto
        Object[][] datos = {
            {null, "Ejemplo 1", "Drama", "Resumen...", "Calificar"},
            {null, "Ejemplo 2", "Acción", "Resumen...", "Calificar"}
        }; 

        tableModel = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nada es editable
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return ImageIcon.class;
                return Object.class;
            }
        };

        tableFilms = new JTable(tableModel);
        tableFilms.setRowHeight(100);
        tableFilms.setSelectionBackground(new Color(232, 240, 254));
        tableFilms.setSelectionForeground(Color.BLACK);
        tableFilms.setShowVerticalLines(false);
        tableFilms.setGridColor(new Color(230, 230, 230));
        tableFilms.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        tableFilms.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tableFilms.getTableHeader().setBackground(Color.WHITE);
        tableFilms.getTableHeader().setForeground(Color.DARK_GRAY);

        // Ajuste visual de columnas
        tableFilms.getColumnModel().getColumn(0).setPreferredWidth(80);
        tableFilms.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableFilms.getColumnModel().getColumn(3).setPreferredWidth(300);
        tableFilms.getColumnModel().getColumn(4).setPreferredWidth(100);

        JScrollPane scroll = new JScrollPane(tableFilms);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));

        centerContainer.add(scroll, BorderLayout.CENTER);
        mainPanel.add(centerContainer, BorderLayout.CENTER);
    }

    private void styleSortButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Getters
    public JTable getTableFilms() { return tableFilms; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnSearch() { return btnSearch; }
    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnSortTitle() { return btnSortTitle; }
    public JButton getBtnSortGenre() { return btnSortGenre; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {}
            new MenuWindowGUI().setVisible(true);
        });
    }
}