package brickGame;

//import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gamePlay extends JPanel implements ActionListener, KeyListener {

    private boolean play = false;
    private int totalBrick = 21;
    private Timer timer;
    private int delay = 15;
    private int ballposX=120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int playerX = 300;

    public gamePlay()
    {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay,this);
        timer.start();
    }


    public void paint(Graphics g)
    {
        //black canvas
        g.setColor(Color.BLACK );
        g.fillRect(1,1,692,592);

        //border
        g.setColor(Color.yellow);
        g.fillRect(0,0,692,3);
        g.fillRect(0,3,3,592);
        g.fillRect(683,3,3,592);

        //padder
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //ball
        g.setColor(Color.red);
        g.fillOval(ballposX,ballposY,20,20);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(play)
        {

            if(ballposX<=0){
                ballXdir=-ballXdir;
            }
            if(ballposX>=670){
                ballXdir=-ballXdir;
            }
            if(ballposY<=0){
                ballYdir=-ballYdir;
            }



            ballposX+=ballXdir;
            ballposY+=ballYdir;
        }

        Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
        Rectangle paddleRect = new Rectangle(playerX,550,100,8);

        if(ballRect.intersects(paddleRect))
        {
            ballYdir=-ballYdir;
        }

        repaint();

    }

    private void moveLeft()
    {
        play = true;
        playerX-=20;
    }

    private void moveRight()
    {
        play = true;
        playerX+=20;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX<=0)
            {
                playerX =0;
            }
            else {
                moveLeft();
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX>=600)
            {
                playerX=600;
            }
            else
            moveRight();
        }

        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
