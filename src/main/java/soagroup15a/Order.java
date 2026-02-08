package soagroup15a;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderNr;
    private boolean isStudentOrder;
    private List<MovieTicket> tickets;

    public Order(int orderNr, boolean isStudentOrder) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
        this.tickets = new ArrayList<>();
    }

    public int getOrderNr() {
        return orderNr;
    }

    public void addSeatReservation(MovieTicket ticket) {
        tickets.add(ticket);
    }

    public double calculatePrice() {
        double totalPrice = 0.0;
        int ticketCount = tickets.size();

        for (int i = 0; i < ticketCount; i++) {
            MovieTicket ticket = tickets.get(i);
            MovieScreening screening = ticket.getMovieScreening();

            double price = screening.getPricePerSeat();

            if (ticket.isPremiumTicket()) {
                price += isStudentOrder ? 2.0 : 3.0;
            }

            DayOfWeek day = screening.dateAndTime.getDayOfWeek();
            boolean isWeekday = day == DayOfWeek.MONDAY || day == DayOfWeek.TUESDAY
                    || day == DayOfWeek.WEDNESDAY || day == DayOfWeek.THURSDAY;

            boolean secondTicketFree = (i % 2 == 1) && (isStudentOrder || isWeekday);

            if (!secondTicketFree) {
                totalPrice += price;
            }
        }

        if (!isStudentOrder && ticketCount >= 6) {
            boolean allWeekend = true;

            for (MovieTicket ticket : tickets) {
                DayOfWeek day = ticket.getMovieScreening().dateAndTime.getDayOfWeek();
                if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                    allWeekend = false;
                    break;
                }
            }

            if (allWeekend) {
                totalPrice *= 0.9;
            }
        }

        return totalPrice;
    }

    public String export(TicketExportFormat format) {
        StringBuilder sb = new StringBuilder();

        switch (format) {
            case PLAINTEXT:
                sb.append("Order Number: ").append(orderNr).append("\n");
                sb.append("Student Order: ")
                        .append(isStudentOrder ? "Yes" : "No").append("\n");
                sb.append("Tickets:\n");

                for (MovieTicket ticket : tickets) {
                    sb.append(ticket).append("\n");
                }

                sb.append(String.format("Total Price: €%.2f%n", calculatePrice()));
                break;

            case JSON:
                sb.append("{\n");
                sb.append("  \"orderNr\": ").append(orderNr).append(",\n");
                sb.append("  \"isStudentOrder\": ").append(isStudentOrder).append(",\n");

                sb.append("  \"tickets\": [\n");
                for (int i = 0; i < tickets.size(); i++) {
                    sb.append("    {\n");
                    sb.append("      \"description\": \"").append(tickets.get(i)).append("\"\n");
                    sb.append("    }");
                    if (i < tickets.size() - 1) {
                        sb.append(",");
                    }
                    sb.append("\n");
                }
                sb.append("  ],\n");

                sb.append(String.format("  \"totalPrice\": %.2f\n", calculatePrice()));
                sb.append("}");
                break;
        }

        return sb.toString();
    }
}
