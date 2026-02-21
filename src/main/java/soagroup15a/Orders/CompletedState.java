package soagroup15a.Orders;

public class CompletedState implements State {
    @Override
    public void submitOrder(Order order) {
        System.out.println("Cannot submit a completed order.");
    }

    @Override
    public void payOrder(Order order) {
        System.out.println("Cannot pay for a completed order.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel a completed order.");
    }

    @Override
    public void changeOrder(Order order) {
        System.out.println("Cannot change a completed order.");
    }

    @Override
    public void checkDeadline(Order order) {
        System.out.println("No deadline for completed orders.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Order is already completed.");
    }
}
