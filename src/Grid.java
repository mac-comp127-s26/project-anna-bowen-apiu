import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

/**
 * Grid class for the Tic Tac Toe Board.
 * 
 * @author Anna Wurtz <awurtz@macalester.edu>
 * @author Apiu Agou <akur@macalester.edu>
 * @author Bowen Tang <btang1@macalester.edu>
 * 
 * This class extends the GraphicsGroup class and creates the
 * Tic Tac Toe game board using Cell objects.
 */
public class Grid extends GraphicsGroup {
    public static final int GRID_WIDTH = 420;
    public static final int GRID_HEIGHT = 420;
    private Rectangle board;
    private int x = 90;
    private int y = 190;

    /**
     * Constructs a grid object containing the Tic Tac Toe board
     * and its individual cells.
     */
    public Grid() {
        board = new Rectangle(x,y,GRID_WIDTH,GRID_HEIGHT);
        add(board);
        int cellWidth = GRID_WIDTH/3;
        int cellHeight = GRID_HEIGHT/3;
        for(int row = 0;row < 3;row++){
            for(int col = 0;col < 3;col++){
                int cellX = x+col*cellWidth;
                int cellY = y+row*cellHeight;             
                Cell cell = new Cell(cellX, cellY, cellWidth, cellHeight, row, col);
                add(cell);
            }
        }
    }
}
