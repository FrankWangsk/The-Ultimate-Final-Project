import java.awt.*;

public class Bullet extends GameObject {

    private final MyJet parent;

    public Bullet(Vector position, Vector velocity) {
        this(null, position, velocity);
    }

    public Bullet(MyJet parent, Vector position, Vector velocity) {
        super(position, new Vector(10, 10));
        this.velocity = velocity;
        this.parent = parent;
    }


    @Override
    public void tick() {
        if (parent != null) {
            position.add(parent.velocity.x() + velocity.x(), velocity.y());
        } else
            super.tick();

        if (position.x() <= 0 || position.y() <= 0 || position.x() >= SkyForceGame.getInstance().getWidth() || position.y() >= SkyForceGame.getInstance().getHeight())
            remove();

        for (GameObject object : SkyForceGame.getPanel().getColliding(this)) {
            if (parent != null && object instanceof EnemyJet jet) {
                jet.damage(10);
                remove();
                return;
            }
            if (parent == null && object instanceof MyJet jet) {
                jet.damage(10);
                remove();
                return;
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        if (parent != null) {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.drawRect(position.getX(), position.getY(), size.getX(), size.getY());
    }
}
