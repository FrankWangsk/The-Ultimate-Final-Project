import javax.swing.*;
import java.awt.*;

public abstract class GameObject {
    public Vector position;
    public Vector size;
    public Vector velocity = new Vector();

    public  GameObject(Vector position, Vector size) {
        this.position = position.copy();
        this.size = size.copy();
    }

    public abstract void paint(Graphics graphics);

    public void repaint() {
        SwingUtilities.invokeLater(() -> SkyForceGame.getPanel().repaint());
    }

    public void tick() {
        if (!velocity.isZero()) {
            position.add(velocity);
            repaint();
        }
    }

    public void remove() {
        SkyForceGame.getPanel().removeObject(this);
    }

}
