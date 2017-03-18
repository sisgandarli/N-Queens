package shahnur.nqueens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class Board {
    private Image queenWhite = new Image("file:img/queen-white.png");
    private Image queenBlue = new Image("file:img/queen-blue.png");
    private Image white = new Image("file:img/white.png");
    private Image blue = new Image("file:img/blue.png");

    private GridPane grid;
    private int size;
    private int[][] board;
    private QueenPlacer placer;

    public Board(int size, QueenPlacer placer) {
        this.board = new int[size][size];
        this.size = size;
        this.grid = new GridPane();
        this.placer = placer;
    }

    private void generateSolution() {
        this.placer.placeQueens();

        int[][] tmp = this.placer.getCells();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = tmp[i][j];
            }
        }
    }

    private ImageView createImageView(int i, int j, int value) {
        int color = 0;
        if (i % 2 == 0) {
            if (j % 2 == 0) {
                color = 0;
            } else {
                color = 1;
            }
        } else {
            if (j % 2 == 0) {
                color = 1;
            } else {
                color = 0;
            }
        }
        if (value == 1) {
            if (color == 1) {
                return new ImageView(queenBlue);
            } else {
                return new ImageView(queenWhite);
            }
        } else {
            if (color == 0) {
                return new ImageView(white);
            } else {
                return new ImageView(blue);
            }
        }
    }

    public void drawBoard() {
        generateSolution();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ImageView imageView = createImageView(i, j, board[i][j]);
                grid.addRow(i, imageView);
            }
        }
    }

    public GridPane getGrid() {
        return grid;
    }

}
