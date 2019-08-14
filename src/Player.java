import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player { //сделать 1 класс персонаж и оот него унаследовать игрока и соперников

    Image imgCenter = new ImageIcon(getClass().getResource("img/car2.png")).getImage();
    Image imgLeft = new ImageIcon(getClass().getResource("img/carLeft.png")).getImage();
    Image imgRight = new ImageIcon(getClass().getResource("img/carRight.png")).getImage();

    Image img = imgCenter;

    boolean paused = false;
    public static float currentSpeed;
    private float traveledDistance;
    public static String formattedTraveledDistance;
    public static final int DISTANCE = 30000;

    int v = 0;
    int dv = 0;
    public  static  int s = 0;

    int x = 10;
    int y = 20;
    int dy = 0;

    int layer1 = 0;
    int layer2 = 450;
    int layer3 = 900;

    public static final int MAX_V = 50;
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM = 185;

    public void move() {
        s += v;
        v += dv;

        if (v <= 0) v = 0;
        if (v >= MAX_V) {
            v = MAX_V;
        }
        currentSpeed = (100 / Player.MAX_V) * v;
        traveledDistance = (float)Player.s / DISTANCE * 100;
        if (traveledDistance >= 100){
            traveledDistance = 100;
        }
        formattedTraveledDistance = String.format("%.2f", traveledDistance);

        y -= dy;
        if (y <= MAX_TOP) {
            y = MAX_TOP;
        }
        if (y >= MAX_BOTTOM) {
            y = MAX_BOTTOM;
        }

        if (layer2 - v <= 0) {
            layer1 = 0;
            layer2 = 450;
            layer3 = 900;
        } else {
            layer1 -= v;
            layer2 -= v;
            layer3 -= v;

        }
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, 135, 67);
    }

    public void keyPressed(KeyEvent e, Timer timer){
        int key = e.getKeyCode();
        switch (key){
            case (KeyEvent.VK_RIGHT) : dv = 1; break;
            case (KeyEvent.VK_LEFT)  : dv = -1; break;
            case (KeyEvent.VK_UP)    : dy = 5; img = imgLeft; break;
            case (KeyEvent.VK_DOWN)  : dy = -5; img = imgRight; break;
            case (KeyEvent.VK_SPACE) :
                if (!paused){
                    timer.stop();
                    paused = true;
                }
                else {
                    timer.start();
                    paused = false;
                }
                break;
        }
    }

    public  void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            img = imgCenter;
        }
    }
}
