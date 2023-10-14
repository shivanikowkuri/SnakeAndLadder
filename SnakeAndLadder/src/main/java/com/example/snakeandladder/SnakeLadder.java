package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize =40, height=10, width=10;
    public static final int buttonLineY= tileSize*height+50;
    public static  final int displayLine= buttonLineY-30;

    private static Dice dice= new Dice();
    private Player player1, player2;

    private boolean gameStart=false, player1Turn=false, player2turn=false;

    private Pane createContent()
    {
        Pane root = new Pane();
        root.setPrefSize(tileSize *width, tileSize * height+100 ); // here 50 for extra space
        // setPreSize --> set the boundary of layout
        // Add the shape to the Layout
        // Make a box grid
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Tile tile = new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().add(tile);

            }

        }
        // Grid is completed here
        // Put a image here

        Image img = new Image("E:\\JAVA\\SnakeAndLadder\\src\\main\\istockphoto-531466314-1024x1024.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
       board.setFitWidth(width*tileSize);
       board.setFitHeight(height*tileSize);
        root.getChildren().add(board);
        // adding three buttons below the board
        Button p1= new Button("Player One");
        Button p2= new Button("Player Two");
        Button Start= new Button("Start");
        p1.setTranslateX(20);
        p1.setTranslateY(buttonLineY);
        p1.setDisable(true);

        p2.setTranslateX(300);
        p2.setTranslateY(buttonLineY);
        p2.setDisable(true);

        Start.setTranslateX(180);
        Start.setTranslateY(buttonLineY);

        root.getChildren().addAll(p1, p2, Start);

        Label p1Lable = new Label(" ");
        Label p2Lable = new Label(" ");
        Label diceLabel = new Label("Start game..");
        p1Lable.setTranslateX(20);
        p1Lable.setTranslateY(displayLine);

        p2Lable.setTranslateX(300);
        p2Lable.setTranslateY(displayLine);

        diceLabel.setTranslateX(180);
        diceLabel.setTranslateY(displayLine);

        root.getChildren().addAll(p1Lable, p2Lable, diceLabel);

        // Adding GUI(coin) of two player
        player1 = new Player(tileSize, Color.BLACK, "Rahul");
        player2 = new Player(tileSize-5, Color.WHITE, "Aman");
        root.getChildren().addAll(player1.getCoin(), player2.getCoin());

        // Move the players
        p1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart)
                {
                    if(player1Turn)
                    {
                        int diceVal=Dice.getDiceVal();
                        diceLabel.setText("Dice Value : "+ diceVal);
                        player1.move(diceVal);
                        // winning condition
                        if(player1.isWinner())
                        {
                            diceLabel.setText(player1.getName()+ " is winner");
                            player2turn=false;
                            p1.setDisable(true);
                            p1Lable.setText(" ");

                            p2.setDisable(true);
                            p2Lable.setText(" ");
                            player1Turn=true;

                            Start.setDisable(false);
                            diceLabel.setText("Restart game");


                        }
                        else
                        {
                            player1Turn=false;
                            p1.setDisable(true);
                            p1Lable.setText(" ");

                            player2turn=true;
                            p2.setDisable(false);
                            p2Lable.setText("Your turn : "+ player2.getName());
                        }

                    }
                }


            }
        });

        p2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart)
                {
                    if(player2turn)
                    {
                        int diceVal=Dice.getDiceVal();
                        diceLabel.setText("Dice Value : "+ diceVal);
                        player2.move(diceVal);
                        // winning condition
                        if(player2.isWinner())
                        {
                            diceLabel.setText(player2.getName()+ " is winner");
                            player1Turn=false;
                            p1.setDisable(true);
                            p1Lable.setText(" ");

                            p2.setDisable(true);
                            p2Lable.setText(" ");
                            player2turn=true;
                            Start.setDisable(false);
                            Start.setText("ReStart");


                        }
                        else
                        {
                            player2turn=false;
                            p2.setDisable(true);
                            p2Lable.setText(" ");

                            player1Turn=true;
                            p1.setDisable(false);
                            p1Lable.setText("Your turn : "+ player2.getName());
                        }

                    }
                }


            }
        });
        Start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart=true;
                diceLabel.setText("Game is Started !");
                Start.setDisable(true);
                player1Turn=true;
                player1.posStart();
                p1Lable.setText("Your Turn : "+ player1.getName());
                p1.setDisable(false);
                player2turn=false;
                p2Lable.setText(" ");
                p2.setDisable(true);
                player2.posStart();

            }
        });

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}