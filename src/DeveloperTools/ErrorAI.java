package DeveloperTools;

import ConnectKSource.*;


public class ErrorAI extends Player
{
    public int makeMove(Board board, Chip color){
        return 1/0;
    }
    
    public void acceptEndGame(Board board, Chip myColor, Chip winningColor){
        int errorInt = 1/0;
    }
}