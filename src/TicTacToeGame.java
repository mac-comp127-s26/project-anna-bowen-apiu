import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

/**
 * Main class for running and playing the Tic Tac Toe Game.
 * 
 * @author Anna Wurtz <awurtz@macalester.edu>
 * @author Apiu Agou <akur@macalester.edu>
 * @author Bowen Tang <btang1@macalester.edu>
 * 
 * This class creates the game window, handles user interaction,
 * and manages the game logic and score tracking.
 */

public class TicTacToeGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private Set<GraphicsObject>filledCells = new HashSet<>();
    private Set<GraphicsObject> symbols = new HashSet<>();
    private Button restartButton;
    private Button resetScoresButton;
    private String[][] board = new String[3][3];
   
    
    private int gameCount;
    private int symbolCount;
    private int xWinCount;
    private int oWinCount;
    private boolean isGameRunning;
    private boolean isWon;

    private CanvasWindow canvas;
    private Grid grid;

    private GraphicsText displayedMessage;
    private GraphicsText xWinCountMessage;
    private GraphicsText oWinCountMessage;

    private Rectangle popUpBox;
    private GraphicsText popUpText;

    /**
     * Main entry point for the Tic Tac Toe Game.
     */
    public static void main(String[] args){
        new TicTacToeGame();
    }
    
    /**
     * Construct a new Tic Tac Toe Game.
     */
    public TicTacToeGame() {
        gameCount = 0;
        xWinCount = 0;
        oWinCount = 0;
        symbolCount = 0;
        isGameRunning = true;
        isWon = false;
        canvas = new CanvasWindow("Tic-Tac-Toe!", CANVAS_WIDTH, CANVAS_HEIGHT);
        grid = new Grid();
        canvas.add(grid);

        restartButton = new Button("New Game");
        restartButton.setPosition(245.0,660.0);
        canvas.add(restartButton);

        resetScoresButton = new Button("Reset Scores");
        resetScoresButton.setPosition(240.0,710.0);
        canvas.add(resetScoresButton);

        xWinCountMessage = createLabel("X wins: " + xWinCount, 20, 50, 40,2);
        oWinCountMessage = createLabel("O wins: " + oWinCount, 390, 50, 40,2);
        displayedMessage = createLabel("Player X's turn", 130, 160, 50,0.25);

        canvas.onMouseDown(event -> {
            if (isGameRunning) {
                double x = event.getPosition().getX();
                double y = event.getPosition().getY();
                addSymbol(x,y);
            }
        });
        
        restartButton.onClick(() -> {
            newGame();
        });

        resetScoresButton.onClick(() -> {
            xWinCount = 0;
            oWinCount = 0;
            setWinCount();
        });
        
        popUpBox = new Rectangle(120, 100, 360, 90);
        popUpBox.setFillColor(Color.WHITE);

        popUpText = new GraphicsText("", 180, 155);
        popUpText.setFontSize(36);
        popUpText.setStrokeWidth(3);
        popUpText.setFillColor(Color.RED);  
    }

    /**
     * Resets game by clearing symbols from the grid and resetting
     * instance variables for a new round.
     */
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
        canvas.setBackground(Color.WHITE);
        symbols.clear();
        filledCells.clear();
        board = new String[3][3];
        symbolCount = 0;
        isGameRunning = true;
        isWon = false;

        canvas.remove(popUpBox);
        canvas.remove(popUpText);
        popUpText.setText("");
    }

    /**
     * Adds either an X or O symbol to board, centered in the selected cell,
     * if the clicked location inside a valid cell and the cell is not already filled.
     * 
     * @param x The x-coordinate of the clicked location.
     * @param y The y-coordinate of the clicked location.
     */
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
    
    /**
     * Handles Tic Tac Toe winning logic. A player wins if they have three
     * matching symbols in a row, column, or diagonal.
     */
    public void winGame(){
        for(int row = 0;row < 3;row++){
            if (checkWinningConditions(board[row][0],board[row][1],board[row][2])) {
                    endRoundWithWin(row,0);
                }
        }

        for(int col = 0;col < 3;col++){
            if(checkWinningConditions(board[0][col],board[1][col],board[2][col])) {
                    endRoundWithWin(0,col);
                }
        }

        if (checkWinningConditions(board[0][0],board[1][1],board[2][2])) {
                endRoundWithWin(0,0);
            }

        if (checkWinningConditions(board[0][2],board[1][1],board[2][0])) {
                endRoundWithWin(0,2);
            }
        
        if (symbolCount == 9 && !isWon) {
                endRoundWithTie();
            }
    }

    /**
     * Checks whether three board positions contain matching non-null values.
     * 
     * @param a The first board position to check.
     * @param b The second board position to check.
     * @param c The third board position to check.
     * @return True if all three positions match and are not null; false otherwise.

     */
    public boolean checkWinningConditions(Object a, Object b, Object c) {
        return (a != null && a.equals(b) && b.equals(c));
    }

    /**
     * Ends the round when a player wins.
     * 
     * @param a The row index of the winning symbol.
     * @param b The column index of the winning symbol.
     */
    public void endRoundWithWin(int a,int b) {
        isWon = true;
        isGameRunning = false;
        showWinningMessage(a,b);
        setWinCount();
    }

    /**
     * Ends the round in the case of a tie. Displays a pop-up message and
     * changes the background color to purple.
     */
    public void endRoundWithTie() {
        isGameRunning = false;
        popUpText.setText("Tie!");
        popUpText.setPosition(270, 155);
        canvas.add(popUpBox);
        canvas.add(popUpText);
         canvas.setBackground(Color.decode("#E0CCFF"));
    }

    /**
     * Displays the label indicating which player's turn is next.
     * 
     * @param symbol The symbol of the player whose turn is next.
     */
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
    
    /**
     * Displays a pop-up message announcing the winner and changes the
     * background color based on the winning symbol.
     * 
     * @param a The row index of the winning symbol.
     * @param b The column index of the winning symbol.
     */
    public void showWinningMessage(int a,int b) {
        popUpText.setText("Player " + board[a][b] + " wins!");
        popUpText.setPosition(180, 155);
        canvas.add(popUpBox);
        canvas.add(popUpText);
        if (board[a][b].equals("X")) {
            canvas.setBackground(Color.decode("#FFBCB5"));
            xWinCount++;
            } else {
                canvas.setBackground(Color.decode("#B8D6FF"));
                oWinCount++;
            }
    }

    /**
     * Updates the displayed win counts for both X and O.
     */
    public void setWinCount() {
        xWinCountMessage.setText("X wins: " + xWinCount);
        oWinCountMessage.setText("O wins: " + oWinCount);
    }

    /**
     * Creates and displays a text label on the canvas.
     * 
     * @param message The text to display.
     * @param x The x-coordinate of the label.
     * @param y The y-coordinate of the label.
     * @param fontSize The font size of the label.
     * @param strokeWidth The stroke width of the label text.
     * @return The created GraphicsText label.
     */
    public GraphicsText createLabel(String message, int x, int y, int fontSize, double strokeWidth) {
        GraphicsText label = new GraphicsText(message, x, y);
        label.setFontSize(fontSize);
        label.setFillColor(Color.BLACK);
        label.setStrokeWidth(strokeWidth);
        canvas.add(label);
        return label;
    }
}