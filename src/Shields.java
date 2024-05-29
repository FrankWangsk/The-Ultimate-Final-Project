import java.awt.*;

public enum Shields {
    COMMON(25, 100, Color.decode("#A8A8A8")),
    RARE(50, 200, Color.decode("#51A8D6")),
    EPIC(75, 400, Color.decode("#B237C8")),
    MYTHIC(100, Double.POSITIVE_INFINITY, Color.decode("#FF4E1D"));

    private final double shieldPoints;
    private final double evoPoints;
    private final Color color;

    Shields(double shieldPoints, double evoPoints, Color color) {
        this.shieldPoints = shieldPoints;
        this.evoPoints = evoPoints;
        this.color = color;
    }

    public double getShieldPoints() {
        return shieldPoints;
    }

    public double getEvoPoints() {
        return evoPoints;
    }

    public Color getColor() {
        return color;
    }
}
