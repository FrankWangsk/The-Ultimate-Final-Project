import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StorePanel extends JPanel {

    private final List<StoreEntry> upgrades;

    public StorePanel() {
        setLayout(new FlowLayout());

        upgrades = new ArrayList<>();

        List.of(Upgrades.SHOOTING_SPEED, Upgrades.BULLET_SPEED, Upgrades.TRISHOOT).forEach(upgrade -> {
            StoreEntry storeEntry = new StoreEntry(upgrade);
            upgrades.add(storeEntry);
            add(storeEntry);
        });
        refresh();
    }

    public void refresh() {
        for (StoreEntry upgrade : upgrades) {
            upgrade.refresh();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Money: " + SkyForceGame.getInstance().getScoreManager().getMoney(), getWidth() - 100, 20);
    }

    class StoreEntry extends JPanel {

        private final UpgradeOption upgrade;
        private final JLabel nameLabel;
        private final JLabel levelLabel;
        private final JButton upgradeButton;

        public StoreEntry(UpgradeOption upgrade) {
            super(new BorderLayout());
            this.upgrade = upgrade;
            ScoreManager scoreManager = SkyForceGame.getInstance().getScoreManager();

            nameLabel = new JLabel(upgrade.getName());
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            levelLabel = new JLabel();
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            upgradeButton = new JButton();
            upgradeButton.setFocusable(false);
            upgradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            upgradeButton.addActionListener(_ -> {
                if (upgrade.canUpgrade() && scoreManager.getMoney() >= upgrade.getPrice()) {
                    scoreManager.setMoney(scoreManager.getMoney() - upgrade.getPrice());
                    upgrade.upgrade();
                    StorePanel.this.refresh();
                }
            });

            refresh();

            add(nameLabel, BorderLayout.NORTH);
            add(levelLabel, BorderLayout.CENTER);
            add(upgradeButton, BorderLayout.SOUTH);

        }

        public void refresh() {
            ScoreManager scoreManager = SkyForceGame.getInstance().getScoreManager();
            String levelText = upgrade.getLevel() + "";
            if (upgrade.getLimit() != Integer.MAX_VALUE)
                levelText += "/" + upgrade.getLimit();
            levelLabel.setText(levelText);

            upgradeButton.setText(upgrade.canUpgrade() ? "Upgrade, Price: " + upgrade.getPrice() : "X");
            upgradeButton.setEnabled(upgrade.canUpgrade() && scoreManager.getMoney() >= upgrade.getPrice());
        }

    }

}
