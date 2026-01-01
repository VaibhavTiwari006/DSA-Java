package Basics_01;
import java.util.Scanner;
class Cart {
    private double total = 0;
    void addItem(double price) {
        if (price > 0) {
            total += price;
            System.out.println("Item added: ₹" + price);
        }
    }
    void removeItem(double price) {
        if (price > 0 && price <= total) {
            total -= price;
            System.out.println("Item removed: ₹" + price);
        } else {
            System.out.println("Cannot remove item");
        }
    }
    void showBill() {
        System.out.println("Total Bill: Rs" + total);
    }
}
public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cart cart = new Cart();
        while (true) {
            System.out.println("\n1.Add  2.Remove  3.Bill  4.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> cart.addItem(sc.nextDouble());
                case 2 -> cart.removeItem(sc.nextDouble());
                case 3 -> cart.showBill();
                case 4 -> {
                    System.out.println("Thank you for shopping!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}