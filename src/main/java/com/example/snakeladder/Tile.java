package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int tileSize){
        //Set width of tile
        setWidth(tileSize);
        //Set height of tile
        setHeight(tileSize);
        //Set color of tile
        setFill(Color.RED);
        //Set border color of tile
        setStroke(Color.BLACK);
    }
}
