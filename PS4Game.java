public class PS4Game extends Game{
    public PS4Game(long game_id, String name, String platform, String genre, double price, int stock) {
        super(game_id, name, platform, genre, price, stock);
    }
    @Override
    public String getPlatform(){
        return "PS4";
    }
}
