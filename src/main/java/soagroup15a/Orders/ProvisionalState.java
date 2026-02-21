package soagroup15a.Orders;

import java.time.LocalDateTime;

import soagroup15a.MovieScreening;

public class ProvisionalState implements State {
    @Override
    public void submitOrder(Order order) {
        System.out.println("Submitting order...");
        order.setState(order.getSubmittedState());
    }

    @Override
    public void payOrder(Order order) {
        order.setState(order.getPaidState());
        System.out.println("Order paid.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cancelling order...");
        order.setState(order.getCancelledState());
    }

    @Override
    public void changeOrder(Order order) {
        System.out.println(
                "U cannot change a provisional order. Please cancel and create a new one if you want to make changes.");
    }

    @Override
    public void checkDeadline(Order order) {
        MovieScreening screening = order.getMovieScreening();
        if (screening != null) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime screeningTime = screening.getDateAndTime();
            if (now.isAfter(screeningTime.minusHours(1))) {
                System.out.println("Deadline has passed. Order is now cancelled.");
                order.setState(order.getCancelledState());
            } else {
                System.out.println("Deadline has not passed. You can still submit or change the order.");
            }
        } else {
            System.out.println("No movie screening associated with this order.");
        }
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Cannot complete a provisional order. Please submit and pay first.");
    }

}
