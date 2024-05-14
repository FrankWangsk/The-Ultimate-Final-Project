import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyJet extends GameObject{

    private int shootTimer;

    public EnemyJet(Vector position, Vector velocity) {
        super(position,  new Vector(50, 50));
        this.velocity = velocity;
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
        if (shootTimer >= 60) {
            shootTimer = 0;
            Bullet bullet = new Bullet(false, new Vector(position.x() + size.x() / 2, position.y()), new Vector(velocity.x(), 5));
            SkyForceGame.getPanel().addObject(bullet);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Resources.ENEMY_JET, position.getX(), position.getY(), size.getX(), size.getY(), null);
    }


    public static EnemyJet createRandom() {
        Random random = new Random();
        int width = SkyForceGame.getInstance().getWidth();
        int height = SkyForceGame.getInstance().getHeight();

        return new EnemyJet(new Vector(random.nextDouble(width), random.nextDouble(height)),
                new Vector(random.nextDouble(-5d, 5d), random.nextDouble(-5d, 5d)));
    }

}

