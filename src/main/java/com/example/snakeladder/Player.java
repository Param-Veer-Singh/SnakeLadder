package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static com.example.snakeladder.Board.snakeAndLadder;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String playerName;

    private static Board gameBoard = new Board();

    Player(int tileSize,Color coinColor, String playerName ){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        this.playerName = playerName;
        currentPosition = 0;
        movePlayer(1);
    }
    public void movePlayer(int diceValue){
        if(currentPosition + diceValue <= 100){
            currentPosition += diceValue;

            TranslateTransition firstTransition = translateAnimation(diceValue), secondTransition = null;

            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(currentPosition != newPosition && currentPosition != -1){
                currentPosition = newPosition;
                secondTransition = translateAnimation(6);
            }
            if(secondTransition == null) firstTransition.play();
            else {
                SequentialTransition sequentialTransition = new SequentialTransition(firstTransition ,
                        new PauseTransition(Duration.millis(800)), secondTransition);
                sequentialTransition.play();
            }
        }
//        int xPos = gameBoard.getXCoordinate(currentPosition);
//        int yPos = gameBoard.getYCoordinate(currentPosition);
//        coin.setLayoutX(xPos);
//        coin.setLayoutY(yPos);
    }

    public TranslateTransition translateAnimation(int diceValue){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100*diceValue),coin);
        translateTransition.setToX(gameBoard.getXCoordinate(currentPosition));
        translateTransition.setToY(gameBoard.getYCoordinate(currentPosition));
        translateTransition.setAutoReverse(false);
        return translateTransition;
    }
    boolean isWinner(){
        return currentPosition == 100;
    }
    public void moveToInitial(){
        currentPosition = 0;
        movePlayer(1);
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getPlayerName() {
        return playerName;
    }
}
