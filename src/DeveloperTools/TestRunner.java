package DeveloperTools;

import ConnectKSource.*;

public class TestRunner
{
    public static void main(String[] args)
    {
        Game g = new Game(new HumanPlayer("Black"),new RandomAI(), 6,7,4,false);
        Player p = g.playGame();
        System.out.println(g);
        System.out.println(p);
    }
}