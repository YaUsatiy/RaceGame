import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static JLabel label;

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SIZE_WIDTH = 900;
    public static final int SIZE_HEIGHT = 296+ 20;
    public static final int LOCATION_X = (SCREEN_SIZE.width - SIZE_WIDTH) / 2;
    public static final int LOCATION_Y = (SCREEN_SIZE.height - SIZE_HEIGHT) / 2;

    public MainWindow(){
        setTitle("RaceGame 2019");
        setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
        add(new RoadPanel());

        initLabel();

        //центрируем
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    private void initLabel(){
        label = new JLabel("Скорость : 0 км/ч   Пройдено 0%   Столкновений : 0");
        add(label, BorderLayout.SOUTH);
    }

}
