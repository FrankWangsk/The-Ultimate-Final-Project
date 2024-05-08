import java.awt.*;

public abstract class GameObject {

    public GamePanel panel;
    public int x;
    public int y;
    public int width;
    public int height;

    public GameObject(GamePanel panel, int x, int y, int width, int height) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void paint(Graphics graphics);

    public void repaint() {
        panel.repaint();
    }

    public void tick() {

    }
}
