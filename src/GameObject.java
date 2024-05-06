import java.awt.*;

public abstract class GameObject {

    protected GamePanel panel;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

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
