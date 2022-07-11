package com.project.gameoflife;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GameOfLifePane  extends Pane
{
    public void paint(int[][] grid)
    {
        int numOfSquares = grid.length;
        double width = getWidth();
        double height = getHeight();
        double  squareSize = width / numOfSquares;

        for (int i  = 0; i < numOfSquares; i++)
        {
            for(int j=0; j < numOfSquares; j++)
            {
                //creating points that will connect to make the individual squares
                Point2D p1 = new Point2D(i * squareSize,j * squareSize);
                Point2D p2 = new Point2D(i * squareSize,(j*squareSize) + squareSize);
                Point2D p3 = new Point2D((i * squareSize) + squareSize,(j * squareSize) + squareSize);
                Point2D p4 = new Point2D((i * squareSize) + squareSize,j * squareSize);

                Polygon square = new Polygon();

                //connecting the points together to make a square
                square.getPoints().addAll(p1.getX(),p1.getY(),p2.getX(),
                        p2.getY(), p3.getX(), p3.getY(),p4.getX(),p4.getY());


                //if alive draw a black square else draw a white square
                if(grid[j][i] == 1)
                {
                    square.setStroke(Color.RED);
                    square.setFill(Color.RED);
                }else
                {
                    square.setStroke(Color.WHITE);
                    square.setFill(Color.WHITE);
                }

                this.getChildren().add(square);
            }
        }
    }
}
