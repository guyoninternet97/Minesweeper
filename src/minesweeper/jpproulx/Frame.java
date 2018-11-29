package minesweeper.jpproulx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JPanel implements MouseListener, ActionListener, KeyListener {

    private JFrame window;
    private Timer timer = new Timer(100, this);
    private Grid testGrid;
    boolean clickedForTheFirstTime = false;
    private int size;

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
        window.setTitle("Minesweeper!");
        window.getContentPane().setPreferredSize(new Dimension(700, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        size = 16;
        testGrid = new Grid(this.size, 25);

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
        if (SwingUtilities.isLeftMouseButton(e)) {
            testGrid.handleClick(e.getX(), e.getY());
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            testGrid.handleFlag(e.getX(), e.getY());
        }

        testGrid.checkCompletion();

        if (!clickedForTheFirstTime) {
            testGrid.populateBombs();
            clickedForTheFirstTime = true;
        }


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
            this.testGrid = new Grid(this.size, 25);
            this.clickedForTheFirstTime = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
