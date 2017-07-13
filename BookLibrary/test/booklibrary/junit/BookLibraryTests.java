/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary.junit;

import booklibrary.db.BookLibrary;
import booklibrary.entity.Book;
import booklibrary.entity.BookId;
import booklibrary.manager.BookManager;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author piotr
 */
public class BookLibraryTests {
    
    public BookLibraryTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void selectBookProvidesCorrectBook() {
        BookLibrary bookLibrary = new BookLibrary();
        BookManager bookManager = new BookManager(bookLibrary);
        Book book1 = new Book();
        Book book2 = new Book();
        Book result;
        
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
        
        result = bookManager.selectBook(bookId1);
        assertEquals(result.getTitle().matches("Human evolution"), true);
    }
    
    @Test
    public void deleteBookRemovesCorrectBook() {
        BookLibrary bookLibrary = new BookLibrary();
        BookManager bookManager = new BookManager(bookLibrary);
        Book book1 = new Book();
        Book book2 = new Book();
        Book result;
        
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
        
        bookManager.deleteBook(bookId1);
        result = bookManager.selectBook(bookId1);
        assertEquals(result == null, true);
    }
    
    @Test
    public void qeuryBookSelectsCorrectBook() {
        BookLibrary bookLibrary = new BookLibrary();
        BookManager bookManager = new BookManager(bookLibrary);
        Book book1 = new Book();
        Book book2 = new Book();
        Book result;
        
        BookId bookId1, bookId2, resultId;

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
        
        bookManager.deleteBook(bookId1);
        HashSet<BookId> bookIds = bookManager.searchByQuery("Steven King|Karol Darwin#Horror#2000");
        resultId = (BookId)bookIds.toArray()[0];
        result = bookManager.selectBook(resultId);
        assertEquals(result.getAuthor().matches("Steven King"),  true);
    }
    
    @Test
    public void lentBookCannotBeLentAgain() {
        BookLibrary bookLibrary = new BookLibrary();
        BookManager bookManager = new BookManager(bookLibrary);
        Book book1 = new Book();
        BookId bookId1;

        book1.setAuthor("Steven King")
             .setTitle("Horror")
             .setYear("2000")
             .setLent(true)
             .setLentFor("Ally McBeal");
        bookId1 = bookManager.insertBook(book1);
        
        bookManager.lendBook(bookId1, "Patty Anderson");
        book1 = bookManager.selectBook(bookId1);
        assertEquals(book1.getLentFor().matches("Ally McBeal"),  true);
    }
    
}
