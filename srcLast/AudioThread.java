import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {
    @Override
    public void run() {

        try {
            Player player = new Player(getClass().getResourceAsStream("music/track1.mp3"));
            player.play();
        } catch (JavaLayerException e) {
            System.out.println("Problems with library");
        }
    }
}