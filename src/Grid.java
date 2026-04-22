import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class Grid extends GraphicsGroup {
    private Rectangle board;
    private List<Rectangle> cells;
    private static final int GRID_WIDTH = 420;
    private static final int GRID_HEIGHT = 420;
    private int x = 90;
    private int xnew = x;
    private int y = 190;



    public Grid() {
        board = new Rectangle(x,y,GRID_WIDTH,GRID_HEIGHT);
        add(board);
        for (int i=0;i<8;i++) {
            if (xnew + GRID_WIDTH/3 > x + GRID_WIDTH) {
                xnew = x;
                y += GRID_HEIGHT/3;
            }
            Rectangle cell = new Rectangle(xnew,y,GRID_WIDTH/3,GRID_HEIGHT/3);
            add(cell);
            xnew += GRID_WIDTH/3;
        }
    }
}
