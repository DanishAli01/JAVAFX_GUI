package sample;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameLogic {

    public GameLogic(GoBoard goBoard) {
        super();
        this.goBoard = 	goBoard;
        this.score = 1;
        //Making a SimpleIntegerProperty which will bind to the TextField in the controlPanel
        this.scoreProperty = new SimpleIntegerProperty(this.score);
    }

    public GoBoard getBoard() {
        return goBoard;
    }

    public void resetGame() {
        this.goBoard.resetGame();
    }





    // This method is called when binding the SimpleIntegerProperty scoreProperty in this class to the TextField tf_score in controlPanel
    public IntegerProperty getScore() {
        return scoreProperty;
    }

    private Integer score;
    private IntegerProperty scoreProperty;
    private GoBoard goBoard;




}
