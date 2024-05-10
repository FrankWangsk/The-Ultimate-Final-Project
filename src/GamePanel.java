import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private final SkyForceGame game;

    private EnemyJet enemyJet;
    private final List<GameObject> objects = new ArrayList<>();

    public GamePanel(SkyForceGame game) {
        this.game = game;
        Jet jet = new Jet(this, true, 50, 50);
        addObject(jet);
        enemyJet = new EnemyJet(0, 50, 3);
    }

    public void tick() {
        for (GameObject object : List.copyOf(objects)) {
            object.tick();
        }
        enemyJet.update();
    }

    public void addObject(GameObject object) {
        if (object instanceof KeyListener listener) {
            game.addKeyListener(listener);
        }
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        if (object instanceof KeyListener listener)
            game.removeKeyListener(listener);
        objects.remove(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject object : objects) {
            object.paint(g);
        }
        enemyJet.draw(g);
    }
}