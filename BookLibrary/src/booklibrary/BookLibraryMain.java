/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary;

import booklibrary.db.BookLibrary;
import booklibrary.entity.Book;
import booklibrary.entity.BookId;
import booklibrary.manager.BookManager;
import java.util.Set;

/**
 *
 * @author piotr
 */
public class BookLibraryMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        BookLibrary bookLibrary = new BookLibrary();
        BookManager bookManager = new BookManager(bookLibrary);
        
        Book book1 = new Book();
        Book book2 = new Book();
        BookId bookId1, bookId2;

        book1.setAuthor("Karol Darwin")
             .setTitle("Human evolution")
             .setYear("1900")
             .setLent(false);
        book2.setAuthor("Steven King")
             .setTitle("Horror")
             .setYear("2000")
             .setLent(true)
             .setLentFor("Ally McBeal");
        
        bookId1 = bookManager.insertBook(book1);
        bookId2 = bookManager.insertBook(book2);
        System.out.println(bookId1);
        bookManager.printBook(bookId1);
        System.out.println(bookId2);
        bookManager.printBook(bookId2);
        //bookManager.deleteBook(bookId1);
        //bookManager.deleteBook(bookId2);
        System.out.println("++++++++++++++++++");
        //bookManager.lendBook(bookId1, "Kisieliński");
        //bookManager.printAllBooks();
        Set<BookId> bookIds = bookManager.searchByQuery("King|Darwin#Małpy#1900");
        bookManager.printBooks(bookIds);

    }
    
}
