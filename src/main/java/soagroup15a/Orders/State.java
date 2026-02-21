package soagroup15a.Orders;

interface State {
    void submitOrder(Order order);

    void payOrder(Order order);

    void cancelOrder(Order order);

    void changeOrder(Order order);

    void checkDeadline(Order order);

    void completeOrder(Order order);
}
