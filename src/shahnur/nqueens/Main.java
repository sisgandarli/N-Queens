package shahnur.nqueens;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private void placeNQuenns(int n) {
        queenPlacer = new QueenPlacer(n);
        board = new Board(n, queenPlacer);
        board.drawBoard();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();

        HBox hBox = new HBox();

        Label label = new Label("Select number of Queens");
        label.setPadding(new Insets(5));

        TextField textField = new TextField();
        textField.setMaxWidth(40);
        textField.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        textField.setPadding(new Insets(5));

        Button button = new Button("Run!");
        button.setPadding(new Insets(5));

        vBox.getChildren().add(hBox);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (vBox.getChildren().size() > 1) {
                    vBox.getChildren().remove(vBox.getChildren().size() - 1);
                }
                placeNQuenns(Integer.parseInt(textField.getText()));
                GridPane grid = board.getGrid();
                grid.setAlignment(Pos.CENTER);

                vBox.getChildren().add(grid);
                stage.sizeToScene();
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
