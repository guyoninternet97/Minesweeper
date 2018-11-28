package minesweeper.jpproulx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JPanel implements MouseListener, ActionListener, KeyListener {

    private JFrame window;
    private Timer timer = new Timer(100, this);
    private Grid testGrid;
    boolean clickedForTheFirstTime = false;

    public static void main(String[] args) {
        new Frame();
    }

    //Frame constructor
    public Frame() {
        setLayout(null);
        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);


        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("A* Pathfinding Visualization");
        window.getContentPane().setPreferredSize(new Dimension(700, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        testGrid = new Grid(20, 50);

        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        timer.setDelay(50);
        timer.start();

        if (testGrid != null) {
            testGrid.draw(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tile tile = testGrid.handleClick(e.getX(), e.getY());
        if (!clickedForTheFirstTime) {
            System.out.println("YOO");
            testGrid.populateBombs();
            testGrid.assignNeighborCounts();
        }
        clickedForTheFirstTime = true;
        System.out.println(tile.getTileID());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        //This is disgusting
        key = new StringBuilder().append(key).toString().toUpperCase().charAt(0);
        if (key == KeyEvent.VK_Q) {
            System.exit(0);
        } else if (key == KeyEvent.VK_R) {
            this.testGrid = new Grid(20, 50);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
