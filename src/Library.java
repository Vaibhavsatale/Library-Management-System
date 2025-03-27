import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Library 
{
    private Map<String, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) throws IllegalArgumentException {
        if (books.containsKey(book.getBookId())) {
            throw new IllegalArgumentException("Book ID already exists");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (!book.getAvailability().equals("Available") && !book.getAvailability().equals("Checked Out")) {
            throw new IllegalArgumentException("Availability must be 'Available' or 'Checked Out'");
        }

        books.put(book.getBookId(), book);
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Book searchBook(String identifier) {
        // Search by ID
        if (books.containsKey(identifier)) {
            return books.get(identifier);
        }

        // Search by title (case insensitive)
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(identifier)) {
                return book;
            }
        }

        return null;
    }

    public boolean updateBook(String bookId, String title, String author, String genre, String availability) 
            throws IllegalArgumentException {
        if (!books.containsKey(bookId)) {
            return false;
        }

        Book book = books.get(bookId);

        if (title != null) {
            if (title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            book.setTitle(title);
        }

        if (author != null) {
            if (author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty");
            }
            book.setAuthor(author);
        }

        if (genre != null) {
            book.setGenre(genre);
        }

        if (availability != null) {
            if (!availability.equals("Available") && !availability.equals("Checked Out")) {
                throw new IllegalArgumentException("Availability must be 'Available' or 'Checked Out'");
            }
            book.setAvailability(availability);
        }

        return true;
    }

    public boolean deleteBook(String bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            return true;
        }
        return false;
    }
}
