public class Game {
    private long game_id;
    private  String name;
    private String platform;
    private String genre;
    private double price;
    private  int stock;

    public Game(String name,String platform,String genre, double price, int stock) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
        this.platform=platform;
    }

    public Game(long game_id, String name, String platform, String genre, double price, int stock) {
        this.game_id = game_id;
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
    }

    //Setter
    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    //Getter

    public long getGame_id() {
        return game_id;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "ID: " + game_id +
                "\nName: " + name +
                "\nPlatform: " + getPlatform() +
                "\nGenre: " + genre +
                "\nPrice: $" + price +
                "\nStock: " + stock;
    }

}



