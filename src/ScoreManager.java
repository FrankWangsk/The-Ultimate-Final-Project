public class ScoreManager {
    private int score = 0;
    private int bossCounter = 0;
    private int money = 0;


    public void addScore(int points) {
        score += points;
        money += points;
        if (!SkyForceGame.getPanel().isInBossFight())
            bossCounter += points;
        if (bossCounter >= getLevel() * 40) {
            bossCounter = 0;
            SkyForceGame.getPanel().enterBossFight(getLevel());
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public int getLevel() {
        if (score <= 50) {
            return 1;
        }
        if (score <= 100)
            return 2;
        return (int) Math.log(score) - 1;
    }
}

