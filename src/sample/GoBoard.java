package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

class GoBoard extends Pane{

    // rectangle that makes the background of the board
    private Rectangle background;
    // arrays for the lines that makeup the horizontal and vertical grid lines
    private Line[] horizontal;
    private Line[] vertical;
    // arrays holding translate objects for the horizontal and vertical grid lines
    private Translate[] horizontal_t;
    private Translate[] vertical_t;
    // arrays for the internal representation of the board and the pieces that are
    // in place
    private GOPiece[][] render;
    // the current player who is playing and who is his opposition
    private int current_player;
    private int opposing;
    // is the game currently in play
    private boolean in_play;
    // current scores of player 1 and player 2
    private int player1_score;
    private int player2_score;
    // the width and height of a cell in the board
    private double cell_width;
    private double cell_height;

    //For KO Rule
     int chain1[] = new int[8];



    public GoBoard() {
        render = new GOPiece[7][7] ; // array that holds all pieces
        horizontal = new Line[7];
        vertical = new Line[7];
        horizontal_t = new Translate[7];
        vertical_t = new Translate[7];

        initialiseLinesBackground();
        initialiseRender();
        resetGame();
    }
    public void placePiece(final double x, final double y) {
        // public method that will try to place a piece in the given x,y coordinate

            // figure out which cell the current player has clicked on
            final int cellx = (int) (x / cell_width);
            final int celly = (int) (y / cell_height);

            // if the game is not in play then do nothing
            if(!in_play)
                return;

            if(!validCo(cellx, celly))
                return;

            // if there is a piece already placed then return and do nothing
            if(render[cellx][celly].getPiece() != 0)
                return;

           // determine what pieces surround the current piece. if there is no opposing
            // pieces then a valid move cannot be made.



            // see if a reverse can be made in any direction if none can be made then return
            if(!validMove(cellx, celly))
                return;

            // at this point we have done all the checks and they have passed so now we can place
            // the piece and perform the reversing also check if the game has ended
            //placeAndReverse(cellx, celly);

            // if we get to this point then a successful move has been made so swap the
            // players and update the scores
            //swapPlayers();
            //updateScores();
            //determineEndGame();



            System.out.println("Count Liberties : "+countLiberties(cellx,celly));



        this.render[cellx][celly].setPiece(this.current_player);
            // print out some information
            System.out.println("placed at: " + cellx + ", " + celly);
            System.out.println("White: " + player1_score + " Black: " + player2_score);
            if(current_player == 1)
                System.out.println("current player is White");
            else
                System.out.println("current player is Black");

        KO(cellx,celly);
        swapPlayers();
        updateScores();

    }
    //resize background
    public void resize(double width, double height) {
        //call the superclass resize method.
        super.resize(width,height);

        //Determine the width and height of each cell in the board based on the new width and height (store in cell_width, and cell_height respectively)

        cell_height = height / 7.0; cell_width = width / 7.0;

        //resize the background rectangle to take the full width and height of the window.

        // resize the rectangle to take the full size of the widget
        background.setWidth(width); background.setHeight(height);

        horizontalResizeRelocate(width);
        verticalResizeRelocate(height);
        pieceResizeRelocate();
    }



    //method to reset game
    public void resetGame() {
        resetRenders();
        initialiseRender();


        for(int i=0; i<render.length; i++)
            for(int j=0; j<render[i].length; j++)
                getChildren().add(render[i][j]);

        in_play = true; current_player = 2; opposing = 1;
        player1_score = 2; player2_score = 2;
    }

    public void resetRenders() {
        for(int i=0; i<render.length; i++) {
            for(int j=0; j<render[i].length; j++) {
                render[i][j].setPiece(0);
            }
        }
    }

