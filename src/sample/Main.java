package sample;

//imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;

import static java.awt.SystemColor.TEXT;
import static java.awt.SystemColor.text;

//class defnition f
public class Main extends Application {
    // overridden init method
    public void init() {
        bp_layout = new BorderPane();				    // create layout
        board = new GoBoard();					        // creates a board
        gameLogic = new GameLogic(board); 	        // create gameLogic and pass board to gameLogic so gameLogic can call methods from board
        customControl = new GoCustomControl(gameLogic); // create customControl and pass gameLogic to customControl so customControl can call methods from gameLogic
        controlPanel = new GoControlPanel(gameLogic);   // create controlPanel and pass gameLogic to controlPanel so customControl can call methods from gameLogicogic
        BorderPane.setAlignment(controlPanel, Pos.CENTER);
        BorderPane.setMargin(controlPanel, new Insets(300,12,12,12));
        bp_layout.setCenter(customControl);				// put the customControl in the center of the layout
        bp_layout.setLeft(controlPanel);




    }
/*
    // overridden start method
    public void start(Stage primaryStage) {
        // set a title, size and the stack pane as the root of the scene graph
        primaryStage.setTitle("Go");
        primaryStage.setScene(new Scene(bp_layout, 950, 800));
        primaryStage.show();


    }

*/
@Override
public void start(final Stage primaryStage) {
    primaryStage.setTitle("Go");

    Group root3 = new Group();

    final Scene scene1 = new Scene(bp_layout, 950, 800);
    final Scene scene3 = new Scene(root3, 950, 800);



    VBox v = new VBox();


    Button b2 = new Button("Back To Game");
    b2.setStyle("-fx-font-size: 20px;");
    b2.setAlignment(Pos.BOTTOM_LEFT);


    Text m = new Text(20,20,"How to Play");
    m.setStyle("-fx-font-weight: bold;");




    Text t = new Text (40, 50, "Although the normal size of a Go board is 19 by 19 lines, it is possible to use smaller sizes.\n " +
            "A quick game can be played on a 13 by 13 board without losing the essential character of the game.\n The following examples all use a 9 by 9 board." +
            " We recommend that beginners learn the basics on a 9 by 9 board, moving up to a 13 by 13 board after a\n few games and only playing on a 19 by 19 board " +
            "if you can play a complete game within 15 minutes and are comfortable with some of the strategic concepts");

   // t.setFont(Font.font ("Verdana", 10));
    m.setFont(Font.font ("Verdana", 20));

    v.getChildren().addAll (m,t,b2);

    root3.getChildren().addAll(v);

    GoControlPanel.b1.setOnAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            primaryStage.setScene(scene3);
        }
    });

    b2.setOnAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            primaryStage.setScene(scene1);
        }
    });


    primaryStage.setScene(scene1);
    primaryStage.show();
}

    // overridden stop method
    public void stop() {

    }

    // entry point into our program for launching our javafx applicaton
    public static void main(String[] args) {
        //T1
        launch(args);
    }

    private Stage stage;
    private Pane root;

    // private fields
    private BorderPane bp_layout, gp_layout;
    private GoCustomControl customControl;
    private GoControlPanel controlPanel;
    private GameLogic gameLogic;
    private GoBoard board;
    private GoCustomControl gc_go;

}

