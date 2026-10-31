import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        final int price = 15;
        int paid = 0;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Snack price is 15. Enter coins (ONE, TWO, FIVE, TEN):");
            while (paid < price) {
                try {
                    Coin coin = Coin.valueOf(input.next().trim().toUpperCase());
                    paid += coin.value();
                    System.out.println("Inserted: " + coin.value() + " | Total so far: " + paid);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Please enter a listed coin.");
                }
            }
        }
        System.out.println("Paid. Change: " + (paid - price));
    }
}
