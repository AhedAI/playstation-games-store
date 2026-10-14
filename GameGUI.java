import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GameGUI extends JFrame {

    private JTextField nameField;
    private JTextField genreField;
    private JTextField priceField;
    private JTextField stockField;
    private JComboBox<String> platformBox;

    private JTable gameTable;
    private DefaultTableModel tableModel;

    private GameDAO gameDAO;

    public GameGUI() {

        gameDAO = new GameDAO();

        setTitle("PlayStation Games Store");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(50, 50);
        setAlwaysOnTop(true);

        createInterface();
    }

    private void createInterface() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel =
                new JLabel("PLAYSTATION GAMES STORE", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        inputPanel.add(new JLabel("Game Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Platform:"));

        platformBox = new JComboBox<>(
                new String[]{"PS4", "PS5"}
        );

        inputPanel.add(platformBox);

        inputPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        inputPanel.add(genreField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        inputPanel.add(stockField);

        mainPanel.add(inputPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton displayButton = new JButton("Display All");
        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        String[] columns = {
                "ID",
                "Name",
                "Platform",
                "Genre",
                "Price",
                "Stock"
        };

        tableModel = new DefaultTableModel(columns, 0);

        gameTable = new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(gameTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addGame());

        displayButton.addActionListener(e -> displayGames());

        searchButton.addActionListener(e -> searchGame());

        updateButton.addActionListener(e -> updateGame());

        deleteButton.addActionListener(e -> deleteGame());

        add(mainPanel);
    }

    private void addGame() {

        try {

            String name = nameField.getText();

            String platform =
                    platformBox.getSelectedItem().toString();

            String genre = genreField.getText();

            double price =
                    Double.parseDouble(priceField.getText());

            int stock =
                    Integer.parseInt(stockField.getText());

            Game game;

            if (platform.equals("PS4")) {

                game = new PS4Game(
                        0,
                        name,
                        platform,
                        genre,
                        price,
                        stock
                );

            } else {

                game = new PS5Game(
                        0,
                        name,
                        platform,
                        genre,
                        price,
                        stock
                );
            }

            gameDAO.addGame(game);

            JOptionPane.showMessageDialog(
                    this,
                    "Game added successfully!"
            );

            displayGames();

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid price and stock."
            );
        }
    }

    private void displayGames() {

        tableModel.setRowCount(0);

        try {

            var result = gameDAO.getAllGamesForGUI();

            while (result.next()) {

                Object[] row = {
                        result.getLong("game_id"),
                        result.getString("name"),
                        result.getString("platform"),
                        result.getString("genre"),
                        result.getDouble("price"),
                        result.getInt("stock")
                };

                tableModel.addRow(row);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading games."
            );

            e.printStackTrace();
        }
    }

    private void searchGame() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Game ID:"
                );

        if (input == null) {
            return;
        }

        try {

            long id = Long.parseLong(input);

            var result = gameDAO.searchGameForGUI(id);

            if (result != null && result.next()) {

                nameField.setText(
                        result.getString("name")
                );

                platformBox.setSelectedItem(
                        result.getString("platform")
                );

                genreField.setText(
                        result.getString("genre")
                );

                priceField.setText(
                        String.valueOf(
                                result.getDouble("price")
                        )
                );

                stockField.setText(
                        String.valueOf(
                                result.getInt("stock")
                        )
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Game not found!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Game ID."
            );
        }
    }

    private void updateGame() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Game ID to update:"
                );

        if (input == null) {
            return;
        }

        try {

            long id = Long.parseLong(input);

            String name = nameField.getText();

            String platform =
                    platformBox.getSelectedItem().toString();

            String genre = genreField.getText();

            double price =
                    Double.parseDouble(
                            priceField.getText()
                    );

            int stock =
                    Integer.parseInt(
                            stockField.getText()
                    );

            Game game;

            if (platform.equals("PS4")) {

                game = new PS4Game(
                        id,
                        name,
                        platform,
                        genre,
                        price,
                        stock
                );

            } else {

                game = new PS5Game(
                        id,
                        name,
                        platform,
                        genre,
                        price,
                        stock
                );
            }

            gameDAO.updateGame(game);

            JOptionPane.showMessageDialog(
                    this,
                    "Game updated successfully!"
            );

            displayGames();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data."
            );
        }
    }

    private void deleteGame() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Game ID to delete:"
                );

        if (input == null) {
            return;
        }

        try {

            long id = Long.parseLong(input);

            gameDAO.deleteGame(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Game deleted successfully!"
            );

            displayGames();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Game ID."
            );
        }
    }

    private void clearFields() {

        nameField.setText("");
        genreField.setText("");
        priceField.setText("");
        stockField.setText("");

        platformBox.setSelectedIndex(0);
    }
}

