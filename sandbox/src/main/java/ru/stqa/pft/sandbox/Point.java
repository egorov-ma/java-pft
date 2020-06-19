package ru.stqa.pft.sandbox;

import static java.lang.Math.pow;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        return Math.sqrt(pow((p.x - this.x), 2) + pow((p.y - this.y), 2));
    }

}
