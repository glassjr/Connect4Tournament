import DeveloperTools.*;
import ConnectKSource.*;

public class TournamentPlayer
{
    private Player player;
    private String name;
    private int id;
    private int wins;
    private int losses;
    private int draws;

    public TournamentPlayer(int i) {
        player = new RandomAI();
        //name is the memory location
        id = i;
        wins = 0;
        losses = 0;
        draws = 0;
    }

    public TournamentPlayer(Player p, String s, int i) {
        player = p;
        name = s;
        id = i;
        wins = 0;
        losses = 0;
        draws = 0;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getID() {
        return id;
    }
    
    public void addWin(){
        wins++;
    }
    
    public void addLoss(){
        losses++;
    }
    
    public void addDraw(){
        draws++;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public int getPoints() {
        return wins*2 + draws;
    }
    
    public String toString(){
        return name + ": " + wins + " - " + losses + " - " + draws + "\t| Points: " + getPoints();
    }
}
