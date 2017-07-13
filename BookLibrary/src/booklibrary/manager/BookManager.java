/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary.manager;

import booklibrary.db.BookLibrary;
import booklibrary.entity.Book;
import booklibrary.entity.BookId;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Piotr Kisielinski
 */
public class BookManager {
   
    private BookLibrary   bookLibrary;
        
    public BookManager(BookLibrary bookLibrary) {
        this.bookLibrary = bookLibrary;
    }
    
    public BookManager() {
    }
  
    public Book deleteBook(BookId bookId) {
        return bookLibrary.deleteBook(bookId);
    }
    
    public BookId insertBook(Book book) {
        return bookLibrary.insertBook(book);
    }

    public Book selectBook(BookId bookId) {
        return bookLibrary.selectBook(bookId);
    }
    
    public boolean printBook(BookId bookId) {
        
        Book book = bookLibrary.selectBook(bookId);
        if( book != null ) {
            System.out.println("Book Id : " + bookId);
            System.out.println("Author  : " + book.getAuthor());
            System.out.println("Title   : " + book.getTitle());
            System.out.println("Year    : " + book.getYear());
            System.out.println("Lent    : " + (book.isLent() ? "Yes" : "No"));
            System.out.println("Lent For: " + (book.isLent() ? book.getLentFor() : "-"));
            
            return true;
        }
        else {
            System.out.println("No book with Book Id: " + bookId);
            
            return false;
        }
    }
    
    public void printBooks(Set<BookId> bookIds) {
        for(BookId bookId : bookIds) {
              printBook(bookId);
        }
    }
    
    public boolean printAllBooks() {
        Set<BookId> bookIds = bookLibrary.selectAllKeys();
        int booksCount = 0;
        int booksLent  = 0;
        
        if(bookIds.isEmpty()) {
            System.out.println("No books found");
            return false;
        }
        else {
          booksCount = bookIds.size();
          
          for(BookId bookId : bookIds) {
              if( selectBook(bookId).isLent() )
                 booksLent++; 
              printBook(bookId);
          }
          System.out.println("Books count : " + booksCount);
          System.out.println("Books lent  : " + booksLent);
          return true;
        }
    }
    
    public boolean lendBook(BookId bookId, String lendFor) {
        Book book = selectBook(bookId);
        
        if( book != null && !book.isLent() ) {
            book.setLent(true);
            book.setLentFor(lendFor);
            
            return true;
        }
        else
            return false;
    }
    
    /**
     * @param query String : format Titlequery[#Authorquery[#Yearquery]]
     * 
     */
    public HashSet<BookId> searchByQuery(String query) {
        Set<BookId>       allBookIds = bookLibrary.selectAllKeys();
        HashSet<BookId> bookIds    = new HashSet();
        Book              book;
        String            authorQuery = ".*";
        String            titleQuery  = ".*";
        String            yearQuery   = "[0-9]*";
        
        if( query != null && query.length() > 0 ) {
            String[] queries = query.split("#");
            switch(queries.length) {
                case 1: 
                        if( queries[0].length()>0 )
                            authorQuery=queries[0];
                        titleQuery=".*";
                        yearQuery="[0-9]*";
                        break;
                case 2: 
                        if( queries[0].length()>0 )
                           authorQuery=queries[0];
                        if( queries[1].length()>0 )
                           titleQuery=queries[1];
                        yearQuery="[0-9]*";
                        break;
                case 3: 
                        if( queries[0].length()>0 )
                           authorQuery=queries[0];
                        if( queries[1].length()>0 )
                           titleQuery=queries[1];
                        if( queries[2].length()>0 )
                           yearQuery=queries[2];
                        break;
                default:
                        authorQuery=".*";
                        titleQuery=".*";
                        yearQuery="[0-9]*";
                        break;        
            }
        }
        
        for(BookId bookId : allBookIds) {
              book = selectBook(bookId);
              if( book.getAuthor().matches(authorQuery) &&
                  book.getTitle().matches(titleQuery) &&
                  book.getYear().matches(yearQuery) ) {
                bookIds.add(bookId);
              }  
              
          }
        return bookIds;
    }
 
    /**
     * @return the bookLibrary
     */
    public BookLibrary getBookLibrary() {
        return bookLibrary;
    }

    /**
     * @param bookLibrary the bookLibrary to set
     */
    public void setBookLibrary(BookLibrary bookLibrary) {
        this.bookLibrary = bookLibrary;
    }
    
    
}
