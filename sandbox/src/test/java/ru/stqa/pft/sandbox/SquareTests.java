package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square(5);
        Assert.assertEquals(s.area(), 25.0);
    }

    @Test
    public void firstTestDistance() {
        Point p1 = new Point(2, -7);
        Point p2 = new Point(8, 1);
        Assert.assertEquals(p1.distance(p2),10.0);
    }

    @Test
    public void secondTestDistance() {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(8, 9);
        Assert.assertEquals(p1.distance(p2),10.0);
    }

}
