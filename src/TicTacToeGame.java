import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

public class TicTacToeGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private Set<GraphicsObject>filledCells = new HashSet<>();
    private Set<GraphicsObject> xSymbols = new HashSet<>();
    private Set<GraphicsObject> oSymbols = new HashSet<>();
    private Button restartButton;
   
    
    private int gameCount;
    private int symbolCount;

    private CanvasWindow canvas;
    private Grid grid;
    

    public TicTacToeGame() {
        gameCount = 0;
        canvas = new CanvasWindow("Tic-Tac-Toe!", CANVAS_WIDTH, CANVAS_HEIGHT);
        grid = new Grid();
        canvas.add(grid);
        restartButton=new Button("New Game");
        restartButton.setPosition(245.0,700.0);
        canvas.add(restartButton);
        restartButton.onClick(() -> {
            newGame();
        });
        canvas.onMouseDown(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            GraphicsObject clicked = grid.getElementAtLocalCoordinates(x, y);
             if(clicked!=null && !filledCells.contains(clicked)){
               filledCells.add(clicked);

                String imageFile;

                if (symbolCount%2 == 0) {
                    imageFile = "ex.png";

                } else {
                    imageFile = "oh.png";
                }

                Image symbol = new Image(x, y, imageFile);
                double paddingX = (clicked.getWidth() - symbol.getWidth()/2)/2;
                double paddingY = (clicked.getHeight() - symbol.getHeight()/2)/2;
                double xnew = clicked.getPosition().getX() - symbol.getWidth()/4 + paddingX;
                double ynew = clicked.getPosition().getY() - symbol.getHeight()/4 + paddingY;
                symbol.setScale(0.5);
                symbol.setPosition(xnew,ynew);
                canvas.add(symbol);
                addToSet(imageFile,symbol);
                symbolCount++;             
            }
        });
    }
    
    private void newGame() {
        for (GraphicsObject symbol:xSymbols) {
            canvas.remove(symbol);
        }
        for (GraphicsObject symbol:oSymbols) {
            canvas.remove(symbol);
        }
        xSymbols.clear();
        oSymbols.clear();
        filledCells.clear();
        symbolCount = 0;
        gameCount++;
    }

    private void addToSet(String imageFile, Image symbol) {
        if (imageFile == "ex.png") {
            xSymbols.add(symbol);
        } else {
            oSymbols.add(symbol);
        }
    }
    public static void main(String[] args){
        new TicTacToeGame();
    }
}
