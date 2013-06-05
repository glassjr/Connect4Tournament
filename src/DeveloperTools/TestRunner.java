package DeveloperTools;

import ConnectKSource.*;

public class TestRunner
{
    public static void humanGame(){
        Game g = new Game(new HumanPlayer("Black"), new HumanPlayer("Red"), 6, 7, 4, false);
        //Alternatively, Game g = new Game(new HumanPlayer("Black"), new HumanPlayer("Red"));
        //Or even: Game g = new Game();
        Player winner = g.playGame();
        System.out.println(g);
        System.out.println(winner);
        //Alternatively, playGamePrintWinner(g);
    }
        
    private static void playGamePrintWinner(Game g){
        Player winner = g.playGame();
        System.out.println(g);
        System.out.println(winner);
    }
    
    public static void randomGame()
    {
        Game g = new Game(new RandomAI(), new RandomAI());
        playGamePrintWinner(g);
    }
    
    public static void humanRandomGame(){
        Game g = new Game(new HumanPlayer("Human"), new RandomAI());
        playGamePrintWinner(g);
    }
    
    public static void errorGame(){
        Game g = new Game(new RandomAI(), new ErrorAI());
        playGamePrintWinner(g);
    }
    
    public static void main(String[] args){
        String black, red;
        if(args.length == 2){
            black = args[0];
            red = args[1];
        }
        else{
            black = "Black";
            red = "Red";
        }
        Game g = new Game(new HumanPlayer(black), new HumanPlayer(red));
        playGamePrintWinner(g);
    }

}