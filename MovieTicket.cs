class MovieTicket
{
    MovieScreening movieScreening;
    int rowNr;
    int seatNr;
    Boolean isPremium { get; }

    public MovieTicket(MovieScreening movieScreening, int rowNr, int seatNr, Boolean isPremium)
    {
        this.movieScreening = movieScreening;
        this.rowNr = rowNr;
        this.seatNr = seatNr;
        this.isPremium = isPremium;
    }

    public double GetPrice(Boolean isStudentOrder)
    {
        double price = movieScreening.PricePerSeat;
        if (isPremium)
        {
            price += isStudentOrder ? 2 : 3;
        }
    }

    public override string ToString()
    {
        string premiumText = isPremium ? " (Premium Seat)" : "";
        return $"Ticket for {movieScreening}, Row: {rowNr}, Seat: {seatNr}{premiumText}";
    }
}