import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static JLabel label;

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int sizeWidth = 900;
    public static final int sizeHeight = 296+ 20;
    public static final int locationX = (screenSize.width - sizeWidth) / 2;
    public static final int locationY = (screenSize.height - sizeHeight) / 2;

    public MainWindow(){
        setTitle("RaceGame 2019");
        setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
        add(new RoadPanel());

        initLabel();

        //центрируем
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    private void initLabel(){
        label = new JLabel("Скорость : 0 км/ч.  Пройдено 0%");
        add(label, BorderLayout.SOUTH);
        //Font font = new Font("Arial", Font.ITALIC, 20);
        //g.setFont(font);
    }

}
