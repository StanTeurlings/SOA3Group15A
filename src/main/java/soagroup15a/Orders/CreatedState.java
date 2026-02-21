package soagroup15a.Orders;

public class CreatedState implements State {

    @Override
    public void submitOrder(Order order) {
        order.setState(order.getSubmittedState());
        System.out.println("Order submitted.");
    }

    @Override
    public void payOrder(Order order) {
        System.out.println("You need to submit the order before you can pay.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cant cancel an order that has not been submitted. Please submit first.");
    }

    @Override
    public void changeOrder(Order order) {
        System.out.println("You can change the order while it's in created state.");
    }

    @Override
    public void checkDeadline(Order order) {
        System.out.println("No deadline for created orders.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Cannot complete an order that has not been paid. Please submit and pay first.");
    }

}
