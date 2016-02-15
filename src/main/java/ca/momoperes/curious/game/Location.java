package ca.momoperes.curious.game;

public class Location {
    private double x, y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Location location) {
        return Math.sqrt(Math.pow(Math.abs(location.getX() - getX()), 2) + Math.pow(Math.abs(location.getY() - getY()), 2));
    }
}
