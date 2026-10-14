import java.util.Scanner;

public class GameApp {

    private GameDAO gameDAO;
    private Scanner scanner;

    public GameApp() {
        gameDAO = new GameDAO();
        scanner = new Scanner(System.in);
    }

    public void start() {

        int choice;

        do {
            System.out.println("\n===== PLAYSTATION GAMES STORE =====");
            System.out.println("1. Add Game");
            System.out.println("2. Display All Games");
            System.out.println("3. Update Game");
            System.out.println("4. Delete Game");
            System.out.println("5. Search Game");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter game name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter platform (PS4/PS5): ");
                    String newPlatform = scanner.nextLine();

                    System.out.print("Enter genre: ");
                    String newGenre = scanner.nextLine();

                    System.out.print("Enter price: ");
                    double newPrice = scanner.nextDouble();

                    System.out.print("Enter stock: ");
                    int newStock = scanner.nextInt();

                    Game newGame; //Polymorphism

                    if (newPlatform.equalsIgnoreCase("PS4")) {//(equalsIgnoreCase) PS4 = ps4

                        newGame = new PS4Game(
                                0,
                                newName,
                                newPlatform,
                                newGenre,
                                newPrice,
                                newStock
                        );

                    } else if (newPlatform.equalsIgnoreCase("PS5")) {

                        newGame = new PS5Game(
                                0,
                                newName,
                                newPlatform,
                                newGenre,
                                newPrice,
                                newStock
                        );

                    }
                    else {

                        System.out.println("Invalid platform!");
                        break;
                    }

                    gameDAO.addGame(newGame);

                    break;


                case 2:

                    gameDAO.getAllGames();

                    break;


                case 3:

                    System.out.print("Enter Game ID: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Enter new game name: ");
                    String updateName = scanner.nextLine();

                    System.out.print("Enter new platform (PS4/PS5): ");
                    String updatePlatform = scanner.nextLine();

                    System.out.print("Enter new genre: ");
                    String updateGenre = scanner.nextLine();

                    System.out.print("Enter new price: ");
                    double updatePrice = scanner.nextDouble();

                    System.out.print("Enter new stock: ");
                    int updateStock = scanner.nextInt();

                    Game updateGame;

                    if (updatePlatform.equalsIgnoreCase("PS4")) {

                        updateGame = new PS4Game(
                                id,
                                updateName,
                                updatePlatform,
                                updateGenre,
                                updatePrice,
                                updateStock
                        );

                    } else if (updatePlatform.equalsIgnoreCase("PS5")) {

                        updateGame = new PS5Game(
                                id,
                                updateName,
                                updatePlatform,
                                updateGenre,
                                updatePrice,
                                updateStock
                        );

                    } else {

                        System.out.println("Invalid platform!");
                        break;
                    }

                    gameDAO.updateGame(updateGame);

                    break;

                case 4:

                    System.out.print("Enter Game ID to delete: ");
                    long deleteId = scanner.nextLong();

                    gameDAO.deleteGame(deleteId);

                    break;
                case 5:

                    System.out.print("Enter Game ID to search: ");
                    long searchId = scanner.nextLong();

                    gameDAO.searchGame(searchId);

                    break;
                    case 6:

                    System.out.println("Goodbye!");

                    break;


                default:

                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }
}