public class CinemaShow {
    private static int totalBooked;
    private final String title;
    private final int capacity;
    private int seatsAvailable;
    public CinemaShow(String title) { this(title, 100); }
    public CinemaShow(String title, int capacity) { this.title = title; this.capacity = capacity; seatsAvailable = capacity; }
    public boolean book(int seats) { if (seats <= 0 || seats > seatsAvailable) return false; seatsAvailable -= seats; totalBooked += seats; return true; }
    public void cancel(int seats) { if (seats > 0) seatsAvailable = Math.min(capacity, seatsAvailable + seats); }
    public int getSeatsAvailable() { return seatsAvailable; }
    public static int getTotalBooked() { return totalBooked; }
}
