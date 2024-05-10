import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jet extends GameObject implements KeyListener {

    private final boolean controlling;
    private int shootTimer;
    private int health;
    private boolean isPressedRight;
    private boolean isPressedLeft;
    private boolean isPressedUp;
    private boolean isPressedDown;

    public Jet(GamePanel panel, boolean controlling, int x, int y) {
        super(panel, x, y, 100, 100);
        this.controlling = controlling;
        this.health = 100;
        this.isPressedLeft = false;
        this.isPressedRight = false;
        this.isPressedUp = false;
        this.isPressedDown = false;
        if (controlling) {
            panel.addKeyListener(this);
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public void tick() {
        if (isPressedLeft){
            x -= 5;
        }
        if (isPressedRight){
            x += 5;
        }
        if (isPressedUp){
            y -= 5;
        }
        if (isPressedDown){
            y += 5;
        }
        shootTimer++;
        if (shootTimer >= 60) {
            shootTimer = 0;
            Bullet bullet = new Bullet(panel, x + width / 2, y, 0, -5);
            panel.addObject(bullet);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Resources.MY_JET, x, y, 100, 100, null);
        g.setColor(Color.GREEN);
        g.fillRect(x, y + 110, health, 10);
        g.setColor(Color.RED);
        g.fillRect(x + health, y + 110, 100 - health, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        if (key == 'a') {
            isPressedLeft = true;
        } else if (key == 'd') {
            isPressedRight = true;
        } else if (key == 'w') {
            isPressedUp = true;
        } else if (key == 's') {
            isPressedDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyChar();
        if (key == 'a') {
            isPressedLeft = false;
        } else if (key == 'd') {
            isPressedRight = false;
        } else if (key == 'w') {
            isPressedUp = false;
        } else if (key == 's') {
            isPressedDown = false;
        }
    }
}

