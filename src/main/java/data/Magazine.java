package data;

import java.time.LocalDate;

public class Magazine extends Element{
    private Periodicity periodicity;

    public Magazine(String title, String isbn, LocalDate yearOfPublication, int numberOfPages, Periodicity periodicity) {
        super(title, yearOfPublication, numberOfPages, isbn );
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return super.toString() + "Riviste{" +
                "periodicità=" + periodicity +
                '}';
    }
}
