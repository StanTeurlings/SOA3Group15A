import org.junit.jupiter.api.Test;

import soagroup15a.Movie;
import soagroup15a.MovieScreening;
import soagroup15a.MovieTicket;
import soagroup15a.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    // Helper: screening maken
    private MovieScreening createScreening(double price, LocalDateTime dateTime) {
        Movie movie = new Movie("TestMovie");
        return new MovieScreening(movie, dateTime, price);
    }

    // Helper: ticket maken
    private MovieTicket createTicket(MovieScreening screening, boolean premium) {
        return new MovieTicket(screening, premium, 1, 1);
    }

    // -------------------------------
    // Testcase 1: 2 tickets student premium = 12
    // -------------------------------
    @Test
    void testCase1_studentPremiumTwoTickets() {
        Order order = new Order(1, true);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 10, 20, 0));

        order.addSeatReservation(createTicket(screening, true));
        order.addSeatReservation(createTicket(screening, true));

        assertEquals(12.0, order.calculatePrice());
    }

    // -------------------------------
    // Testcase 2: 2 tickets student normal = 10
    // -------------------------------
    @Test
    void testCase2_studentNormalTwoTickets() {
        Order order = new Order(2, true);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 10, 20, 0));

        order.addSeatReservation(createTicket(screening, false));
        order.addSeatReservation(createTicket(screening, false));

        assertEquals(10.0, order.calculatePrice());
    }

    // -------------------------------
    // Testcase 3: 2 tickets Monday premium = 13
    // -------------------------------
    @Test
    void testCase3_mondayPremiumTwoTickets() {
        Order order = new Order(3, false);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 9, 20, 0)); // Monday

        order.addSeatReservation(createTicket(screening, true));
        order.addSeatReservation(createTicket(screening, true));

        assertEquals(13.0, order.calculatePrice());
    }

    // -------------------------------
    // Testcase 4: 2 tickets Monday normal = 10
    // -------------------------------
    @Test
    void testCase4_mondayNormalTwoTickets() {
        Order order = new Order(4, false);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 9, 20, 0)); // Monday

        order.addSeatReservation(createTicket(screening, false));
        order.addSeatReservation(createTicket(screening, false));

        assertEquals(10.0, order.calculatePrice());
    }

    // -------------------------------
    // Testcase 5: 6 tickets premium weekend + korting = 70.2
    // -------------------------------
    @Test
    void testCase5_sixPremiumTicketsWeekendDiscount() {
        Order order = new Order(5, false);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 8, 20, 0)); // Sunday

        for (int i = 0; i < 6; i++) {
            order.addSeatReservation(createTicket(screening, true));
        }

        assertEquals(70.2, order.calculatePrice(), 0.01);
    }

    // -------------------------------
    // Testcase 6: 6 tickets normal weekend + korting = 54
    // -------------------------------
    @Test
    void testCase6_sixNormalTicketsWeekendDiscount() {
        Order order = new Order(6, false);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 8, 20, 0)); // Sunday

        for (int i = 0; i < 6; i++) {
            order.addSeatReservation(createTicket(screening, false));
        }

        assertEquals(54.0, order.calculatePrice(), 0.01);
    }

    // -------------------------------
    // Testcase 7: 2 tickets premium weekend = 26
    // -------------------------------
    @Test
    void testCase7_twoPremiumTicketsNoFreeTicket() {
        Order order = new Order(7, false);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 7, 20, 0)); // Saturday

        order.addSeatReservation(createTicket(screening, true));
        order.addSeatReservation(createTicket(screening, true));

        assertEquals(26.0, order.calculatePrice());
    }

    // -------------------------------
    // Testcase 8: 2 tickets normal weekend = 20
    // -------------------------------
    @Test
    void testCase8_twoNormalTicketsNoFreeTicket() {
        Order order = new Order(8, false);

        MovieScreening screening = createScreening(10, LocalDateTime.of(2026, 2, 7, 20, 0)); // Saturday

        order.addSeatReservation(createTicket(screening, false));
        order.addSeatReservation(createTicket(screening, false));

        assertEquals(20.0, order.calculatePrice());
    }
}
