
public class ScoreManager {
    private int score = 0;
    private int bossCounter = 0;

    public void addScore(int points) {
        score += points;
        if (!SkyForceGame.getPanel().isInBossFight())
            bossCounter += points;
        if (bossCounter >= getLevel() * 40) {
            bossCounter = 0;
            SkyForceGame.getPanel().enterBossFight(getLevel());
        }
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
        return (int) (2 + Math.log10(score));
    }

}
