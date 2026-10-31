public class Driver {
    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Inception", 50);
        System.out.println("Book 30: " + show.book(30) + " | Seats left: " + show.getSeatsAvailable());
        System.out.println("Book 25: " + show.book(25) + " | Seats left: " + show.getSeatsAvailable());
        show.cancel(10);
        System.out.println("After cancel 10 | Seats left: " + show.getSeatsAvailable());
        System.out.println("Total Booked Across All Shows: " + CinemaShow.getTotalBooked());
    }
}
