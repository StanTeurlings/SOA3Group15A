class Movie
{
    string title;
    private List<MovieScreening> _screenings;


    public Movie(string title)
    {
        this.title = title;
        _screenings = new List<MovieScreening>();
    }

    static void Addscreening(MovieScreening screening)
    {
        _screenings.Add(screening);
    }

    public override string ToString()
    {
        return title;
    }
}