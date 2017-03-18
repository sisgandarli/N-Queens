package shahnur.nqueens;

public class QueenPlacer {
    private int[][] cells;
    private int size;

    public QueenPlacer(int size) {
        this.size = size;
        this.cells = new int[size][size];
    }

    private boolean canPlace(int[][] cells, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (cells[row][i] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (cells[i][j] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i < size && j >= 0; i++, j--) {
            if (cells[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public void placeQueens() {
        boolean result = placeQueens(this.cells, 0);
    }

    private boolean placeQueens(int[][] cells, int col) {
        if (col >= size) {
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (canPlace(cells, i, col)) {
                cells[i][col] = 1;
                if (placeQueens(cells, col + 1)) {
                    return true;
                }
                cells[i][col] = 0;
            }
        }
        return false;
    }

    public int[][] getCells() {
        return cells;
    }
}
