package shahnur.nqueens;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Shahnur Isgandarli
 */

public class Runner extends Application {
    private final int defaultValue = 5;
    private final int minimumWidth = 250;
    private final Insets defaultInsets = new Insets(defaultValue);
    private QueenPlacer queenPlacer;
    private Board board;
    private Label label;
    private TextField textField;
    private Button button;
    private VBox vBox;

    /**
     * This method places the N Queens in correct positions.
     *
     * @param n The size of the board.
     */
    private void placeNQuenns(int n) {
        queenPlacer = new QueenPlacer(n);
        board = new Board(n, queenPlacer);
        board.generateBoard();
    }

    /**
     * This method is used in event-handler operations.
     * All it does it changes the result grid and resizes
     * the stage.
     *
     * @param stage The stage of the JavaFX application.
     */
    private void processEvent(Stage stage) {
        if (vBox.getChildren().size() > 1) {
            vBox.getChildren().remove(vBox.getChildren().size() - 1);
        }
        try {
            int sizeOfTheBoard = Integer.parseInt(textField.getText());
            if (sizeOfTheBoard > 19) {
                Label tooLargeNumber = new Label(String.format("The board size %d is too large!", sizeOfTheBoard));
                tooLargeNumber.setTextFill(Color.RED);
                vBox.getChildren().add(tooLargeNumber);
                vBox.setMargin(tooLargeNumber, defaultInsets);
            } else if (sizeOfTheBoard < 4) {
                Label noSolutions = new Label(String.format("No Solution exist for the board size %d", sizeOfTheBoard));
                noSolutions.setTextFill(Color.RED);
                vBox.getChildren().add(noSolutions);
                vBox.setMargin(noSolutions, defaultInsets);
            } else {
                placeNQuenns(sizeOfTheBoard);
                GridPane grid = board.getGrid();
                grid.setAlignment(Pos.CENTER);
                vBox.getChildren().add(grid);
            }
        } catch (NumberFormatException e) {
            Label enterANumber = new Label("Please, enter an integer number.");
            enterANumber.setTextFill(Color.RED);
            vBox.getChildren().add(enterANumber);
            vBox.setMargin(enterANumber, defaultInsets);
        }
        stage.sizeToScene();
    }

    /**
     * This method starts the stage and does necessary operations
     * for displaying the result.
     *
     * @param stage The stage of the JavaFX application.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        vBox = new VBox();

        HBox hBox = new HBox();

        label = new Label("Select Number of Queens");

        textField = new TextField();
        textField.setMaxWidth(40);
        textField.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    processEvent(stage);
                }
            }
        });

        button = new Button("Run!");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                processEvent(stage);
            }
        });

        hBox.getChildren().addAll(label, textField, button);
        hBox.setMargin(label, defaultInsets);
        hBox.setMargin(textField, defaultInsets);
        hBox.setMargin(button, defaultInsets);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().add(hBox);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setMinWidth(minimumWidth);

        stage.getIcons().add(new Image("file:img/icon.png"));
        stage.setTitle("N-Queens Solver");
        stage.setScene(new Scene(vBox));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }
}
