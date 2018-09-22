package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import javax.xml.soap.Text;

public class GoControlPanel extends Pane{

    public GoControlPanel(GameLogic goGameLogic) {
        super();
        this.goGameLogic = 	goGameLogic;
        this.tf_score = new Label();
        // Binding the SimpleIntegerProperty scoreProperty in GoGameLogic to the TextField tf_score
        this.tf_score.textProperty().bindBidirectional(this.goGameLogic.getScore(), new NumberStringConverter());
        this.vb = new VBox();
        this.getChildren().add(vb);



        vb.setPrefWidth(170);
        vb.setSpacing(30);
        b1 = new Button("How to Play");
        b2 = new Button("Reset Game");
        b3 = new Button("Quit Game");
        b1.setStyle("-fx-font-size: 20px;");
        b1.setMinWidth(vb.getPrefWidth());
        b2.setMinWidth(vb.getPrefWidth());
        b3.setMinWidth(vb.getPrefWidth());
        b2.setStyle("-fx-font-size: 20px;");
        b3.setStyle("-fx-font-size: 20px;");
        b3.setOnAction(actionEvent -> Platform.exit());
        b2.setOnAction(actionEvent -> this.goGameLogic.resetGame());
        vb.getChildren().addAll (b1, b2, b3);
    }




    private GameLogic goGameLogic;
    private Label tf_score;
    private VBox vb;
    public static Button b1,b2,b3;





}

