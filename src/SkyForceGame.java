import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class SkyForceGame extends JFrame {

    private final GamePanel panel = new GamePanel(this);

    public SkyForceGame() {
        setSize(1024, 768);
        setContentPane(panel);
        setVisible(true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                panel.tick();
            }
        }, 0, 30);
    }


    public static void main(String[] args) {
        SkyForceGame game = new SkyForceGame();
    }
}
