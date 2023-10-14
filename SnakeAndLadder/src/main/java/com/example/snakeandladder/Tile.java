package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    public Tile(int tile)
    {
        setWidth(tile);
        setHeight(tile);
        setFill(Color.AQUA);
        setStroke(Color.BLACK);
    }
}
