package data;

public class Book extends Element{
    private String author;
    private String genre;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Book(String title, String isbn, int yearOfPublication, int numberOfPages, String author, String genre) {
        super(title, yearOfPublication, numberOfPages, isbn);
        this.author = author;
        this.genre = genre;
    }
    //utilizzo toString della classe astratta + quello della classe Libri
    @Override
    public String toString() {
        return super.toString() + "Libri{" +
                "autore='" + author + '\'' +
                ", genere='" + genre + '\'' +
                '}';
    }
}
