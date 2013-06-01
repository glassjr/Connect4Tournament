package ConnectKSource;

public class Board
{
    private Cell[][] gameBoard;

    public Board(){
        this(6, 7);
    }

    public Board(int m, int n){
        gameBoard = new Cell[m][n];
        for(int row=0; row<m; row++){
            for(int col=0; col<n; col++){
                gameBoard[row][col] = new Cell();
            }
        }
    }

    public Board(Board that){
        int m = that.gameBoard.length;
        int n = that.gameBoard[0].length;
        this.gameBoard = new Cell[m][n];
        for(int row=0; row<m; row++){
            for(int col=0; col<n; col++){
                this.gameBoard[row][col] = new Cell(that.gameBoard[row][col]);
            }
        }
    }

    public boolean set(int r, int c, Chip color){
        if (!isValid(r,c) || !gameBoard[r][c].isEmpty()) return false;
        gameBoard[r][c].set(color);
        return true;
    }

    public boolean isValid(int r, int c){
        return r >=0 && c >= 0 && r < gameBoard.length && c < gameBoard[r].length;
    }

    public Location findPlacing(int col){
        int row=0;
        while(isValid(row, col) && gameBoard[row][col].isEmpty())
            row++;
        row--;
        return new Location(row, col);
    }

    public boolean set(Location loc, Chip color) {
        return set(loc.row, loc.col, color);
    }

    public boolean isWon(Location lastMove, int k){
        return whoWon(lastMove, k) != null;
    }
    
    public Chip whoWon(Location lastMove, int k){
        //columns
        int colBegin = Math.max(0, lastMove.row-(k-1));
        int colEnd = Math.min(gameBoard.length-1, lastMove.row+(k-1));
        Cell[] colCells = new Cell[colEnd-colBegin+1];
        for (int i=0; i<colCells.length; i++){
            colCells[i] = gameBoard[i+colBegin][lastMove.col];
        }
        Chip who = checkKRun(k, colCells);
        if(who != null) return who;
        
        //rows
        int rowBegin = Math.max(0, lastMove.col-(k-1));
        int rowEnd = Math.min(gameBoard[0].length-1, lastMove.col+(k-1)); 
        Cell[] rowCells = new Cell[rowEnd-rowBegin+1];
        for(int i=0; i<rowCells.length; i++){
            rowCells[i] = gameBoard[lastMove.row][i+rowBegin];
        }
        who = checkKRun(k, colCells);
        if(who != null) return who;
        
        //southwest-to-northeast diagonal
        int rBegin = lastMove.row + (k-1);
        int cBegin = lastMove.col - (k-1);
        while(!isValid(rBegin,cBegin)){
            rBegin--; cBegin++;
        }
        int rEnd = lastMove.row - (k-1);
        int cEnd = lastMove.col + (k-1);
        while(!isValid(rEnd,cEnd)){
            rEnd++; cEnd--;
        }
        Cell[] diagCells = new Cell[cEnd-cBegin+1];
        for(int i=0; i<diagCells.length; i++){
            diagCells[i] = gameBoard[rBegin-i][cBegin+i];
        }
        who = checkKRun(k, colCells);
        if(who != null) return who;
        
        //southeast-to-northwest diagonal
        rBegin = lastMove.row + (k-1);
        cBegin = lastMove.col + (k-1);
        while(!isValid(rBegin,cBegin)){
            rBegin--; cBegin--;
        }
        rEnd = lastMove.row - (k-1);
        cEnd = lastMove.col - (k-1);
        while(!isValid(rEnd,cEnd)){
            rEnd++; cEnd++;
        }
        diagCells = new Cell[rBegin-rEnd+1];
        for(int i=0; i<diagCells.length; i++){
            diagCells[i] = gameBoard[rBegin-i][cBegin-i];
        }
        return checkKRun(k, diagCells);
        //that was the last check!
    }
    
    
    private Chip checkKRun(int k, Cell[] cells) {
        if(cells.length < k) return null;
        Chip currentColor = Chip.EMPTY;
        int colorCount = 0;
        for (Cell c : cells) {
            if (currentColor != c.chip()) {
                currentColor = c.chip();
                colorCount = 1;
            }
            else colorCount++;
            if (colorCount >= k && currentColor != Chip.EMPTY) return currentColor;
        }
        return null;
    }

    public boolean hasAvailableMove(){
        for(Cell c : gameBoard[0]){
            if(c.isEmpty()) return true;
        }
        return false;
    }

    public Cell getCell(int r, int c){
        return gameBoard[r][c];
    }

    public Chip getChip(int r, int c){
        return gameBoard[r][c].chip();
    }
    
    public int rows(){
        return gameBoard.length;
    }
    
    public int columns(){
        return gameBoard[0].length;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<gameBoard[0].length; i++){
            sb.append(i + " ");
        }
        sb.append("\n");
        for(int r=0; r<gameBoard.length; r++){
            for(int c=0; c<gameBoard[r].length; c++){
                sb.append(getCell(r,c) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
