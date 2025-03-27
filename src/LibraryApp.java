package digitalLibraryBookManagementSystem;

import java.util.Scanner;
import java.util.Collection;

public class LibraryApp {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    viewAllBooks();
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    updateBook();
                    break;
                case "5":
                    deleteBook();
                    break;
                case "6":
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            String bookId = scanner.nextLine();

            System.out.print("Enter Title: ");
            String title = scanner.nextLine();

            System.out.print("Enter Author: ");
            String author = scanner.nextLine();

            System.out.print("Enter Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Enter Availability (Available/Checked Out): ");
            String availability = scanner.nextLine();
            if (availability.isEmpty()) {
                availability = "Available";
            }

            Book book = new Book(bookId, title, author, genre, availability);
            library.addBook(book);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllBooks() {
        Collection<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\nAll Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        String identifier = scanner.nextLine();

        Book book = library.searchBook(identifier);
        if (book != null) {
            System.out.println("\nBook Found:");
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine();

        System.out.println("Leave fields blank to keep current values:");

        System.out.print("Enter new Title: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) title = null;

        System.out.print("Enter new Author: ");
        String author = scanner.nextLine();
        if (author.isEmpty()) author = null;

        System.out.print("Enter new Genre: ");
        String genre = scanner.nextLine();
        if (genre.isEmpty()) genre = null;

        System.out.print("Enter new Availability (Available/Checked Out): ");
        String availability = scanner.nextLine();
        if (availability.isEmpty()) availability = null;

        try {
            if (library.updateBook(bookId, title, author, genre, availability)) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();

        if (library.deleteBook(bookId)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
}