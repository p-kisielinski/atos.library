/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary.entity;

/**
 *
 * @author piotr
 */
public class Book {
    
    private String  title;
    private String  author;
    private boolean lent;
    private String  lentFor;
    private String  year;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * @return the lent
     */
    public boolean isLent() {
        return lent;
    }

    /**
     * @param lent the lent to set
     * @return 
     */
    public Book setLent(boolean lent) {
        this.lent = lent;
        
        return this;
    }

    /**
     * @return the lentFor
     */
    public String getLentFor() {
        return lentFor;
    }

    /**
     * @param lentFor the lentFor to set
     */
    public Book setLentFor(String lentFor) {
        this.lentFor = lentFor;
        
        return this;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public Book setYear(String year) {
        this.year = year;
        
        return this;
    }
    
    public String toString() {
        return getTitle() + ":" + getAuthor();
    }
}
