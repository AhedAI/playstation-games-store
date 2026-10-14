import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class GameDAO {
     private Connection connection;

    public GameDAO() {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try {
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Connected to Supabase successfully!");
        }
        catch (SQLException e){
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
    public void getAllGames() {

        String sql = "SELECT * FROM games";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + result.getInt("game_id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Platform: " + result.getString("platform"));
                System.out.println("Genre: " + result.getString("genre"));
                System.out.println("Price: $" + result.getDouble("price"));
                System.out.println("Stock: " + result.getInt("stock"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getAllGamesForGUI() {
        String sql = "SELECT * FROM games";

        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet searchGameForGUI(long gameId) {
        String sql = "SELECT * FROM games WHERE game_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);//(prepareStatement) it can send parmiter without problem
            statement.setLong(1, gameId);

            return statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void addGame(Game game) {

        String sql = "INSERT INTO games (name, platform, genre, price, stock) VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, game.getName());
            statement.setString(2, game.getPlatform());
            statement.setString(3, game.getGenre());
            statement.setDouble(4, game.getPrice());
            statement.setInt(5, game.getStock());


            statement.executeUpdate();

            System.out.println("Game added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateGame(Game game) {

        String sql = "UPDATE games SET name = ?, platform = ?, genre = ?, price = ?, stock = ? WHERE game_id = ?";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, game.getName());
            statement.setString(2, game.getPlatform());
            statement.setString(3, game.getGenre());
            statement.setDouble(4, game.getPrice());

            // stock نوعه Integer
            statement.setInt(5, game.getStock());

            // game_id نوعه BIGINT
            statement.setLong(6, game.getGame_id());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Game updated successfully!");
            } else {
                System.out.println("Game not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteGame(long gameId) {

        String sql = "DELETE FROM games WHERE game_id = ?";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setLong(1, gameId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Game deleted successfully!");
            } else {
                System.out.println("Game not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchGame(long gameId) {

        String sql = "SELECT * FROM games WHERE game_id = ?";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setLong(1, gameId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println("\n===== GAME FOUND =====");

                System.out.println("ID: "
                        + result.getLong("game_id"));

                System.out.println("Name: "
                        + result.getString("name"));

                System.out.println("Platform: "
                        + result.getString("platform"));

                System.out.println("Genre: "
                        + result.getString("genre"));

                System.out.println("Price: $"
                        + result.getDouble("price"));

                System.out.println("Stock: "
                        + result.getInt("stock"));

            } else {

                System.out.println("Game not found!");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
