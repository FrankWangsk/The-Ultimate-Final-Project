import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jet extends GameObject implements KeyListener {

    private final boolean controlling;
    private int shootTimer;

    public Jet(GamePanel panel, int x, int y) {
        this(panel, false, x, y);
    }

    public Jet(GamePanel panel, boolean controlling, int x, int y) {
        super(panel, x, y, 100, 100);
        this.controlling = controlling;
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
