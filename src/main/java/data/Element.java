package data;

public class Element {
    private String isbn;
    private String title;
    private int yearOfPublication;
    private int numberOfPages;

    public Element(String isbn, int yearOfPublication, int numberOfPages, String title){
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

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
