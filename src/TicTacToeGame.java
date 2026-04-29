import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

public class TicTacToeGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private Set<GraphicsObject>filledCells = new HashSet<>();
    private Set<GraphicsObject> symbols = new HashSet<>();
    private Button restartButton;
    private String[][] board = new String[3][3];
   
    
    private int gameCount;
    private int symbolCount;
    private boolean isGameRunning;
    private boolean isWon;

    private CanvasWindow canvas;
    private Grid grid;
    private GraphicsText displayedMessage;

    public TicTacToeGame() {
        gameCount = 0;
        isGameRunning = true;
        isWon = false;
        canvas = new CanvasWindow("Tic-Tac-Toe!", CANVAS_WIDTH, CANVAS_HEIGHT);
        grid = new Grid();
        
        canvas.add(grid);
        restartButton=new Button("New Game");
        restartButton.setPosition(245.0,700.0);
        canvas.add(restartButton);
        displayedMessage = new GraphicsText("Player X's turn", 130, 160);
        displayedMessage.setFontSize(50);
        displayedMessage.setFillColor(Color.BLACK);
        canvas.add(displayedMessage);
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
        gameCount++;
        if (gameCount%2 == 0) {
            showPlayerTurn("X");
        } else {
            showPlayerTurn("O");
        }
        for (GraphicsObject symbol:symbols) {
            canvas.remove(symbol);
        }
        symbols.clear();
        filledCells.clear();
        board = new String[3][3];
        symbolCount = 0;
        isGameRunning = true;
    }

    private void addSymbol(double x, double y) {
        GraphicsObject clicked = grid.getElementAtLocalCoordinates(x, y);
        Cell cell = (Cell)clicked;
        int row = cell.getRow();
        int col = cell.getColumn();

        if(clicked != null && !filledCells.contains(clicked)){
            filledCells.add(clicked);

            String imageFile;

            if (gameCount%2 == 0) {
                if (symbolCount%2 == 0) {
                board[row][col] = "X";
                imageFile = "ex.png";
                showPlayerTurn("O");
                } else {
                    board[row][col] = "O";
                    imageFile = "oh.png";
                    showPlayerTurn("X");             
                }
            } else {
                if (symbolCount%2 == 0) {
                board[row][col] = "O";
                imageFile = "oh.png";
                showPlayerTurn("X"); 
                } else {
                    board[row][col] = "X";
                    imageFile = "ex.png";
                    showPlayerTurn("O");               
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
            symbols.add(symbol);
            symbolCount++;
            winGame();
        }
    }
 
    public void winGame(){
        for(int row = 0;row < 3;row++){
            if (checkWinningConditions(board[row][0],board[row][1],board[row][2])) {
                    endGameWithWin(row,0);
                }
        }

        for(int col = 0;col < 3;col++){
            if(checkWinningConditions(board[0][col],board[1][col],board[2][col])) {
                    endGameWithWin(0,col);
                }
        }

        if (checkWinningConditions(board[0][0],board[1][1],board[2][2])) {
                endGameWithWin(0,0);
            }

        if (checkWinningConditions(board[0][2],board[1][1],board[2][0])) {
                endGameWithWin(0,2);
            }
        
        if (symbolCount == 9 && !isWon) {
                endGameWithTie();
            }
    }

    public boolean checkWinningConditions(Object a, Object b, Object c) {
        return (a != null && a.equals(b) && b.equals(c));
    }

    public void endGameWithWin(int a,int b) {
        isWon = true;
        isGameRunning = false;
        showWinningMessage(a,b);
    }

    public void endGameWithTie() {
        isGameRunning = false;
        displayedMessage.setText("Tie!");
        displayedMessage.setPosition(255,160);
    }

    public void showPlayerTurn(String symbol) {
        String message;
        int x;
        if (symbol.equals("X")) {
            message = "Player X's turn";
            x = 130;
            } else {
                message = "Player O's turn";
                x = 128;
            }
        displayedMessage.setText(message);
        displayedMessage.setPosition(x, 160);
        canvas.add(displayedMessage);
    }
    
    public void showWinningMessage(int a,int b) {
        String message;
        if (board[a][b].equals("X")) {
            message = "Player X wins!";
            } else {
                message = "Player O wins!";
            }
        displayedMessage.setText(message);
        displayedMessage.setPosition(135, 160);
    }
            
    public static void main(String[] args){
        new TicTacToeGame();
    }
}