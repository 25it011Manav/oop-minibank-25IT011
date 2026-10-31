public final class Thermostat {
    private static final int MINIMUM = 16, MAXIMUM = 30;
    private static int activeCount;
    private final String location;
    private int temperature;
    public Thermostat(String location) { this(location, 22); }
    public Thermostat(String location, int initialTemperature) {
        this.location = location;
        temperature = initialTemperature >= MINIMUM && initialTemperature <= MAXIMUM ? initialTemperature : 22;
        activeCount++;
    }
    public void raise() { setTemperature(temperature + 1); }
    public void lower() { setTemperature(temperature - 1); }
    private void setTemperature(int next) {
        if (next > MAXIMUM) System.out.println("Already at maximum (30)");
        else if (next < MINIMUM) System.out.println("Already at minimum (16)");
        else temperature = next;
    }
    public int getTemperature() { return temperature; }
    public static int getActiveCount() { return activeCount; }
}
