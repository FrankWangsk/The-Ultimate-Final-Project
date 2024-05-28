import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel {

    private final List<GameObject> objects = new CopyOnWriteArrayList<>();
    private int enemyJetSpawnTimer = 23520;

    private List<EnemyJet> bosses = new ArrayList<>();

    public GamePanel() {

    }

    public void init() {
        MyJet jet = new MyJet(new Vector(50, 50));
        addObject(jet);
    }

    public void tick() {
        for (GameObject object : getObjects()) {
            object.tick();
        }

        if (!isInBossFight() && enemyJetSpawnTimer > 400 - (3 * Math.log10(SkyForceGame.getInstance().getScoreManager().getLevel() * 100))) {
            enemyJetSpawnTimer = 0;
            EnemyJet jet = EnemyJet.createRandom();
            addObject(jet);
        }
        enemyJetSpawnTimer++;
    }

    public boolean isInBossFight() {
        return !bosses.isEmpty();
    }

    public void enterBossFight(int amount) {
        getObjects().forEach(object -> {
            if (object instanceof EnemyJet)
                removeObject(object);
        });

        for (int i = 0; i < amount; i++) {
            EnemyJet jet = EnemyJet.createBoss();
            addObject(jet);
            bosses.add(jet);
        }

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
        if (object instanceof EnemyJet)
            bosses.remove(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject object : getObjects()) {
            object.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Score: " + SkyForceGame.getInstance().getScoreManager().getScore(), 10, 20);
        g.drawString("Level: " + SkyForceGame.getInstance().getScoreManager().getLevel(), 10, 40);
        g.drawString("Objects: " + objects.size(), 10, getHeight() - 10);
        g.drawString("Money: " + SkyForceGame.getInstance().getScoreManager().getMoney(), getWidth() - 100, 20);

    }

    public List<GameObject> getObjects() {
        return objects;
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