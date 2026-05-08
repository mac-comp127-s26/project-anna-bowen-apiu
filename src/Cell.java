import edu.macalester.graphics.Rectangle;

/**
 * Cell class for the Tic Tac Toe Board.
 * 
 * @author Anna Wurtz <awurtz@macalester.edu>
 * @author Apiu Agou <akur@macalester.edu>
 * @author Bowen Tang <btang1@macalester.edu>
 * 
 * This class extends the Rectangle class and stores the row and
 * column indices of a grid cell.
 */
public class Cell extends Rectangle {
    private int row;
    private int col;

    /**
     * Constructs a cell object.
     * 
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @param width The width of the cell.
     * @param height The height of the cell.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    public Cell(int x, int y, int width, int height, int row, int col) {
        super(x,y,width,height);
        this.row = row;
        this.col = col;
    }


    /**
     * Returns the row index of the cell.
     * 
     * @return The row index of the cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of the cell.
     * 
     * @return The column index of the cell.
     */
    public int getColumn() {
        return col;
    }
}
