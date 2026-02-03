using System.Runtime;
using System.Runtime.CompilerServices;
using Microsoft.VisualBasic;

class Order
{
    int orderNr { get; }
    Boolean isStudentOrder;
    private List<MovieTicket> _tickets = new List<MovieTicket>();

    public Order(int orderNr, Boolean isStudentOrder)
    {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
    }

    public void addSeatReservation(MovieTicket ticket)
    {
        _tickets.Add(ticket);
    }

    public double calculatePrice()
    {
        double totalPrice = 0;
        int amountOfTickets = _tickets.Count;

        for (int i = 0; i < amountOfTickets; i++)
        {
            MovieTicket ticket = _tickets[i];
            MovieScreening screening = ticket.MovieScreening;

            bool isWeekday =
                screening.DateAndTime.DayOfWeek >= DayOfWeek.Monday &&
                screening.DateAndTime <= screening.DateAndTime.AddDays(0) &&
                screening.DateAndTime.DayOfWeek <= DayOfWeek.Thursday;

            bool secondTicketFree =
                i % 2 == 1 && (isStudentOrder || isWeekday);

            if (secondTicketFree)
            {
                continue;
            }

            double price = ticket.GetPrice(isStudentOrder);

            totalPrice += price;
        }

        bool isWeekend =
            _tickets[0].MovieScreening.DateAndTime.DayOfWeek == DayOfWeek.Friday ||
            _tickets[0].MovieScreening.DateAndTime.DayOfWeek == DayOfWeek.Saturday ||
            _tickets[0].MovieScreening.DateAndTime.DayOfWeek == DayOfWeek.Sunday;

        if (!isStudentOrder && isWeekend && amountOfTickets >= 6)
        {
            totalPrice *= 0.9;
        }

        return totalPrice;
    }


    public void export(TicketExportFormat format)
    {
        switch (format)
        {
            case TicketExportFormat.PLAINTEXT:
                Console.WriteLine($"Order #{orderNr}");
                Console.WriteLine(isStudentOrder ? "Student order" : "Regular order");
                Console.WriteLine("Tickets:");

                foreach (MovieTicket ticket in _tickets)
                {
                    Console.WriteLine(
                        $"- {ticket.MovieScreening.Movie} | " +
                        $"{ticket.MovieScreening.DateAndTime} | " +
                        $"Row {ticket.RowNr}, Seat {ticket.SeatNr}" +
                        (ticket.IsPremium ? " (Premium)" : "")
                    );
                }

                Console.WriteLine($"Total price: €{calculatePrice():0.00}");
                break;

            case TicketExportFormat.JSON:
                Console.WriteLine("{");
                Console.WriteLine($"  \"orderNr\": {orderNr},");
                Console.WriteLine($"  \"isStudentOrder\": {isStudentOrder.ToString().ToLower()},");
                Console.WriteLine($"  \"totalPrice\": {calculatePrice():0.00},");
                Console.WriteLine("  \"tickets\": [");

                for (int i = 0; i < _tickets.Count; i++)
                {
                    MovieTicket ticket = _tickets[i];

                    Console.WriteLine("    {");
                    Console.WriteLine($"      \"movie\": \"{ticket.MovieScreening.Movie}\",");
                    Console.WriteLine($"      \"dateTime\": \"{ticket.MovieScreening.DateAndTime}\",");
                    Console.WriteLine($"      \"row\": {ticket.RowNr},");
                    Console.WriteLine($"      \"seat\": {ticket.SeatNr},");
                    Console.WriteLine($"      \"premium\": {ticket.IsPremium.ToString().ToLower()}");
                    Console.Write("    }");

                    if (i < _tickets.Count - 1)
                        Console.WriteLine(",");
                    else
                        Console.WriteLine();
                }

                Console.WriteLine("  ]");
                Console.WriteLine("}");
                break;

            default:
                throw new ArgumentOutOfRangeException(nameof(format), format, null);
        }
    }

}