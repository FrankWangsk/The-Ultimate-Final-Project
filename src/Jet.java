import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jet extends GameObject implements KeyListener {

    private final boolean controlling;
    private int shootTimer;
    private int health;



    public Jet(GamePanel panel, boolean controlling, int x, int y) {
        super(panel, x, y, 100, 100);
        this.controlling = controlling;
        this.health = 100;
    }

    public void takeDamge(int damage){
        health -= damage;
        if(health < 0){
            health = 0;
        }
    }

    @Override
    public void tick() {
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
        g.fillRect(x, y + 110, 100, 10);
        g.setColor(Color.RED);
        System.out.println(health);
        g.fillRect(x, y + 110, 100 - health, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'a')
            x -= 15;
        if (e.getKeyChar() == 'w')
            y -= 15;
        if (e.getKeyChar() == 's')
            y += 15;
        if (e.getKeyChar() == 'd')
            x += 15;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
