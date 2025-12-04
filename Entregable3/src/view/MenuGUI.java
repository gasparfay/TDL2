package view;

import controller.Controllers;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import model.*;

public class MenuGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tableFilms;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnLogout;
    private JButton btnSortTitle;
    private JButton btnSortGenre;

    public MenuGUI(Controllers controller, List<Film> films, String userName) {
        // Configuración de ventana
        setTitle("Plataforma de Streaming - Bienvenida");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));

        // Panel izquierdo
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        userPanel.setBackground(Color.WHITE);
        JLabel lblUser = new JLabel(userName);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUser.setForeground(Color.DARK_GRAY);
        userPanel.add(lblUser);

        // Panel derecho
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

        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBackground(new Color(220, 53, 69));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setPreferredSize(new Dimension(120, 28));
        
        btnLogout.addActionListener(e -> {
            controller.handleLogout();
            dispose();
        }); 

        actionsPanel.add(txtSearch);
        actionsPanel.add(btnSearch);
        actionsPanel.add(btnLogout);

        topPanel.add(userPanel, BorderLayout.WEST);
        topPanel.add(actionsPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel central
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

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

        // Botones de ordenar
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

        // Tabla
        String[] columnas = {"Poster", "Título", "Género", "Resumen"};

        Object[][] datos = new Object[Math.min(films.size(), 10)][4];
        
        for (int i = 0; i < Math.min(films.size(), 10); i++) {
            Film film = films.get(i);
            datos[i][0] = film.getPosterImage();
            datos[i][1] = film.getTitle();
            datos[i][2] = film.getGenre() != null ? film.getGenre().toString() : "";
            datos[i][3] = film.getSynopsis();
        }

        tableModel = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return ImageIcon.class;
                return Object.class;
            }
        };

        tableFilms = new JTable(tableModel);
        tableFilms.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200)));
        tableFilms.setRowHeight(100);
        tableFilms.setSelectionBackground(new Color(232, 240, 254));
        tableFilms.setSelectionForeground(Color.BLACK);
        tableFilms.setShowVerticalLines(true);
        tableFilms.setShowHorizontalLines(true);
        tableFilms.setFillsViewportHeight(true);
        tableFilms.setGridColor(new Color(230, 230, 230));
        tableFilms.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableFilms.setRowSelectionAllowed(false);
        tableFilms.setColumnSelectionAllowed(false);
        tableFilms.setFocusable(false);

        tableFilms.getTableHeader().setReorderingAllowed(false);
        tableFilms.getTableHeader().setResizingAllowed(false);
        tableFilms.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tableFilms.getTableHeader().setBackground(Color.WHITE);
        tableFilms.getTableHeader().setForeground(Color.DARK_GRAY);
        tableFilms.getColumnModel().getColumn(0).setPreferredWidth(80);
        tableFilms.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableFilms.getColumnModel().getColumn(2).setPreferredWidth(120);
        tableFilms.getColumnModel().getColumn(3).setPreferredWidth(300);

        //Hacemos que al scrollear sobre la tabla se scrollee toda la ventana
        tableFilms.addMouseWheelListener(e -> {
            JScrollBar globalScrollBar = ((JScrollPane)getContentPane()).getVerticalScrollBar();
            int scrollAmount = e.getUnitsToScroll() * globalScrollBar.getUnitIncrement();
            globalScrollBar.setValue(globalScrollBar.getValue() + scrollAmount);
            e.consume();
        });

        JPanel tableButtonPanel = new JPanel(new BorderLayout(0, 0));
        tableButtonPanel.setBackground(Color.WHITE);
        
        JScrollPane tableScroll = new JScrollPane(tableFilms);
        tableScroll.getViewport().setBackground(Color.WHITE);
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        tableButtonPanel.add(tableScroll, BorderLayout.CENTER);
        tableScroll.setWheelScrollingEnabled(false);


        // Panel de los botones "Calificar"
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 0, 0));

        JButton[] btnCalificarArray = new JButton[Math.min(films.size(), 10)];
        for (int i = 0; i < Math.min(films.size(), 10); i++) {
            // Se mantiene model.Film aquí
           Film film = films.get(i);

            btnCalificarArray[i] = new JButton("Calificar Película");
            btnCalificarArray[i].setBackground(new Color(0, 144, 255));
            btnCalificarArray[i].setForeground(Color.WHITE);
            btnCalificarArray[i].setFont(new Font("SansSerif", Font.BOLD, 12));
            btnCalificarArray[i].setFocusPainted(false);
            btnCalificarArray[i].setBorderPainted(false);
            btnCalificarArray[i].setMaximumSize(new Dimension(150, 28));
            btnCalificarArray[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalificarArray[i].setActionCommand(String.valueOf(i));

            btnCalificarArray[i].addActionListener(e -> {
                System.out.println("Calificando: " + film.getTitle());
            });

            buttonsPanel.add(btnCalificarArray[i]);
            buttonsPanel.add(Box.createVerticalStrut(72)); 
        }
        buttonsPanel.add(Box.createVerticalGlue());

        tableButtonPanel.add(buttonsPanel, BorderLayout.EAST);
        
        // JScrollPane para la tabla y los botones
        JScrollPane mainContentScroll = new JScrollPane(tableButtonPanel);
        mainContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        mainContentScroll.setBorder(BorderFactory.createEmptyBorder()); 
        mainContentScroll.setWheelScrollingEnabled(false);

        centerContainer.add(northCenterPanel, BorderLayout.NORTH);
        centerContainer.add(mainContentScroll, BorderLayout.CENTER); 
        mainPanel.add(centerContainer, BorderLayout.CENTER);

        //ScrollPane de la ventana
        JScrollPane globalScroll = new JScrollPane(mainPanel);
        globalScroll.setBorder(BorderFactory.createEmptyBorder()); 
        globalScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JScrollBar verticalScrollBar = globalScroll.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);

        

        setContentPane(globalScroll);
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
}