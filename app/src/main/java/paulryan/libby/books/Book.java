package paulryan.libby.books;

/**
 * Created by Ryan on 10.04.2016.
 */
public class Book {

    private int id;
    private String book;
    private String author;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Book() {   }

    public Book(String book, String author, String year) {
        this.book = book;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}