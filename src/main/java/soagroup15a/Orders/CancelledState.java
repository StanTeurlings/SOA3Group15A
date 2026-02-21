package soagroup15a.Orders;

public class CancelledState implements State {
    @Override
    public void submitOrder(Order order) {
        System.out.println("Cannot submit an order that has been cancelled.");
    }

    @Override
    public void payOrder(Order order) {
        System.out.println("Cannot pay for an order that has been cancelled.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is already cancelled.");
    }

    @Override
    public void changeOrder(Order order) {
        System.out.println("Cannot change an order that has been cancelled.");
    }

    @Override
    public void checkDeadline(Order order) {
        System.out.println("No deadline for cancelled orders.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Cannot complete an order that has been cancelled.");
    }

}
