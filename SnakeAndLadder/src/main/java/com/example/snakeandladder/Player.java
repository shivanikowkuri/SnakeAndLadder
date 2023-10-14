package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
   private  String Name;
    private int curPos;
    private static Board gameBoard= new Board();


    public Player(int tileSize, Color coinColor, String name)
    {
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        curPos=0;
        Name=name;
        move(1);


    }


    // now we have to move the coin
    public void move(int DiceValue)
    {
        if(curPos+DiceValue <= 100)
        {
            // we have to change value
            curPos+=DiceValue;

            TranslateTransition secondmove = null, firstmove=transtion();


            int newPos = gameBoard.getSnakeLadder(curPos);
            if(newPos != curPos && newPos != -1)
            {
                curPos=newPos;
                secondmove=transtion();
            }
            if(secondmove == null)
            {
                firstmove.play();
            }
            else {
                SequentialTransition sq= new SequentialTransition(firstmove, new PauseTransition(Duration.millis(1000)), secondmove);
                sq.play();
            }

        }

    }

    private TranslateTransition transtion()
    {
        TranslateTransition animate = new TranslateTransition(Duration.millis(600), coin);
        // we have to change the pos of coin in the Board
        animate.setToX(gameBoard.getXcod(curPos));
        animate.setToY(gameBoard.getYcod(curPos));
        animate.setAutoReverse(false);
        return animate;

    }
    public String getName() {
        return Name;
    }

    public int getCurPos() {
        return curPos;
    }

    public Circle getCoin() {
        return coin;
    }
    public boolean isWinner()
    {
        if(curPos==100)
        {
            return true;
        }
        return false;

    }
    public void posStart()
    {
        curPos=0;
        move(1);
    }
  
}
