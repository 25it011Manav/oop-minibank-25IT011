public class Driver {
    public static void main(String[] args) {
        Thermostat livingRoom = new Thermostat("Living Room", 25);
        new Thermostat("Bedroom");
        for (int i = 0; i < 10; i++) livingRoom.raise();
        for (int i = 0; i < 20; i++) livingRoom.lower();
        System.out.println("Current Temperature: " + livingRoom.getTemperature());
        System.out.println("Active Thermostats: " + Thermostat.getActiveCount());
    }
}
