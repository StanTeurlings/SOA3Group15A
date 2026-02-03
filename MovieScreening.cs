class MovieScreening
{
    Movie movie;
    DateTime dateAndTime { get; }
    double pricePerSeat { get; }

    public MovieScreening(Movie movie, DateTime dateAndTime, double pricePerSeat)
    {
        this.movie = movie;
        this.dateAndTime = dateAndTime;
        this.pricePerSeat = pricePerSeat;
    }

    public override string ToString()
    {
        return $"{movie} at {dateAndTime}, Price per seat: {pricePerSeat}";
    }

}