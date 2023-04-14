package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    //Define arrayList to store x and y coordinates of numbers/positions
    public static ArrayList<Pair<Integer, Integer>> positionCoordinates;
    public static ArrayList<Integer> snakeAndLadder;
    public Board(){
        positionCoordinates = new ArrayList<>();
        snakeAndLadder = new ArrayList<>();
        insertPositions();
        populateSnakeAndLadder();
    }
    public void insertPositions(){
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                // x coordinate
                int xCoordinate = 0;
                if(i % 2 == 0){
                    xCoordinate = (j * SnakeLadder.tileSize) + (SnakeLadder.tileSize / 2);
                }else{
                    xCoordinate = (SnakeLadder.height * SnakeLadder.tileSize) - (j * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                }
                //y coordinate
                int yCoordinate = (SnakeLadder.height * SnakeLadder.tileSize) - (i * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                positionCoordinates.add(new Pair<>(xCoordinate,yCoordinate));
            }
        }
    }
    public void populateSnakeAndLadder(){
        for(int i=0; i<101; i++){
            snakeAndLadder.add(i);
        }
        //Ladders
        snakeAndLadder.set(2,38);
        snakeAndLadder.set(8,31);
        snakeAndLadder.set(21,42);
        snakeAndLadder.set(46,84);
        snakeAndLadder.set(51,67);
        snakeAndLadder.set(71,91);
        snakeAndLadder.set(80,99);
        //Snakes
        snakeAndLadder.set(33,5);
        snakeAndLadder.set(63,16);
        snakeAndLadder.set(54,34);
        snakeAndLadder.set(97,61);
        snakeAndLadder.set(93,74);
    }

    public int getNewPosition(int currentPosition){
        if(currentPosition > 0 && currentPosition <= 100){
            return snakeAndLadder.get(currentPosition);
        }
        return -1;
    }
    int getXCoordinate(int position){
        if(position >= 1 && position <= 100) return positionCoordinates.get(position).getKey();
        return -1;
    }int getYCoordinate(int position){
        if(position >= 1 && position <= 100) return positionCoordinates.get(position).getValue();
        return -1;
    }
}
