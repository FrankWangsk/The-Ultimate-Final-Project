public class UpgradeOption {

    private final String name;
    private final int limit;
    private int level;


    public UpgradeOption(String name) {
        this(name, Integer.MAX_VALUE);
    }

    public UpgradeOption(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public int getPrice() {
        return (level + 1) * 10;
    }

    public boolean canUpgrade() {
        return level < limit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void upgrade() {
        if (!canUpgrade())
            return;
        level++;
    }
}
