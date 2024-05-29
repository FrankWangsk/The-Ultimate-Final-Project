import java.awt.*;
import java.util.Random;

public class ShieldBattery extends GameObject {

    private static final Random random = new Random();
    private final boolean big;

    public ShieldBattery(Vector position, boolean big) {
        super(position, new Vector(50, 50));
        this.big = big;
    }

    @Override
    public void tick() {
        for (GameObject object : SkyForceGame.getPanel().getColliding(this)) {
            if (object instanceof MyJet jet) {
                if (big)
                    jet.setShieldPoints(jet.getShield().getShieldPoints());
                else
                    jet.setShieldPoints(jet.getShieldPoints() + 25);
                remove();
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(big ? Resources.BIG_SHIELD : Resources.SMALL_SHIELD, position.getX(), position.getY(), null);
    }

    public static void spawnRandom() {
        ShieldBattery battery = new ShieldBattery(EnemyJet.randomPos(new Vector(50, 50)), random.nextBoolean());
        SkyForceGame.getPanel().addObject(battery);
    }

}
