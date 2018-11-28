package minesweeper.jpproulx;

import java.awt.*;

public class Tile {

    private boolean hasBomb;
    private int x, y;
    private int tileID;
    private static int staticTileID = 0;
    private int explosiveNeighbors;
    static final int TILE_HIEGHT = 24;
    static final int TILE_WIDTH = 24;

    public boolean isClicked;



    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        staticTileID++;
        this.tileID = staticTileID;
        isClicked = false;
    }

    public Tile(int x, int y, boolean bomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = bomb;
        staticTileID++;
        this.tileID = staticTileID;
        isClicked = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(x, y, TILE_WIDTH, TILE_HIEGHT);
        g.setColor(Color.gray);
        if (isClicked) {
            g.setColor(Color.WHITE);
        }
        if (hasBomb) {
            g.setColor(Color.BLACK);
        }
        g.fillRect(x + 2, y + 2, TILE_WIDTH - 2, TILE_HIEGHT - 2);
        g.setColor(Color.red);
        if (isClicked) {
            g.drawString(Integer.toString(explosiveNeighbors), x + 11, y + 15);
        }

    }

    /***
     * Give the tile a bomb
     *
     * @return if the giving of a bomb was successful, that is, the tile did not already have a bomb
     */
    public boolean giveBomb() {
        if (hasBomb) {
            return false;
        } else {
            hasBomb = true;
        }
        return true;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    /***
     * Get the number of tiles surrounding {@code this} which contains a bomb
     *
     * @return the number of bombs
     */
    public int getExplosiveNeighbors() {
        return explosiveNeighbors;
    }

    /***
     * Sets the number of explosive neighbors for {@code this} to keep track of
     * @param neighbors The number of neighbors
     */
    public void setExplosiveNeighbors(int neighbors) {
        this.explosiveNeighbors = neighbors;
    }

    public int getTileID() {
        return this.tileID;
    }
}
