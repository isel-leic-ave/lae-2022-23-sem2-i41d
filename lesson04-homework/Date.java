public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int nextMonth(){ return (this.month < 12)? this.month + 1 : 1;}
}