import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("bike", 0); counts.put("car", 0); counts.put("truck", 0);
        int total = 0;
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter vehicle number (or 'done'): ");
                String number = input.next();
                if (number.equalsIgnoreCase("done")) break;
                System.out.print("Enter type (bike/car/truck): ");
                String type = input.next().toLowerCase();
                Vehicle vehicle = new Vehicle(number, type);
                int toll = switch (vehicle.type()) { case "bike" -> 20; case "car" -> 50; case "truck" -> 150; default -> 0; };
                if (toll == 0) System.out.println("Unknown vehicle type; no toll added.");
                else { counts.merge(type, 1, Integer::sum); total += toll; }
            }
        }
        String frequent = counts.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getKey();
        System.out.println("Total toll: " + total);
        System.out.println("Most frequent: " + frequent);
    }
}
