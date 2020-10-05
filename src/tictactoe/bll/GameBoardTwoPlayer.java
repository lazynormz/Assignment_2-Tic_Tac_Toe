package tictactoe.bll;

/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {

    /*
     * Player data
     * */
    private int currentPlayer = 0;

    /*
    * Game data
    * */
    private final int COLS = 3;
    private final int ROWS = 3;

    /*
     * Map data
     * */
    private String[] map;
    private int availableSlots;

    //Clears the board and it's data to get it ready for a new round of play.
    private void clearBoard(){
        map = new String[COLS * ROWS];
        availableSlots = COLS * ROWS;
    }

    protected GameBoardTwoPlayer() {
        clearBoard();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {
        return currentPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) {
        //Check that the cell is empty, and that the game is still running
        if(map[col + row * COLS] == null && isGameOver() == false){
            //Set the cell value to the correct players character
            map[col + row * COLS] = (currentPlayer == 0) ? "X" : "O";
            currentPlayer = (currentPlayer == 0) ? 1 : 0;
            availableSlots -= 1;
            return true;
        }else {
            return false;
        }
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    @Override
    public boolean isGameOver() {
        //If there are no slots, or either of the players have won,
        //the game is over, otherwise we're continuing the game
        if(availableSlots == 0 || getWinner() == 0 || getWinner() == 1){
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        //Horizontal Lines
        if((map[0] == map[1] && map[1] == map[2]) && map[0] != null) return (map[0] == "X" ? 0 : 1);
        if((map[3] == map[4] && map[4] == map[5]) && map[3] != null) return (map[3] == "X" ? 0 : 1);
        if((map[6] == map[7] && map[7] == map[8]) && map[6] != null) return (map[6] == "X" ? 0 : 1);
        //Vertical Lines
        if((map[0] == map[3] && map[3] == map[6]) && map[0] != null) return (map[0] == "X" ? 0 : 1);
        if((map[1] == map[4] && map[4] == map[7]) && map[1] != null) return (map[1] == "X" ? 0 : 1);
        if((map[2] == map[5] && map[5] == map[8]) && map[2] != null) return (map[0] == "X" ? 0 : 1);
        //Diagonal Lines
        if((map[0] == map[4] && map[4] == map[8]) && map[0] != null) return (map[0] == "X" ? 0 : 1);
        if((map[2] == map[4] && map[4] == map[6]) && map[2] != null) return (map[2] == "X" ? 0 : 1);
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        clearBoard();
    }

}