package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class SnakeLadder extends Application {
    /*
    Set tile size as 40 pixels
    Set width to 10, to set 10 tiles for each row
    Set height to 10, to set 10 tiles for each column
     */
    public static final int tileSize = 40, height = 10, width = 10;

    //Set line for buttons below the board;
    public static final int buttonline = height * tileSize + 50;
    //Set display line
    public static final int displayline = height * tileSize + 30;
    //Declare dice, we need only one dice
    private static Dice dice = new Dice();
    //Initiate players
    private Player playerOne,playerTwo;
    private boolean startGame = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent(){
        //Create new pane
        Pane root = new Pane();
        //Set size of pane, add 50 pixels to height so as to include extra buttons
        root.setPrefSize(tileSize * width, tileSize * height + 100);

        //Using nested loop add tiles and set layout
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //Create new tile
                Tile tile = new Tile(tileSize);
                //Set x layout
                tile.setLayoutX(j*tileSize);
                //Set y layout
                tile.setLayoutY(i*tileSize);
                //Add tile to root pane
                root.getChildren().add(tile);
            }
        }

        //Add image of snake and ladder
        Image image = new Image("C:\\Users\\krish\\IdeaProjects\\SnakeLadder\\src\\main\\resources\\board.png");
        //Add image board
        ImageView board = new ImageView();
        //Set image to board
        board.setImage(image);
        //Fit the size of image to board
        board.setFitHeight(height * tileSize);
        board.setFitWidth(width * tileSize);

        //Insert buttons
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start Game");

        //Set button position
        playerOneButton.setLayoutY(buttonline);
        playerOneButton.setLayoutX(30);

        playerTwoButton.setLayoutY(buttonline);
        playerTwoButton.setLayoutX(300);

        //Initially disable both button
        playerOneButton.setDisable(true);
        playerTwoButton.setDisable(true);

        startButton.setLayoutY(buttonline);
        startButton.setLayoutX(160);

        //Add display label
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label startLabel = new Label("Start Game");

        //Set display label position
        playerOneLabel.setLayoutY(displayline);
        playerOneLabel.setLayoutX(30);

        playerTwoLabel.setLayoutY(displayline);
        playerTwoLabel.setLayoutX(300);

        startLabel.setLayoutY(displayline);
        startLabel.setLayoutX(160);

        playerOne = new Player(tileSize-5, Color.BLUE,"P1");
        playerTwo = new Player(tileSize-7, Color.BROWN, "P2");

        //Start Game
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startGame = true;
                startButton.setDisable(true);
                startLabel.setText("Game Start !");
                playerOneTurn = true;
                playerOneButton.setDisable(false);
                playerOneLabel.setText("Your turn " + playerOne.getPlayerName());
                playerTwoButton.setDisable(true);
                playerTwoLabel.setText("");

                playerOne.moveToInitial();
                playerTwo.moveToInitial();
            }
        });
        //Moving players
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startGame && playerOneTurn){
                    int diceValue = dice.rolledDiceValue();
                    startLabel.setText("Dice Value : " + diceValue);
                    playerOne.movePlayer(diceValue);
                    playerOneTurn = false;
                    playerOneButton.setDisable(true);
                    //Wining condition
                    if(playerOne.isWinner()){
                        startLabel.setText("Winner is " + playerOne.getPlayerName());
                        playerOneTurn = false;
                        playerTwoTurn = false;
                        playerOneButton.setDisable(true);
                        playerTwoButton.setDisable(true);
                        playerOneLabel.setText("");
                        playerTwoLabel.setText("");
                        startButton.setText("Restart Game");
                        startButton.setDisable(false);
                        startGame = false;
                    }else{
                        playerTwoButton.setDisable(false);
                        playerOneLabel.setText("");
                        playerTwoLabel.setText("Your turn " + playerTwo.getPlayerName());
                        playerTwoTurn = true;
                    }
                }
            }
        });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startGame && playerTwoTurn){
                    int diceValue = dice.rolledDiceValue();
                    startLabel.setText("Dice Value : " + diceValue);
                    playerTwo.movePlayer(diceValue);
                    playerTwoTurn = false;
                    playerTwoButton.setDisable(true);
                    if(playerTwo.isWinner()){
                        startLabel.setText("Winner is " + playerOne.getPlayerName());
                        playerOneTurn = false;
                        playerTwoTurn = false;
                        playerOneButton.setDisable(true);
                        playerTwoButton.setDisable(true);
                        playerOneLabel.setText("");
                        playerTwoLabel.setText("");
                        startButton.setText("Restart Game");
                        startButton.setDisable(false);
                        startGame = false;
                    }else{
                        playerOneButton.setDisable(false);
                        playerTwoLabel.setText("");
                        playerOneLabel.setText("Your turn " + playerOne.getPlayerName());
                        playerOneTurn = true;
                    }
                }
            }
        });
        //Add board , buttons , label to root pane
        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,
                playerOneLabel, playerTwoLabel, startLabel, playerOne.getCoin(),playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene1 = new Scene((createContent()));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake And Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}