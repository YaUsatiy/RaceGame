import javax.swing.*;
import java.awt.*;

public class Rival {

    private int x;
    private int y;
    private int v;
    private Image img1 = new ImageIcon(getClass().getResource("img/rival1.png")).getImage();
    private Image img2 = new ImageIcon(getClass().getResource("img/rival2.png")).getImage();
    private Image img3 = new ImageIcon(getClass().getResource("img/rival3.png")).getImage();
    private Image img4 = new ImageIcon(getClass().getResource("img/rival4.png")).getImage();


    public static final int IMAGE_RIVAL_WIDTH = 115;
    public static final int IMAGE_RIVAL_HEIGHT = 56;

    private RoadPanel road;
    private int imageNumber;

    public Rival(int x, int y, int v, RoadPanel road, int imageNumber){
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
        this.imageNumber = imageNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImg() {
        switch (imageNumber){
            case 1 : return img1;
            case 2 : return img2;
            case 3 : return img3;
            case 4 : return img4;
        }
        return img1; //сюда никогда не войдёт
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, IMAGE_RIVAL_WIDTH, IMAGE_RIVAL_HEIGHT);
    }

    public void move(){
        x = x - road.getPlayer().getV() - v;
    }

}
