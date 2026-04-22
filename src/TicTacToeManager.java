import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class TicTacToeManager {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Grid grid;

    public TicTacToeManager() {
        canvas = new CanvasWindow("Tic-Tac-Toe!", CANVAS_WIDTH, CANVAS_HEIGHT);
        grid = new Grid();
        canvas.add(grid);
        canvas.onMouseDown(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            GraphicsObject clicked = grid.getElementAtLocalCoordinates(x, y);

            if (clicked != null) {
                Image exSymbol = new Image(x, y, "ex.png");
                double paddingX = (clicked.getWidth() - exSymbol.getWidth()/2)/2;
                double paddingY = (clicked.getHeight() - exSymbol.getHeight()/2)/2;
                double xnew = clicked.getPosition().getX() - exSymbol.getWidth()/4 + paddingX;
                double ynew = clicked.getPosition().getY() - exSymbol.getHeight()/4 + paddingY;
                exSymbol.setScale(0.5);
                exSymbol.setPosition(xnew,ynew);
                canvas.add(exSymbol);
            }
        });
    }
    public static void main(String[] args){
        new TicTacToeManager();
    }
}
