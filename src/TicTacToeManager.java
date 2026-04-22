import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class TicTacToeManager {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Grid grid;

    public TicTacToeManager() {
        canvas = new CanvasWindow("Tic-Tac-Toe!", CANVAS_WIDTH, CANVAS_HEIGHT);
        // Image exSymbol = new Image(0, 0, "ex.png");
        // exSymbol.setScale(0.5);
        // canvas.add(exSymbol);
        // Image ohSymbol = new Image(300, 300, "oh.png");
        // ohSymbol.setScale(0.5);
        // canvas.add(ohSymbol);
        grid = new Grid();
        canvas.add(grid);
        canvas.onMouseDown(event -> {
            Image exSymbol = new Image(0, 0, "ex.png");
            canvas.add(exSymbol);
            exSymbol.setPosition(event.getPosition());
            exSymbol.setScale(0.5);
        });
    }
    public static void main(String[] args){
        new TicTacToeManager();
    }
}
