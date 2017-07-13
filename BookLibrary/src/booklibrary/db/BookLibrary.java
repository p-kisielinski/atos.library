/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary.db;

import booklibrary.entity.Book;
import booklibrary.entity.BookId;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;


/**
 *
 * @author Piotr Kisielinski
 */
public class BookLibrary {
    
    private HashMap bookDb;
    
    public BookLibrary() {
        bookDb = new HashMap();
    }
    
    public BookId getNewBookId() {
        BookId bookId = new BookId(UUID.randomUUID().toString());
        
        return bookId;
    }
    
    public Book deleteBook(BookId bookId) {
        Book book = selectBook(bookId);
        
        if(book != null && !book.isLent()) {
            return (Book)bookDb.remove(bookId);
        }
        else 
            return null;
    }
    
    public BookId insertBook(Book book) {
        BookId bookId = getNewBookId();
        
        try {
            bookDb.put(bookId, book);
        } catch( Exception e ) {
            return null;
        }
        finally {
        }
        return bookId;
    }

    public Book selectBook(BookId bookId) {
        return (Book)bookDb.get(bookId);
    }
    
    public Set selectAllKeys() {
        return bookDb.keySet();
    }
    
}
