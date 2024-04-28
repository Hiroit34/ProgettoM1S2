package data;

import java.time.LocalDate;

public class Element {
    private String isbn;
    private String title;
    private LocalDate yearOfPublication;
    private int numberOfPages;

    public Element(String isbn, LocalDate yearOfPublication, int numberOfPages, String title){
        this.isbn = isbn;
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
