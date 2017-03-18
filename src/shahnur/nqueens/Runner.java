package shahnur.nqueens;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private QueenPlacer queenPlacer;
    private Board board;
    private Label label;
    private TextField textField;
    private Button button;
    private VBox vBox;

    private void placeNQuenns(int n) {
        queenPlacer = new QueenPlacer(n);
        board = new Board(n, queenPlacer);
        board.drawBoard();
    }

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
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
