package view;

import controller.Controllers;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import model.*;

public class MenuGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes principales de la vista
    private JTable tableFilms;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnLogout;
    private JButton btnSortTitle;
    private JButton btnSortGenre;
    private JButton[] btnCalificarArray;
    private List<Film> films;
    private Controllers controller;
    private JPanel buttonsPanel;
    private Set<Film> disabledFilms;

    public MenuGUI(Controllers controller, List<Film> films, String userName) {
        this.films = films;
        this.controller = controller;
        this.disabledFilms = new HashSet<>();

        // Configuración de la ventana
        setTitle("Plataforma de Streaming - Bienvenida");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Panel superior (usuario + búsqueda + logout)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));

        // Panel usuario (izquierda)
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        userPanel.setBackground(Color.WHITE);
        JLabel lblUser = new JLabel("Hola " + userName);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUser.setForeground(Color.DARK_GRAY);
        userPanel.add(lblUser);

        // Panel acciones (derecha)
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
        btnSearch.addActionListener(e -> {
            controller.handleSearch(txtSearch.getText());
        });

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

        // Contenedor central (encabezado + tabla + botones)
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        // Encabezado (título + subtítulo)
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

        // Panel de ordenamiento
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        sortPanel.setBackground(Color.WHITE);
        sortPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JLabel lblSort = new JLabel("Ordenar por:");
        lblSort.setFont(new Font("SansSerif", Font.BOLD, 12));

        btnSortTitle = new JButton("Título ↕");
        styleSortButton(btnSortTitle);
        btnSortTitle.addActionListener(e -> {
            try {
                this.films = controller.handleSort(this.films, "Title");
                reloadTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage());
            }
        });

        btnSortGenre = new JButton("Género ↕");
        styleSortButton(btnSortGenre);

        btnSortGenre.addActionListener(e -> {
            try {
                this.films = controller.handleSort(this.films, "Genre");
                reloadTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage());
            }
        });

        sortPanel.add(lblSort);
        sortPanel.add(btnSortTitle);
        sortPanel.add(btnSortGenre);

        JPanel northCenterPanel = new JPanel(new BorderLayout());
        northCenterPanel.setBackground(Color.WHITE);
        northCenterPanel.add(headerPanel, BorderLayout.NORTH);
        northCenterPanel.add(sortPanel, BorderLayout.SOUTH);

        // Definición de columnas y datos de la tabla
        String[] columnas = {"Poster", "Título", "Género", "Resumen"};

        Object[][] datos = new Object[Math.min(films.size(), 10)][4];

        for (int i = 0; i < Math.min(films.size(), 10); i++) {
            Film film = films.get(i);
            datos[i][0] = film.getPosterImage();
            datos[i][1] = "<html>" + film.getTitle() + "<br><br></html>";
            datos[i][2] = "<html>" + (film.getGenre() != null ? mapGenres(film.getGenre()) : "") + "<br><br></html>";
            datos[i][3] = "<html>" + film.getSynopsis() + "<br><br></html>";
        }

        // Modelo de tabla
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

        // Tabla de películas
        tableFilms = new JTable(tableModel);
        tableFilms.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200)));
        tableFilms.setRowHeight(200);
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

        tableFilms.addMouseWheelListener(e -> {
            JScrollBar globalScrollBar = ((JScrollPane) getContentPane()).getVerticalScrollBar();
            int scrollAmount = e.getUnitsToScroll() * globalScrollBar.getUnitIncrement();
            globalScrollBar.setValue(globalScrollBar.getValue() + scrollAmount);
            e.consume();
        });

        // Panel que contiene tabla y botones a la derecha
        JPanel tableButtonPanel = new JPanel(new BorderLayout(0, 0));
        tableButtonPanel.setBackground(Color.WHITE);

        JScrollPane tableScroll = new JScrollPane(tableFilms);
        tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableButtonPanel.add(tableScroll, BorderLayout.CENTER);
        tableScroll.setWheelScrollingEnabled(false);

        // Panel de botones "Calificar"
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 0));

        buttonsPanel.addMouseWheelListener(e -> {
            JScrollBar globalScrollBar = ((JScrollPane) getContentPane()).getVerticalScrollBar();
            int scrollAmount = e.getUnitsToScroll() * globalScrollBar.getUnitIncrement();
            globalScrollBar.setValue(globalScrollBar.getValue() + scrollAmount);
            e.consume();
        });

        btnCalificarArray = new JButton[Math.min(films.size(), 10)];
        for (int i = 0; i < Math.min(films.size(), 10); i++) {
            Film film = films.get(i);
            final int filmIndex = i;

            btnCalificarArray[i] = new JButton("Calificar Película");
            btnCalificarArray[i].setBackground(new Color(0, 144, 255));
            btnCalificarArray[i].setForeground(Color.WHITE);
            btnCalificarArray[i].setFont(new Font("SansSerif", Font.BOLD, 12));
            btnCalificarArray[i].setFocusPainted(false);
            btnCalificarArray[i].setBorderPainted(false);
            btnCalificarArray[i].setMaximumSize(new Dimension(150, 28));
            btnCalificarArray[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalificarArray[i].setActionCommand(String.valueOf(filmIndex));

            if (disabledFilms.contains(film)) {
                btnCalificarArray[i].setEnabled(false);
                btnCalificarArray[i].setBackground(new Color(125, 125, 125));
            }

            btnCalificarArray[i].addActionListener(e -> {
                controller.showRateMovieGUI(film, filmIndex);
            });

            buttonsPanel.add(btnCalificarArray[i]);

            int rowHeight = tableFilms.getRowHeight();
            int buttonHeight = btnCalificarArray[i].getPreferredSize().height;
            int spacing = Math.max(0, rowHeight - buttonHeight);
            buttonsPanel.add(Box.createVerticalStrut(spacing));
        }

        buttonsPanel.add(Box.createVerticalGlue());
        tableButtonPanel.add(buttonsPanel, BorderLayout.EAST);

        // Scroll principal de contenido central
        JScrollPane mainContentScroll = new JScrollPane(tableButtonPanel);
        mainContentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainContentScroll.setBorder(BorderFactory.createEmptyBorder());
        mainContentScroll.setWheelScrollingEnabled(false);

        centerContainer.add(northCenterPanel, BorderLayout.NORTH);
        centerContainer.add(mainContentScroll, BorderLayout.CENTER);
        mainPanel.add(centerContainer, BorderLayout.CENTER);

        // Scroll general de la ventana
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

    // Deshabilita el botón asociado a una película y guarda el estado
    public void disableButton(int buttonIndex) {
        Film film = films.get(buttonIndex);
        disabledFilms.add(film);
        JButton targetButton = btnCalificarArray[buttonIndex];
        targetButton.setEnabled(false);
        targetButton.setBackground(new Color(125, 125, 125));
        targetButton.revalidate();
        targetButton.repaint();
    }

    // Recarga la tabla luego de ordenar o cambiar la lista de films
    private void reloadTable() {
        tableModel.setRowCount(0);

        for (int i = 0; i < Math.min(this.films.size(), 10); i++) {
            Film film = this.films.get(i);
            tableModel.addRow(new Object[]{
                    film.getPosterImage(),
                    "<html>" + film.getTitle() + "<br><br></html>",
                    "<html>" + (film.getGenre() != null ? mapGenres(film.getGenre()) : "") + "<br><br></html>",
                    "<html>" + film.getSynopsis() + "<br><br></html>"
            });
        }
        recreateButtonsPanel();
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
        tableModel.fireTableDataChanged();
    }

    // Reconstruye el panel de botones manteniendo el estado de deshabilitado
    private void recreateButtonsPanel() {
        buttonsPanel.removeAll();
        btnCalificarArray = new JButton[Math.min(this.films.size(), 10)];

        for (int i = 0; i < Math.min(this.films.size(), 10); i++) {
            Film film = this.films.get(i);
            final int filmIndex = i;

            JButton btn = new JButton("Calificar Película");

            btn.setBackground(new Color(0, 144, 255));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("SansSerif", Font.BOLD, 12));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setMaximumSize(new Dimension(150, 28));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setActionCommand(String.valueOf(filmIndex));

            if (disabledFilms.contains(film)) {
                btn.setEnabled(false);
                btn.setBackground(new Color(125, 125, 125));
            }

            btnCalificarArray[i] = btn;

            btnCalificarArray[i].addActionListener(e -> {
                controller.showRateMovieGUI(film, filmIndex);
            });

            buttonsPanel.add(btnCalificarArray[i]);

            int rowHeight = tableFilms.getRowHeight();
            int buttonHeight = btn.getPreferredSize().height;
            int spacing = Math.max(0, rowHeight - buttonHeight);
            buttonsPanel.add(Box.createVerticalStrut(spacing));
        }

        buttonsPanel.add(Box.createVerticalGlue());
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    // Traduce el enum Genre a un texto amigable
    private String mapGenres(Genre g) {
        if (g == null) return "";
        switch (g) {
            case ACCION: return "Acción";
            case AVENTURA: return "Aventura";
            case ANIMACION: return "Animación";
            case COMEDIA: return "Comedia";
            case CRIMEN: return "Crimen";
            case DRAMA: return "Drama";
            case FANTASIA: return "Fantasía";
            case HISTORIA: return "Historia";
            case TERROR: return "Terror";
            case MUSICA: return "Música";
            case MISTERIO: return "Misterio";
            case ROMANCE: return "Romance";
            case GUERRA: return "Guerra";
            case INFANTIL: return "Infantil";
            case DEPORTE: return "Deporte";
            case DOCUMENTAL: return "Documental";
            case CIENCIA_FICCION: return "Ciencia Ficción";
            default: return "Otro";
        }
    }

    public JTable getTableFilms() {
        return tableFilms;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnSortTitle() {
        return btnSortTitle;
    }

    public JButton getBtnSortGenre() {
        return btnSortGenre;
    }
}
