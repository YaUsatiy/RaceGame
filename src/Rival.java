import javax.swing.*;
import java.awt.*;

public class Rival { //потом можно отнаследоваться
    //добавить пройденный километраж
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    int x;
    int y;
    int v;
    Image img = new ImageIcon(getClass().getResource("img/rival2.png")).getImage();

    RoadPanel road;

    public Rival(int x, int y, int v, RoadPanel road){
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    private Image getImage(String name){
        String fileName = "/res/img/" + name + ".jpg";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, 115, 56);
    }

    public void move(){
        x = x - road.player.v - v;
    }

}
