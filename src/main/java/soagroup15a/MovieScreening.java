package src.main.java.soagroup15a;

import java.time.LocalDateTime;

public class MovieScreening {
    private Movie movie;
    LocalDateTime dateAndTime;
    private double pricePerSeat;

    public MovieScreening(
            Movie movie,
            LocalDateTime dateAndTime,
            double pricePerSeat) {
        this.movie = movie;
        this.dateAndTime = dateAndTime;
        this.pricePerSeat = pricePerSeat;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    @Override
    public String toString() {
        return movie.toString() + " at " + dateAndTime.toString();
    }
}