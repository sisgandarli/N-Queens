package shahnur.nqueens;

/**
 * @author Shahnur Isgandarli
 */
public class QueenPlacer {
    private int[][] board;
    private int size;

    /**
     * This is constructor of {@link QueenPlacer} class.
     * @param size The size of the chess board.
     */
    public QueenPlacer(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    /**
     * This method checks if it is possible to place a queen on
     * a given cell specified by row and column numbers.
     * @param board The board.
     * @param row The specified row number.
     * @param col The specified column number.
     * @return boolean Returns true if it is possible to place a queen, otherwise returns false.
     */
    private boolean canPlace(int[][] board, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i < size && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method tries to solve constraint satisfaction problem called "N-Queens".
     */
    public void placeQueens() {
        boolean result = placeQueens(this.board, 0);
    }

    /**
     * This method is uses backtracking search in order to solve
     * constraint satisfaction problem called "N-Queens".
     * @param board The board.
     * @param col The specified column number.
     * @return boolean Returns true if there is a solution to "N-Queens" problems, otherwise returns false.
     */
    private boolean placeQueens(int[][] board, int col) {
        if (col >= size) {
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (canPlace(board, i, col)) {
                board[i][col] = 1;
                if (placeQueens(board, col + 1)) {
                    return true;
                }
                board[i][col] = 0;
            }
        }
        return false;
    }

    /**
     * This is a getter method for retrieving the board.
     * @return int[][]
     */
    public int[][] getBoard() {
        return board;
    }
}
