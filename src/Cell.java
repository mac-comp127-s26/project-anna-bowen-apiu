import edu.macalester.graphics.Rectangle;

public class Cell extends Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int row;
    private int col;

    public Cell(int x, int y, int width, int height, int row, int col) {
        super(x,y,width,height);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }
}
