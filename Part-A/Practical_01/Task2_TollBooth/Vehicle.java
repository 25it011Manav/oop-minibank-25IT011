public record Vehicle(String number, String type) {
    public Vehicle {
        if (number == null || number.isBlank() || type == null) throw new IllegalArgumentException("Vehicle details are required");
    }
}