    //initialise lines on board
    private void initialiseLinesBackground() {
        background = new Rectangle();
        background.setFill(Color.rgb(242, 205, 113));
        getChildren().add(background);
        for(int i=0 ; i<7 ; i++) {
            horizontal[i]=new Line();
            horizontal[i].setStroke(Color.BLACK);
            horizontal[i].setStartX(0); horizontal[i].setStartY(0); horizontal[i].setEndY(0);
            horizontal_t[i] = new Translate(0, 0);
            horizontal[i].getTransforms().add(horizontal_t[i]);


            vertical[i]=new Line();
            vertical[i].setStroke(Color.BLACK);
            vertical[i].setStartX(0); vertical[i].setStartY(0); vertical[i].setEndY(0);
            vertical_t[i] = new Translate(0, 0);
            vertical[i].getTransforms().add(vertical_t[i]);

            getChildren().addAll(horizontal[i], vertical[i]);
        }
    }

    //method for resizing and relocating the horizontal Lines
    private void horizontalResizeRelocate(final double width) {
        for(int i=0 ; i<7 ; i++) {
            horizontal_t[i].setY((i+.5)*cell_height);
            horizontal[i].setEndX(width-cell_width/2);
            horizontal[i].setStartX(cell_width/2);
        }
    }


    //method for resizing and relocating the vertical lines
    private void verticalResizeRelocate(final double height) {
        for(int i=0 ; i<7 ; i++) {
            vertical_t[i].setX((i+.5)*cell_width);
            vertical[i].setEndY(height-cell_height/2);
            vertical[i].setStartY(cell_height/2);
        }
    }

    //method for swapping the players
    private void swapPlayers() {


        if(current_player == 1 && opposing == 2) {
            current_player = 2;
            opposing = 1;
        }

        else if(current_player == 2 && opposing == 1) {
            current_player = 1;
            opposing = 2;
        }


    }

    //method to update score
    private void updateScores() {
        int scoreCounter;
        this.player1_score = 0; this.player2_score = 0;
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                scoreCounter = this.render[i][j].getPiece();
                if (scoreCounter == 1) ++this.player1_score;
                else if (scoreCounter == 2) ++this.player2_score;
            }
        }
    }

    //method for resizing and relocating all the pieces
    private void pieceResizeRelocate() {
        for (int i = 0; i < render.length; i++){
            for (int j = 0; j < render[i].length; j++){
                render[i][j].resize(cell_width,cell_height);
                render[i][j].relocate(cell_width * i, cell_height * j);
            }
        }
    }

    //method to return piece
    private int getPiece() {
        return current_player;
    }

    //method to determine game end
    private void determineEndGame() {}

    //method for determining winner
    private void determineWinner() {

        this.updateScores();

        if (this.player1_score == this.player2_score) System.out.println("Draw");

        else if (this.player1_score > this.player2_score) System.out.println("Player 1 (White) won with " + this.player1_score);

        else System.out.println("Player 2 (Black) won with " + this.player2_score);

    }

    //method for initialising render
    private void initialiseRender() {
        for(int i=0 ; i<render.length ; i++) {
            for(int j=0; j<render[i].length ; j++) {
                render[i][j] = new GOPiece(0);
            }
        }
    }

    private int getPiece(final int x, final int y) {

        if (this.validCo(x, y)) return (render[x][y].getPiece());
        return -1;
    }
    private boolean validCo(final int x, final int y) {
        if ((x >= 0 && x < 7 && (y >= 0 && y < 7))) return true;
        return (false);
    }
    private int countLiberties(int x,  int y) {
        int liberties = 0;


            if (this.getPiece(x - 1, y) == 0) ++liberties;
            if (this.getPiece(x + 1, y) == 0) ++liberties;
            if (this.getPiece(x, y - 1) == 0) ++liberties;
            if (this.getPiece(x, y + 1) == 0) ++liberties;

        return liberties;
    }
    public void KO(int x, int y ){




        if (

                        this.getPiece(x - 1, y) == opposing &&
                        this.getPiece(x + 1, y) == opposing &&
                        this.getPiece(x, y - 1) == opposing &&
                        this.getPiece(x, y + 1) == opposing


        ){
            this.render[x][y].setPiece(this.opposing);

            updateScores();

            System.out.println("Current Score"+player1_score+ "  Opposing"+player2_score);

        }




    }
    public boolean validMove(int x, int y){
        if(this.render[x][y].getPiece() != 0)return (false);
       // if (this.countLiberties(x, y) <= 0) return (false);
        return (true);
    }


}