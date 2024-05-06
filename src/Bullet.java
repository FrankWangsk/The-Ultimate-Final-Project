import java.awt.*;

public class Bullet extends GameObject {

    private final int xSpeed;
    private final int ySpeed;

    public Bullet(GamePanel panel, int x, int y, int xSpeed, int ySpeed) {
        super(panel, x, y, 10, 10);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void tick() {
        x += xSpeed;
        y += ySpeed;
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
    }
}
