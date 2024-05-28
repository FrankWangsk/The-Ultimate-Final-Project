import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SkyForceGame extends JFrame {

    private static SkyForceGame GAME;

    private final CardLayout rootLayout;
    private final JPanel rootPanel;
    private final GamePanel gamePanel;
    private final StorePanel storePanel;
    private JPanel currentPanel;

    private boolean gameOver = false;
    private ScoreManager ScoreManager = new ScoreManager();

    public SkyForceGame() {
        GAME = this;
        rootLayout = new CardLayout();
        rootPanel = new JPanel(rootLayout);

        gamePanel = new GamePanel();
        storePanel = new StorePanel();
        rootPanel.add(gamePanel, "Game");
        rootPanel.add(storePanel, "Store");
        gamePanel.init();
        currentPanel = gamePanel;
        setContentPane(rootPanel);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'b' -> {
                        if (currentPanel == gamePanel)
                            SkyForceGame.getInstance().openStore();
                        else
                            SkyForceGame.getInstance().openGame();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setSize(1024, 768);
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
        return GAME.gamePanel;
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
        rootPanel.addKeyListener(l);
    }

    public void openGame() {
        currentPanel = gamePanel;
        rootLayout.show(rootPanel, "Game");
    }

    public void openStore() {
        storePanel.refresh();
        currentPanel = storePanel;
        rootLayout.show(rootPanel, "Store");
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

                if (currentPanel == gamePanel)
                    gamePanel.tick();

                updateTime = System.nanoTime() - now;
                wait = (OPTIMAL_TIME - updateTime) / 1000000;

                try {
                    if (wait > 0) {
                        Thread.sleep(wait);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getContentPane().repaint();
            }
        });
        gameThread.start();
    }

}