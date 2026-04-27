import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class Grid extends GraphicsGroup {
    public static final int GRID_WIDTH = 420;
    public static final int GRID_HEIGHT = 420;
    private Rectangle board;
    public List<Rectangle> cells = new ArrayList<>(); //array instead
    private int x = 90;
    private int xnew = x;
    private int y = 190;



    public Grid() {
        board = new Rectangle(x,y,GRID_WIDTH,GRID_HEIGHT);
        add(board);
        for (int i=0;i<9;i++) { //change this to be nested for-loops with rows and columns
            if (xnew + GRID_WIDTH/3 > x + GRID_WIDTH) {
                xnew = x;
                y += GRID_HEIGHT/3;
            }
            Rectangle cell = new Cell(xnew,y,GRID_WIDTH/3,GRID_HEIGHT/3,row,col);
            add(cell);
            cells.add(cell);
            xnew += GRID_WIDTH/3;
        }
    }

    public List<Rectangle> getCells() {
        return cells;
    }
}
