package soagroup15a.Orders;

import java.time.Duration;
import java.time.LocalDateTime;

import soagroup15a.MovieScreening;

public class SubmittedState implements State {
    @Override
    public void submitOrder(Order order) {
        System.out.println("Order is already submitted.");
    }

    @Override
    public void payOrder(Order order) {
        order.setState(order.getPaidState());
        System.out.println("Order is now paid.");
    }

    @Override
    public void cancelOrder(Order order) {
        order.setState(order.getCancelledState());
        System.out.println("Order is now cancelled.");
    }

    @Override
    public void changeOrder(Order order) {
        System.out.println("Cannot change a submitted order. Please cancel and create a new one.");
    }

    @Override
    public void checkDeadline(Order order) {
        System.out.println("Checking deadline for submitted order...");

        MovieScreening screening = order.getMovieScreening();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime screeningTime = screening.getDateAndTime();

        long hoursUntilScreening = Duration.between(now, screeningTime).toHours();

        if (hoursUntilScreening <= 12) {
            order.setState(order.getCancelledState());
            System.out.println("Less than 12 hours left. Order is now cancelled.");
        } else if (hoursUntilScreening <= 24) {
            order.setState(order.getProvisionalState());
            System.out.println("Less than 24 hours left. Order is now provisional.");
        } else {
            System.out.println("More than 24 hours left. No changes made.");
        }
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Cannot complete a submitted order. Please pay first.");
    }

}
