import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJet extends DamageableObject implements KeyListener {

    private int shootTimer;

    public MyJet(Vector pos) {
        super(10000, pos, new Vector(100, 100));
        SkyForceGame.getPanel().addKeyListener(this);
    }

    @Override
    protected void onDeath() {
        SkyForceGame.getInstance().endGame();
    }

    @Override
    public void tick() {
        super.tick();
        shootTimer++;
        if (shootTimer >= ((SkyForceGame.getPanel().isInBossFight() ? 20 : 60) - SkyForceGame.getInstance().getScoreManager().getLevel() * 5)) {
            shootTimer = 0;
            spawnBullet(0, -5);
            if (SkyForceGame.getPanel().isInBossFight()) {
                spawnBullet(-5, -5);
                spawnBullet(5, -5);
            }
        }
    }


    private void spawnBullet(double x, double y) {
        Bullet bullet = new Bullet(this, new Vector(position.x() + size.x() / 2, position.y()), new Vector(x, y));
        SkyForceGame.getPanel().addObject(bullet);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Resources.MY_JET, position.getX(), position.getY(), 100, 100, null);
        drawHealthBar(g);
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
