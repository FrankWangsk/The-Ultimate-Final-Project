import javax.swing.*;

public class SkyForceGame extends JFrame {

    private final GamePanel panel = new GamePanel(this);

    public SkyForceGame() {
        setSize(1024, 768);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Properly exit the application when window is closed
        setVisible(true);

        runGameLoop();
    }

    private void runGameLoop() {
        Thread gameThread = new Thread(new Runnable() {
            public void run() {
                long now;
                long updateTime;
                long wait;

                final int TARGET_FPS = 60;
                final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

                while (true) {
                    now = System.nanoTime();

                    panel.tick();

                    updateTime = System.nanoTime() - now;
                    wait = (OPTIMAL_TIME - updateTime) / 1000000;

                    try {
                        if (wait > 0) {
                            Thread.sleep(wait);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                }
            }
        });
        gameThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SkyForceGame();
            }
        });
    }
}