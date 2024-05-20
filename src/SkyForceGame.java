import javax.swing.*;

public class SkyForceGame extends JFrame {

    private static SkyForceGame GAME;

    private final GamePanel panel;

    private boolean gameOver = false;
    private ScoreManager ScoreManager = new ScoreManager();

    public SkyForceGame() {
        GAME = this;
        panel = new GamePanel();
        panel.init();
        setSize(1024, 768);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        runGameLoop();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SkyForceGame::new);
    }

    public static SkyForceGame getInstance() {
        return GAME;
    }

    public static GamePanel getPanel() {
        return GAME.panel;
    }

    public void endGame() {
        gameOver = true;
        System.out.println("Game Over");
    }

    public ScoreManager getScoreManager() {
        return ScoreManager;
    }


    private void runGameLoop() {
        Thread gameThread = new Thread(() -> {
            long now;
            long updateTime;
            long wait;

            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            while (!gameOver) {
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
        });
        gameThread.start();
    }

}