
import DeveloperTools.*;
import ConnectKSource.*;
import java.util.LinkedList;

public class Tournament
{
    private LinkedList<TournamentPlayer> players;
    private boolean suspenseMode;
    private boolean debugMode;
    private int matchCount;

    public Tournament(){
        players = new LinkedList<TournamentPlayer>();

        for(int i=1; i<=9; i++){
            players.add(new TournamentPlayer(new RandomAI(), "RandomAI"+i, i));
        }

        suspenseMode = false;
        debugMode = false;
        matchCount = 5;
        cleanUpPrepare();
    }

    private void cleanUpPrepare(){
        if(players.size()%2 == 1)
            players.add(new TournamentPlayer(new RandomAI(), "RandomAINULL", -1));
    }

    public void rotate(){
        LinkedList<TournamentPlayer> tops = topList();
        LinkedList<TournamentPlayer> bottoms = bottomList();

        TournamentPlayer tFirst = tops.removeFirst();
        TournamentPlayer bFirst = bottoms.removeFirst();
        TournamentPlayer tLast = tops.removeLast();

        bottoms.addLast(tLast);
        tops.addFirst(bFirst);
        tops.addFirst(tFirst);

        for(TournamentPlayer tp : bottoms){
            tops.addLast(tp);
        }

        players = tops;
    }

    public LinkedList<TournamentPlayer> topList(){
        LinkedList<TournamentPlayer> topPlayers = new LinkedList<TournamentPlayer>();
        int size = players.size();
        for(int i=0; i<size/2; i++){
            topPlayers.add(players.get(i));
        }
        return topPlayers;
    }

    public LinkedList<TournamentPlayer> bottomList(){
        LinkedList<TournamentPlayer> bottomPlayers = new LinkedList<TournamentPlayer>();
        int size = players.size();
        for(int i=size/2; i<size; i++){
            bottomPlayers.add(players.get(i));
        }
        return bottomPlayers;
    }

    public String toString(){
        String str = "";
        for(TournamentPlayer tp : players){
            if(tp.getID() >= 0) str += tp.toString() + "\n";
        }
        return str;
    }

    public void playTournament(){
        for(int round=1; round<=players.size()-1; round++){
            LinkedList<TournamentPlayer> tops = topList();
            LinkedList<TournamentPlayer> bottoms = bottomList();

            while(tops.size() > 0 && bottoms.size() > 0){
                TournamentPlayer tp1 = tops.removeFirst();
                TournamentPlayer tp2 = bottoms.removeFirst();
                if(tp1.getID() >= 0 && tp2.getID() >= 0 && tp1 != tp2){
                    for(int i=0; i<matchCount; i++){
                        goPlayGame(tp1, tp2);
                        goPlayGame(tp2, tp1);
                    }
                }
            }
            rotate();
            System.out.println("\f" + toString());
        }
    }

    private void sendPlayerEndGame(TournamentPlayer tp, Chip itsColor, Chip winner, Game g){
        Player player = tp.getPlayer();
        Board copyBoard = g.getCopyBoard();
        try{
            player.acceptEndGame(copyBoard, itsColor, winner);
        }catch(Exception e){
            //Player AI threw an exception during acceptEndGame
            //Should there be a penalty here?
        }
    }
    
    public void goPlayGame(TournamentPlayer tp1, TournamentPlayer tp2){
        Game g = new Game(tp1.getPlayer(), tp2.getPlayer(), 6, 7, 4, debugMode);
        Player winner = g.playGame();
        if(winner == null){
            tp1.addDraw();  tp2.addDraw();
            sendPlayerEndGame(tp1, Chip.BLACK, null, g);
            sendPlayerEndGame(tp2, Chip.RED, null, g);
        }
        else if(winner == tp1.getPlayer()){
            tp1.addWin();   tp2.addLoss();
            sendPlayerEndGame(tp1, Chip.BLACK, Chip.BLACK, g);
            sendPlayerEndGame(tp2, Chip.RED, Chip.BLACK, g);
        }
        else if(winner == tp2.getPlayer()){
            tp1.addLoss();  tp2.addWin();
            sendPlayerEndGame(tp1, Chip.BLACK, Chip.RED, g);
            sendPlayerEndGame(tp2, Chip.RED, Chip.RED, g);
        }

        if(suspenseMode){
            try{
                System.in.read();
                System.out.println(g);
                System.out.println(tp1);
                System.out.println(tp2);
            }
            catch(Exception e){}
        }
        else
        {
            System.out.println("\f" + tp1);
            System.out.println(tp2);
        }
    }

    public static void main(String[] args){
        System.out.print("\f");
        Tournament t = new Tournament();
        t.playTournament();
    }

}