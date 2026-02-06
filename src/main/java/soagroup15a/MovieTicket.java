package src.main.java.soagroup15a;

public class MovieTicket {
    private int rowNr;
    private int seatNr;
    private boolean isPremium;
    private MovieScreening movieScreening;

    public MovieTicket(
            MovieScreening movieScreening,
            boolean isPremiumReservation,
            int seatRow,
            int seatnr) {
        this.movieScreening = movieScreening;
        this.isPremium = isPremiumReservation;
        this.rowNr = seatRow;
        this.seatNr = seatnr;
    }

    public boolean isPremiumTicket() {
        return isPremium;
    }

    public MovieScreening getMovieScreening() {
        return movieScreening;
    }

    @Override
    public String toString() {
        String premiumText = isPremium ? " (Premium)" : "";
        return "Ticket for " + movieScreening + ", Row: " + rowNr + ", Seat: " + seatNr + premiumText;
    }
}