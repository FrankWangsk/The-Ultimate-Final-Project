import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJet extends GameObject implements KeyListener {

    private int shootTimer;
    private int health;

    public MyJet(Vector pos) {
        super(pos, new Vector(100, 100));
        this.health = 100;
        SkyForceGame.getPanel().addKeyListener(this);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();
        shootTimer++;
        if (shootTimer >= 60) {
            shootTimer = 0;
            Bullet bullet = new Bullet(true, new Vector(position.x() + size.x() / 2, position.y()), new Vector(velocity.x(), -5));
            SkyForceGame.getPanel().addObject(bullet);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Resources.MY_JET, position.getX(), position.getY(), 100, 100, null);
        g.setColor(Color.GREEN);
        g.fillRect(position.getX(), position.getY() + 110, health, 10);
        g.setColor(Color.RED);
        g.fillRect(position.getX() + health, position.getY() + 110, 100 - health, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        if (key == 'a') {
            velocity.setX(-5);
        } else if (key == 'd') {
            velocity.setX(5);
        } else if (key == 'w') {
            velocity.setY(-5);
        } else if (key == 's') {
            velocity.setY(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyChar();
        if (key == 'a') {
            velocity.setX(0);
        } else if (key == 'd') {
            velocity.setX(0);
        } else if (key == 'w') {
            velocity.setY(0);
        } else if (key == 's') {
            velocity.setY(0);
        }
    }
}

