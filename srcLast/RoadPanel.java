import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RoadPanel extends JPanel implements ActionListener, Runnable {

    private Image img = new ImageIcon(getClass().getResource("img/road.jpg")).getImage();

    public Timer timer = new Timer(20, this);
    private Instant starts;
    private Instant ends;
    private Player player = new Player();
    private int imageNumber;
    private int collisions = 0;

    Thread rivalsFactory = new Thread(this);
    ArrayList <Rival> rivals = new ArrayList<Rival>();

    Thread audioThread = new Thread(new AudioThread());

    public RoadPanel(){
        starts = Instant.now();
        timer.start();
        rivalsFactory.start();
        audioThread.start();
        addKeyListener(new FieldListener());
        setFocusable(true);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void paint(Graphics g) {
        g = (Graphics2D)g;
        g.drawImage(img, player.layer1, 0, null);
        g.drawImage(img, player.layer2, 0, null);
        g.drawImage(img, player.layer3, 0, null);
        g.drawImage(player.getImg(), player.getX(), player.getY(), null);

        Iterator<Rival> iterator = rivals.iterator();
        while (iterator.hasNext()) {
            Rival rival = iterator.next();
            if (rival.getX() >= 1200 || rival.getX() <= -1200) {
                iterator.remove();
            } else {
                rival.move();
                g.drawImage(rival.getImg(), rival.getX(), rival.getY(), null);
            }
        }
        ends = Instant.now();
        MainWindow.label.setText("        Скорость : " + player.currentSpeed + " км/ч.              " +
                "Пройдено " + Player.formattedTraveledDistance + "%              Столкновений: " + collisions +
                "            Затраченоое время : " + Duration.between(starts, ends).getSeconds() + "сек                Нажмите SPACE для паузы");
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
            ends = Instant.now();
            JOptionPane.showMessageDialog(null, "Вы выиграли!!!  Столкновений произведено : " +
                    collisions + " Затраченоое время : " + Duration.between(starts, ends).getSeconds() + "  сек", "Поздравляю!", JOptionPane.INFORMATION_MESSAGE);
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
                collisions++;
            }
        }
    }

    @Override
    public void run() {
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(3000) + 1);
                imageNumber = random.nextInt(4) + 1;
                rivals.add(new Rival(930, random.nextInt(150), random.nextInt(8)+2, this, imageNumber));
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
