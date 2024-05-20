import java.awt.*;

public abstract class DamageableObject extends GameObject {

    private final double initialHealth;
    protected double health;

    public DamageableObject(double health, Vector position, Vector size) {
        super(position, size);
        this.initialHealth = health;
        this.health = health;
    }

    public void damage(double amount) {
        health -= amount;
        if (health <= 0) {
            onDeath();
        }
    }

    protected void onDeath() {
        SkyForceGame.getPanel().removeObject(this);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void drawHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.getX(), position.getY() + size.getY() + 5, size.getX(), 10);
        g.setColor(Color.GREEN);
        g.fillRect(position.getX(), position.getY() + size.getY() + 5, (int) ((health / initialHealth) * size.x()), 10);
    }
}