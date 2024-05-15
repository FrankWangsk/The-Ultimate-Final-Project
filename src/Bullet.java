import java.awt.*;

public class Bullet extends GameObject {

    private final boolean mine;

    public Bullet(boolean mine, Vector position, Vector velocity) {
        super(position, new Vector(10, 10));
        this.mine = mine;
        this.velocity = velocity;
    }

    @Override
    public void tick() {
        super.tick();
        if (position.x() <= 0 || position.y() <= 0 || position.x() >= SkyForceGame.getInstance().getWidth() || position.y() >= SkyForceGame.getInstance().getHeight())
            remove();

        for (GameObject object : SkyForceGame.getPanel().getColliding(this)) {
            if (mine && object instanceof EnemyJet jet) {
                jet.remove();
                remove();
                SkyForceGame.getInstance().getScoreManager().addScore(10);
                return;
            }
            if (!mine && object instanceof MyJet jet) {
                jet.takeDamage(10);
                remove();
                return;
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        if (mine) {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.drawRect(position.getX(), position.getY(), size.getX(), size.getY());
    }
}
