package src.main.java.soagroup15a;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        Movie movie1 = new Movie("Movie 1");
        Movie movie2 = new Movie("Movie 2");

        MovieScreening screening1 = new MovieScreening(movie1, LocalDateTime.of(2026, Month.FEBRUARY, 3, 18, 30), 10.0);
        MovieScreening screening2 = new MovieScreening(movie2, LocalDateTime.of(2026, Month.FEBRUARY, 8, 20, 0), 12.0);

        movie1.addScreening(screening1);
        movie2.addScreening(screening2);

        Order studentOrder = new Order(1, true);

        studentOrder.addSeatReservation(new MovieTicket(screening1, false, 5, 10));
        studentOrder.addSeatReservation(new MovieTicket(screening1, true, 5, 11));
        studentOrder.addSeatReservation(new MovieTicket(screening1, false, 5, 12));
        studentOrder.addSeatReservation(new MovieTicket(screening1, true, 5, 13));

        Order weekendOrder = new Order(2, false);

        weekendOrder.addSeatReservation(new MovieTicket(screening2, false, 3, 5));
        weekendOrder.addSeatReservation(new MovieTicket(screening2, true, 3, 6));
        weekendOrder.addSeatReservation(new MovieTicket(screening2, false, 3, 7));
        weekendOrder.addSeatReservation(new MovieTicket(screening2, true, 3, 8));
        weekendOrder.addSeatReservation(new MovieTicket(screening2, false, 3, 9));
        weekendOrder.addSeatReservation(new MovieTicket(screening2, true, 3, 10));

        System.out.println("Student Order Total: €" + studentOrder.calculatePrice());
        System.out.println("Weekend Order Total: €" + weekendOrder.calculatePrice());

        System.out.println("Plaintext Export of Student Order:");
        System.out.println(studentOrder.export(TicketExportFormat.PLAINTEXT));

        System.out.println("JSON Export of Weekend Order:");
        System.out.println(weekendOrder.export(TicketExportFormat.JSON));
    }
}
