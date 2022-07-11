package com.project.gameoflife;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameOfLifeController implements Initializable {

    private GameOfLifePane gamePane = new GameOfLifePane();

    private Double rate = 0.25;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ScrollBar scroll;

    private Integer nextGenCounter  = 0;

    @FXML
    private Label counter;


    int[][] grid = {
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

    };

    GameOfLifeModel gameOfLifeModel = new GameOfLifeModel(grid);


    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(rate),
            e ->{
                   nextGenCounter++;
                   grid = gameOfLifeModel.nextGen();
                   gamePane.paint(grid);
                   counter.setText(nextGenCounter.toString());
                }
            ));


    /**
     * Generates new generations
     * @param event
     */
    @FXML
    protected void nextGen(MouseEvent event)
    {
        nextGenCounter++;
         grid = gameOfLifeModel.nextGen();
         gamePane.paint(grid);
        counter.setText(nextGenCounter.toString());
    }

    /**
     * This runs at initialization
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        borderPane.setCenter(gamePane);
        timeline.rateProperty().bind(scroll.valueProperty());

        gamePane.widthProperty().addListener( e -> gamePane.paint(grid));
        gamePane.paint(grid);

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * This starts the application
     * @param event
     */
    @FXML
    void startAnimation(MouseEvent event)
    {
        if(timeline.getStatus() == Animation.Status.RUNNING)
        {
            System.out.println("Timeline already running");
        }else
        {
            grid = gameOfLifeModel.nextGen();
            gamePane.paint(grid);
            timeline.play();
        }
    }

    @FXML
    void stopAnimation(MouseEvent event)
    {
        timeline.stop();
        System.out.println("Animation stopped");
    }

    /**
     * This spawns new seeds which in turn changes the pattern
     * of new generations
     * @param mouseEvent
     */
    @FXML
    public void changeSeed(MouseEvent mouseEvent)
    {
        Random rand = new Random();

        //loops through the grid and generates new patterns
        for(int i=0; i< grid.length; i++)
        {
            for(int j=0; j< grid[i].length; j++)
            {
                grid[i][j] = rand.nextInt(2);
            }
        }
        gamePane.paint(grid);
    }

}