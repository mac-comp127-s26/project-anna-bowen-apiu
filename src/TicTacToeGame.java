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
    private String[][] board = new String[3][3];
   
    
    private int gameCount;
    private int symbolCount;
    private boolean isGameRunning;

    private CanvasWindow canvas;
    private Grid grid;

    public TicTacToeGame() {
        gameCount = 0;
        isGameRunning = true;
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
            if (isGameRunning) {
                double x = event.getPosition().getX();
                double y = event.getPosition().getY();
                addSymbol(x,y);
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
        board = new String[3][3];
        symbolCount = 0;
        isGameRunning = true;
        gameCount++;
    }

    private void addSymbol(double x, double y) {
        GraphicsObject clicked = grid.getElementAtLocalCoordinates(x, y);
        Cell cell=(Cell)clicked;
        int row=cell.getRow();
        int col=cell.getColumn();

        if(clicked!=null && !filledCells.contains(clicked)){
            filledCells.add(clicked);

            String imageFile;

            if (gameCount%2 ==0){
                if (symbolCount%2 == 0) {
                board[row][col] = "X";
                imageFile = "ex.png";

                } else {
                    board[row][col] = "O";
                    imageFile = "oh.png";               
                }
            } else {
                if (symbolCount%2 == 0) {
                board[row][col] = "O";
                imageFile = "oh.png";

                } else {
                    board[row][col] = "X";
                    imageFile = "ex.png";               
                }
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
            winGame();
            }
    }

    private void addToSet(String imageFile, Image symbol) {
        if (imageFile == "ex.png") {
            xSymbols.add(symbol);
        } else {
            oSymbols.add(symbol);
        }
    }
 
    public void winGame(){
        for(int row=0;row<3;row++){
            if (board[row][0] != null &&
            board[row][0].equals(board[row][1]) &&
            board[row][1].equals(board[row][2])) {
                System.out.println("Win in row");
                isGameRunning = false;
            }
        }

        for(int col=0;col<3;col++){
            if(board[0][col] != null &&
                board[0][col].equals(board[1][col])&&
                board[1][col].equals(board[2][col])) {
                    System.out.println("Win in col");
                    isGameRunning = false;   
                }
        }

        if (board[0][0] != null &&
            board[0][0].equals(board[1][1]) &&
            board[1][1].equals(board[2][2])) {
                System.out.println("Win in diagonal");
                isGameRunning = false;
            }

        if (board[0][2] != null &&
            board[0][2].equals(board[1][1]) &&
            board[1][1].equals(board[2][0])) {
                System.out.println("Win in diagonal");
                isGameRunning = false;
            }
    }
            
    public static void main(String[] args){
        new TicTacToeGame();
    }
}