public class PS5Game extends Game{
    public PS5Game(long game_id, String name, String platform, String genre, double price, int stock) {
        super(game_id, name, platform, genre, price, stock);
    }
    @Override
    public String getPlatform(){
        return "PS5";
    }
}
