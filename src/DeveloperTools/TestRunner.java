package DeveloperTools;

import ConnectKSource.*;

public class TestRunner
{
    public static void main(String[] args)
    {
        System.out.print("\f");
        Game g = new Game(new HumanPlayer("Jake"),new RandomAI(), 6,7,4,false);
        Player p = g.playGame();
        System.out.println(g);
        System.out.println(p);
    }
}