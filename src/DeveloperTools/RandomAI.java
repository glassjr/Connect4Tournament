package DeveloperTools;

import ConnectKSource.*;

public class RandomAI extends Player
{
    public int makeMove(Board board, Chip color) {
        int[] openCells = new int[board.columns()];
        int availibleCount = 0;
        for(int i=0; i<openCells.length; i++) {
            if(board.getChip(0,i) == Chip.EMPTY) {
                openCells[availibleCount] = i;
                availibleCount++;
            }
        }
        return openCells[(int)(Math.random()*availibleCount)];
    }
}
