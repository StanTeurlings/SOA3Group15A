package soagroup15a.Orders;

import soagroup15a.MovieScreening;

public class Order {
    State CreatedState;
    State SubmittedState;
    State ProvisionalState;
    State PaidState;
    State CancelledState;
    State CompletedState;

    State state = CreatedState;

    public Order() {
        CreatedState = new CreatedState();
        SubmittedState = new SubmittedState();
        ProvisionalState = new ProvisionalState();
        PaidState = new PaidState();
        CancelledState = new CancelledState();
        CompletedState = new CompletedState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void submitOrder() {
        state.submitOrder(this);
    }

    public void payOrder() {
        state.payOrder(this);
    }

    public void cancelOrder() {
        state.cancelOrder(this);
    }

    public void changeOrder() {
        state.changeOrder(this);
    }

    public void checkDeadline() {
        state.checkDeadline(this);
    }

    public void completeOrder() {
        state.completeOrder(this);
    }

    public State getCreatedState() {
        return CreatedState;
    }

    public State getSubmittedState() {
        return SubmittedState;
    }

    public State getProvisionalState() {
        return ProvisionalState;
    }

    public State getPaidState() {
        return PaidState;
    }

    public State getCancelledState() {
        return CancelledState;
    }

    public State getCompletedState() {
        return CompletedState;
    }

    public MovieScreening getMovieScreening() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovieScreening'");
    }
}