import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RoadPanel extends JPanel implements ActionListener, Runnable {

    Image img = new ImageIcon(getClass().getResource("img/road.jpg")).getImage();

    public Timer timer = new Timer(20, this);

    Player player = new Player();

    Thread rivalsFactory = new Thread(this);
    ArrayList <Rival> rivals = new ArrayList<Rival>();

    Thread audioThread = new Thread(new AudioThread());

    public RoadPanel(){
        timer.start();
        rivalsFactory.start();
        audioThread.start();
        addKeyListener(new FieldListener());
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) { //физику убрать из этого метода
        g = (Graphics2D)g;
        g.drawImage(img, player.layer1, 0, null);
        g.drawImage(img, player.layer2, 0, null);
        g.drawImage(img, player.layer3, 0, null);
        g.drawImage(player.img, player.x, player.y, null);

        Iterator<Rival> iterator = rivals.iterator();
        while (iterator.hasNext()) {
            Rival rival = iterator.next();
            if (rival.x >= 1200 || rival.x <= -1200) {
                iterator.remove();
            } else {
                rival.move();
                g.drawImage(rival.img, rival.x, rival.y, null);
            }
        }

        MainWindow.label.setText("        Скорость : " + player.currentSpeed + " км/ч.                         " +
                "Пройдено " + Player.formattedTraveledDistance + "%                         " +
                "Нажмите SPACE, чтобы поставить игру на паузу");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        testCollisionsWithRivals();
        testWin();
    }

    private void testWin() {
        if (player.s > player.DISTANCE){
            JOptionPane.showMessageDialog(null, "Вы выиграли!!! Поздравляю!");
            System.exit(0);
        }
    }

    private void testCollisionsWithRivals() {
        Iterator<Rival> iterator = rivals.iterator();
        while (iterator.hasNext()){
            Rival rival = iterator.next();
            if (player.getRect().intersects(rival.getRect())){
                /*JOptionPane.showMessageDialog(null, "Вы проиграли!!!");
                System.exit(0);*/
                iterator.remove();
            }
        }
    }

    @Override
    public void run() {
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(3000) + 1);
                rivals.add(new Rival(930, random.nextInt(150), random.nextInt(8)+2, this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class FieldListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e, timer);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
