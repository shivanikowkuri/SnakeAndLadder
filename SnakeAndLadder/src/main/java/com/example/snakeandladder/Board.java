package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
     ArrayList<Pair<Integer, Integer>> PosCordinates ;
     ArrayList<Integer> snakeLadder ;
    public Board()
    {
        PosCordinates = new ArrayList<>();
        boardTraversal();
        travelofSnakeLadder();
    }
    private void boardTraversal ()
    {
        PosCordinates.add(new Pair<>(0,0)); // dummy
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                // X coordinates
                int xCod=0;
                if(i%2==0)
                {
                    xCod=j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }
                else {
                    xCod=SnakeLadder.tileSize*SnakeLadder.height - (j*SnakeLadder.tileSize)- SnakeLadder.tileSize/2;
                }

                // Y coordinates to travel
                int yCod = SnakeLadder.tileSize*SnakeLadder.height - (i*SnakeLadder.tileSize)- SnakeLadder.tileSize/2;
                PosCordinates.add(new Pair<>(xCod, yCod));

            }
        }
    }

    // get the index of snake
    private void travelofSnakeLadder()
    {
        snakeLadder = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadder.add(i);
        }
        snakeLadder.set(4,25);
        snakeLadder.set(13, 46);
        snakeLadder.set(27, 5);
        snakeLadder.set(33, 49);
        snakeLadder.set(40, 3);
        snakeLadder.set(42,63);
        snakeLadder.set(43, 18);
        snakeLadder.set(50, 69);
        snakeLadder.set(54, 31);
        snakeLadder.set(62, 81);
        snakeLadder.set(66, 45);
        snakeLadder.set(74, 92);
        snakeLadder.set(76, 58);
        snakeLadder.set(89, 53);
        snakeLadder.set(99, 41);

    }



    // we have to get X coordinate of position
    public int getXcod (int Position)
    {
        if(Position>=1 && Position<=100)
            return PosCordinates.get(Position).getKey();
        return -1;
    }

    public int getYcod (int Position)
    {
        if(Position>=1 && Position<=100)
            return PosCordinates.get(Position).getValue();
        return -1;
    }

    public int getSnakeLadder(int Position)
    {
        if(Position>=1 && Position<=100)
        {
            return snakeLadder.get(Position);
        }
        return -1;
    }



}
