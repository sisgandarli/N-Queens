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
import javafx.stage.Stage;

/**
 @author Shahnur Isgandarli
 */
public class Runner extends Application {
    private QueenPlacer queenPlacer;
    private Board board;
    private Label label;
    private TextField textField;
    private Button button;
    private VBox vBox;

    /**
     * This method places the N Queens in correct positions.
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
     * @param stage The stage of the JavaFX application.
     */
    private void eventHandler(Stage stage) {
        if (vBox.getChildren().size() > 1) {
            vBox.getChildren().remove(vBox.getChildren().size() - 1);
        }
        placeNQuenns(Integer.parseInt(textField.getText()));
        GridPane grid = board.getGrid();
        grid.setAlignment(Pos.CENTER);

        vBox.getChildren().add(grid);
        stage.sizeToScene();
    }

    /**
     * This method starts the stage and does necessary operations
     * for displaying the result.
     * @param stage The stage of the JavaFX application.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        vBox = new VBox();

        HBox hBox = new HBox();

        label = new Label("Select number of Queens");
        label.setPadding(new Insets(5));

        textField = new TextField();
        textField.setMaxWidth(40);
        textField.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        textField.setPadding(new Insets(5));
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    eventHandler(stage);
                }
            }
        });

        button = new Button("Run!");
        button.setPadding(new Insets(5));

        vBox.getChildren().add(hBox);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler(stage);
            }
        });
        hBox.getChildren().addAll(label, textField, button);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        stage.setMinWidth(250);
        stage.getIcons().add(new Image("file:img/icon.png"));
        stage.setTitle("N-Queens Solver");
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
