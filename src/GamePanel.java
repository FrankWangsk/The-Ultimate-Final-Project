import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private final List<GameObject> objects = new ArrayList<>();

    private int enemyJetSpawnTimer = 0;
    JPanel scoreBoard;

    public GamePanel() {
    }

    public void init() {
        MyJet jet = new MyJet(new Vector(50, 50));
        addObject(jet);
        EnemyJet enemyJet = new EnemyJet(new Vector(0, 50), new Vector(5, 0));
        addObject(enemyJet);
    }

    public void tick() {
        for (GameObject object : getObjects()) {
            object.tick();
        }
        if (enemyJetSpawnTimer > 300) {
            enemyJetSpawnTimer = 0;
            EnemyJet jet = EnemyJet.createRandom();
            addObject(jet);
        }
        enemyJetSpawnTimer++;
    }

    public void addObject(GameObject object) {
        if (object instanceof KeyListener listener) {
            SkyForceGame.getInstance().addKeyListener(listener);
        }
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        if (object instanceof KeyListener listener)
            SkyForceGame.getInstance().removeKeyListener(listener);
        objects.remove(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject object : getObjects()) {
            object.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Score: " + SkyForceGame.getInstance().getScoreManager().getScore(), 10, 20);
    }

    public List<GameObject> getObjects() {
        return new ArrayList<>(objects);
    }

    public List<GameObject> getColliding(GameObject object) {
        double x1 = object.position.x();
        double w1 = object.size.x();
        double y1 = object.position.y();
        double h1 = object.size.y();
        return getObjects().stream().filter(o -> {
            double x2 = o.position.x();
            double w2 = o.size.x();
            double y2 = o.position.y();
            double h2 = o.size.y();
            if (x1 + w1 < x2 || x2 + w2 < x1) {
                return false;
            }
            return !(y1 + h1 < y2) && !(y2 + h2 < y1);
        }).toList();

    }

}