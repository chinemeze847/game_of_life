package com.project.gameoflife;

public class GameOfLifeModel
{
    private int[][] grid;
    int row,col;

    public GameOfLifeModel(int[][] grid)
    {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
    }

    public int[][] nextGen()
    {
        int[][] futureGen = new int[row][col];

        //looping through every cell
        for(int i = 1; i < row-1; i++)
        {
            for(int j = 1; j < col - 1; j++)
            {
                //find the neighbours that are alive
                int aliveNeighbors = 0;

                for (int k = -1; k <= 1; k++)
                {
                    for (int l = -1; l <= 1; l++)
                          aliveNeighbors += grid[i + k][j +l];
                }

                //removing if it was counted as neighbours
                aliveNeighbors -= grid[i][j];

                //Implementing the rule of life

                //if cell is lonely it dies
                if ((grid[i][j] == 1) && (aliveNeighbors < 2))
                    futureGen[i][j] = 0;

                //cell dies due to over population
                else if ((grid[i][j] == 1) && (aliveNeighbors > 3))
                    futureGen[i][j] = 0;

                //A new cell is created
                else if ((grid[i][j] == 0 ) && (aliveNeighbors == 3))
                    futureGen[i][j] = 1;

                //remains the same if it doesn't fall in any of the above
                else
                    futureGen[i][j] = grid[i][j];
            }

        }
        grid = futureGen;
        return  grid;
    }
}
