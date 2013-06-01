package DeveloperTools;

import ConnectKSource.*;

import java.util.Scanner;
public class HumanPlayer extends Player
{
    private String name;

    public HumanPlayer(String s)
    {
        name = s;
    }

    public int makeMove(Board board, Chip color) {
        Scanner scanny = new Scanner(System.in);
        System.out.println(board);
        System.out.print(name + " Column: ");
        int col = scanny.nextInt();
        return col;
    }
   
    
    public String toString() {
        return name;
    }
}
