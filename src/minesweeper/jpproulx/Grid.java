package minesweeper.jpproulx;

import java.awt.*;
import java.util.ArrayList;

public class Grid {

    private int size;
    private Tile[][] grid;
    private int numBombs;

    public Grid(int size, int numBombs) {

        if (numBombs > Math.pow(size, 2)) {
            //Dont f w me
            throw new IndexOutOfBoundsException("More bombs than tiles!!!");
        }

        this.numBombs = numBombs;

        this.size = size;
        grid = new Tile[size][size];
        populateTiles();
    }

    private void populateTiles() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Tile(j * Tile.TILE_WIDTH, i * Tile.TILE_HIEGHT);
            }
        }
    }

    private void populateBombs() {
        
    }

    public void draw(Graphics g) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile tempTile = grid[i][j];
                tempTile.draw(g);
            }
        }
    }

    public Tile handleClick(int x, int y) {
        Point newXY = adjustXY(x ,y);
        int newX = (int)newXY.getX();
        int newY = (int)newXY.getY();

        grid[newY][newX].isClicked = true;
        return grid[newY][newX];
    }

    private Point adjustXY(int x, int y) {
        int newX = 0;
        if (x % Tile.TILE_WIDTH != 0) {
            newX = x - (x % Tile.TILE_WIDTH);
        } else {
            newX = x;
        }
        newX = newX / Tile.TILE_WIDTH;

        int newY = 0;
        if (y % Tile.TILE_HIEGHT != 0) {
            newY = y - (y % Tile.TILE_HIEGHT);
        } else {
            newY = y;
        }
        newY = newY / Tile.TILE_HIEGHT;

        Point returnPoint = new Point(newX, newY);
        return returnPoint;
    }
}
