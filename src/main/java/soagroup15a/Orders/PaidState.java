package soagroup15a.Orders;

public class PaidState implements State {
    @Override
    public void submitOrder(Order order) {
        System.out.println("Order is already submitted and paid.");
    }

    @Override
    public void payOrder(Order order) {
        System.out.println("Order is already paid.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Can't cancel a paid order. Please contact support.");
    }

    @Override
    public void changeOrder(Order order) {
        System.out.println("Changing a paid order. Please contact support.");
    }

    @Override
    public void checkDeadline(Order order) {
        System.out.println("No deadline for paid orders.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Completing order...");
        order.setState(order.getCompletedState());
    }

}
