package ConnectKSource;




public class Cell
{
    private Chip chip;
    
    public Cell(){
        chip = Chip.EMPTY;
    }
    
    public Cell(Chip c){
        chip = c;
    }
    
    public Cell(Cell that){
        this.chip = that.chip;
    }
    
    public void set(Chip newChip){
        chip = newChip;
    }
    
    public boolean isEmpty() {
        return chip==Chip.EMPTY;
    }
    
    public Chip chip(){
        return chip;
    }
    
    public String toString(){
        switch(chip){
            case RED: return "O";
            case BLACK: return "X";
            default: return ".";
        }
    }
}