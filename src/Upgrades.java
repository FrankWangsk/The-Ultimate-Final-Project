public class Upgrades {

    public static final UpgradeOption SHOOTING_SPEED = new UpgradeOption("Shooting Speed", 15);
    public static final UpgradeOption BULLET_SPEED = new UpgradeOption("Bullet Speed", 15);

    public static final UpgradeOption TRISHOOT = new UpgradeOption("Trishoot", 1) {
        @Override
        public int getPrice() {
            return 100;
        }
    };

}
