package minesweeper.jpproulx;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int size;
    private Tile[][] grid;
    private int numBombs;
    public boolean assignedBombs = true;

    public Grid(int size, int numBombs) {

        if (numBombs > Math.pow(size, 2)) {
            //Dont f w me
            throw new IndexOutOfBoundsException("More bombs than tiles!!!");
        }

        this.numBombs = numBombs;

        this.size = size;
        grid = new Tile[size][size];
        populateTiles();
        assignNeighborCounts();
    }

    private void populateTiles() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Tile(j * Tile.TILE_WIDTH, i * Tile.TILE_HIEGHT);
            }
        }
    }

    public void populateBombs() {
        ArrayList<Point> pointsWithoutBombs = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pointsWithoutBombs.add(new Point(i, j));
            }
        }
        //Assign bombs to a remaining tile which doesn't have a bomb.
        for (int i = 0; i < this.numBombs; i++) {
            int pointIndex = ThreadLocalRandom.current().nextInt(0, pointsWithoutBombs.size());
            if (!grid[(int)pointsWithoutBombs.get(pointIndex).getY()][(int)pointsWithoutBombs.get(pointIndex).getX()].isClicked) {
                grid[(int)pointsWithoutBombs.get(pointIndex).getY()][(int)pointsWithoutBombs.get(pointIndex).getX()].giveBomb();
            }
            pointsWithoutBombs.remove(pointIndex);
        }

    }

    public void assignNeighborCounts() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = 0;
                try {
                    if (grid[i - 1][j - 1].hasBomb()) { count++; }
                    if (grid[i][j - 1].hasBomb()) { count++; }
                    if (grid[i - 1][j].hasBomb()) { count++; }
                    if (grid[i - 1][j + 1].hasBomb()) { count++; }
                    if (grid[i + 1][j - 1].hasBomb()) { count++; }
                    if (grid[i + 1][j + 1].hasBomb()) { count++; }
                    if (grid[i + 1][j].hasBomb()) { count++; }
                    if (grid[i][j + 1].hasBomb()) { count++; }
                } catch (Exception e) {
                    //Do nothing!
                }
                grid[i][j].setExplosiveNeighbors(count);
            }
        }
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
