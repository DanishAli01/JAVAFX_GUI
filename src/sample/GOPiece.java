package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

//class definition for a reversi piece
class GOPiece extends Group {
    // default constructor for the class
    public GOPiece(int player) {
        this.player = player;
        piece = new Ellipse();
        t = new Translate();


        if(player == 1)
            piece.setFill(Color.WHITE);

        else if(player == 2)
            piece.setFill(Color.BLACK);

        else
            piece.setFill(Color.TRANSPARENT);


        getChildren().add(piece);
        piece.getTransforms().add(t);
    }

    // overridden version of the resize method to give the piece the correct size
    public void resize(double width, double height) {
        super.resize(width,height);
        piece.setCenterX(width / 2); piece.setCenterY(height / 2);
        piece.setRadiusX(width / 2); piece.setRadiusY(height / 2);
    }

    // overridden version of the relocate method to position the piece correctly
    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        t.setX(x); t.setY(y);
    }

    // public method that will swap the colour and type of this piece
    public void swapPiece() {
        piece = new Ellipse();
        this.player = player;

        if(player == 1)
            piece.setFill(Color.BLACK);

        else
            piece.setFill(Color.WHITE);

        getChildren().add(piece);
    }

    // method that will set the piece type
    public void setPiece(final int type) {
        if(type == 0) piece.setVisible(false);
        else {
            player = type;
            piece.setVisible(true);
            if(type==1) piece.setFill(GAME_WHITE_COLOR);
            if(type==2) piece.setFill(GAME_BLACK_COLOR);

        }
    }

    // returns the type of this piece
    public int getPiece() {
        // NOTE: this is to keep the compiler happy until you get to this point
        return player;
    }

    // private fields
    private int player;		// the player that this piece belongs to
    private Ellipse piece;	// ellipse representing the player's piece
    private Translate t;	// translation for the player piece
    public static RadialGradient GAME_BLACK_COLOR = new RadialGradient(0.5, 0.5, 0, 0, 1.5, true, CycleMethod.REFLECT, new Stop(0, Color.DARKSLATEGREY), new Stop(1, Color.BLACK));
    public static RadialGradient GAME_WHITE_COLOR = new RadialGradient(0.5, 0.5, 0, 0, 1.5, true, CycleMethod.REFLECT, new Stop(0, Color.WHITE), new Stop(1, Color.GREY));

}
