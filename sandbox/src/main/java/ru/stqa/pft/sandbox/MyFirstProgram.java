package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        hello("мир");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangel r = new Rectangel(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(1, 2);
        Point p2 = new Point(5, 6);
        System.out.println("Расстояние между двумя точками (" + p1.x + ", " + p1.y + ") и ("
                + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
    }

    public static void hello(String sumbody) {
        System.out.println("Привет, " + sumbody + "!");
    }
}
