import java.awt.Graphics;

public class EnemyJet {
    private int x, y;
    private int speed;
    private int direction;
    public EnemyJet(int startX, int startY, int initialSpeed) {
        x = startX;
        y = startY;
        speed = initialSpeed;
        direction = 1;
    }
    public void update() {
        x += speed * direction;
        if (x <= 0 || x >= 1024 - 50) {
            direction *= -1;
        }
    }
    public void draw(Graphics g) {
        g.fillRect(x, y, 50, 20);
    }
}

