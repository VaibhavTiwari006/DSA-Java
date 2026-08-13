package Basics_01;
import java.util.Scanner;
class Rectangle {

    double width, length, area;
    String color;

    void set_length(double l) {
        length = l;
    }

    void set_width(double w) {
        width = w;
    }

    void set_color(String c) {
        color = c;
    }

    void find_area() {
        area = length * width;
    }
}

public class RectangleTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();

        System.out.println("Enter details for Rectangle 1:");
        System.out.print("Enter length: ");
        r1.set_length(sc.nextDouble());

        System.out.print("Enter width: ");
        r1.set_width(sc.nextDouble());

        sc.nextLine();

        System.out.print("Enter color: ");
        r1.set_color(sc.nextLine());

        r1.find_area();

        System.out.println("\nEnter details for Rectangle 2:");
        System.out.print("Enter length: ");
        r2.set_length(sc.nextDouble());

        System.out.print("Enter width: ");
        r2.set_width(sc.nextDouble());

        sc.nextLine();

        System.out.print("Enter color: ");
        r2.set_color(sc.nextLine());

        r2.find_area();

        if (r1.area == r2.area && r1.color.equals(r2.color)) {
            System.out.println("\nMatching Rectangles");
        } else {
            System.out.println("\nNon-Matching Rectangles");
        }

        sc.close();
    }
}