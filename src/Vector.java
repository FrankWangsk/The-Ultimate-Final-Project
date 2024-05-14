public class Vector {

    private double x;
    private double y;

    public Vector() {
        this(0, 0);
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void add(Vector other) {
        add(other.x, other.y);
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void multiply(Vector vector) {
        multiply(vector.x, vector.y);
    }

    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public void scale(double scale) {
        this.x *= scale;
        this.y *= scale;
    }

    public Vector copy() {
        return new Vector(x, y);
    }

    public boolean isZero() {
        return Math.abs(x) <= 1e-15 && Math.abs(y) <= 1e-15;
    }

    @Override
    public String toString() {
        return "Vector[x=" + x + ",y=" + y + "]";
    }
}
