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
public class BookId {
    private final String id;

    public BookId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
     public String toString() {
        return getId();
    }

}
