import java.awt.*;
import java.util.Random;

public class EnemyJet extends DamageableObject {

    private static final Random random = new Random();
    private int shootTimer;

    public EnemyJet(double health, Vector position, Vector size, Vector velocity) {
        super(health, position, size);
        this.health = health;
        this.velocity = velocity;
    }

    public static EnemyJet createRandom() {
        Vector size = new Vector(50, 50);
        return new EnemyJet(10, randomPos(size), size,
                new Vector(random.nextDouble(-5d, 5d), random.nextDouble(-5d, 5d)));
    }

    public static EnemyJet createBoss() {
        Vector size = new Vector(150, 150);
        return new EnemyJet(100, randomPos(size), size,
                new Vector(15, 15));
    }

    public static Vector randomPos(Vector size) {
        int width = SkyForceGame.getInstance().getWidth();
        int height = SkyForceGame.getInstance().getHeight();


        return new Vector(random.nextDouble(width - size.x()), random.nextDouble(height - size.y()));
    }

    @Override
    protected void onDeath() {
        super.onDeath();
        SkyForceGame.getInstance().getScoreManager().addScore(10);
    }

    @Override
    public void tick() {
        super.tick();
        if (position.x() <= 0 || position.x() + size.x() >= SkyForceGame.getInstance().getWidth()) {
            velocity.multiply(-1, 1);
        }
        if (position.y() <= 0 || position.y() + size.y() >= SkyForceGame.getInstance().getHeight()) {
            velocity.multiply(1, -1);
        }
        shootTimer++;
        if (shootTimer >= 70 - SkyForceGame.getInstance().getScoreManager().getLevel() * 20) {
            shootTimer = 0;
            Bullet bullet = new Bullet(new Vector(position.x() + size.x() / 2, position.y()), new Vector(0, 5));
            SkyForceGame.getPanel().addObject(bullet);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Resources.ENEMY_JET, position.getX(), position.getY(), size.getX(), size.getY(), null);
        drawHealthBar(g);
    }

}

