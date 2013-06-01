package ConnectKSource;

public abstract class Player{
    //returns the integer corresponding to the column of the move
    public abstract int makeMove(Board board, Chip color);
    
    //allows a Player AI to learn from mistakes
    public void acceptEndGame(Board board, Chip myColor, Chip winningColor){}
}
