import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJet extends DamageableObject implements KeyListener {


    private int shootTimer;
    private Shields shield;
    private double evoPoints;
    private double shieldPoints;

    public MyJet(Vector pos) {
        super(100, pos, new Vector(100, 100));
        this.shield = Shields.COMMON;
        this.evoPoints = 0;
        this.shieldPoints = shield.getShieldPoints();
        SkyForceGame.getPanel().addKeyListener(this);
    }

    @Override
    public void damage(double amount) {
        double shieldDamage = Math.min(shieldPoints, amount);
        shieldPoints -= shieldDamage;
        super.damage(amount - shieldDamage);
    }

    @Override
    protected void onDeath() {
        SkyForceGame.getInstance().endGame();
    }

    public void addEvoPoints(double points) {
        this.evoPoints += points;
        if (evoPoints >= shield.getEvoPoints()) {
            shield = Shields.values()[shield.ordinal() + 1];
            shieldPoints += 25;
            evoPoints = 0;
        }
    }

    public double getShieldPoints() {
        return shieldPoints;
    }

    public void setShieldPoints(double shieldPoints) {
        if (shieldPoints >= shield.getShieldPoints())
            shieldPoints = shield.getShieldPoints();
        if (shieldPoints < 0)
            shieldPoints = 0;
        this.shieldPoints = shieldPoints;

    }

    public Shields getShield() {
        return shield;
    }

    @Override
    public void tick() {
        super.tick();
        shootTimer++;
        if (shootTimer >= 60 - Upgrades.SHOOTING_SPEED.getLevel() * 5) {
            shootTimer = 0;
            double speed = Upgrades.BULLET_SPEED.getLevel();
            spawnBullet(0, -5 - speed);
            if (Upgrades.TRISHOOT.getLevel() > 0) {
                spawnBullet(-5, -5 - speed);
                spawnBullet(5, -5 - speed);
            }
        }
    }


    private void spawnBullet(double x, double y) {
        Bullet bullet = new Bullet(this, new Vector(position.x() + size.x() / 2, position.y()), new Vector(x, y));
        SkyForceGame.getPanel().addObject(bullet);
    }

    @Override
    public void paint(Graphics g) {
        int radius = (int) (125 * (shieldPoints / shield.getShieldPoints())) + 100;
        g.setColor(shield.getColor());
        g.fillOval(position.getX() - radius / 2 + size.getX() / 2, position.getY() - radius / 2 + size.getY() / 2, radius, radius);
        g.drawImage(Resources.MY_JET, position.getX(), position.getY(), 100, 100, null);
        drawHealthBar(g);
        g.setColor(Color.BLACK);
        if (shield.ordinal() - 1 < Shields.values().length)
            g.drawString("EVO: " + evoPoints + " / " + shield.getEvoPoints(), position.getX(), position.getY() - 5);
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
