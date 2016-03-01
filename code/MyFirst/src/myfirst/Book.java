/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirst;

/**
 *
 * @author Shaunak
 */
public class Book {

    private String name;
    private String author;
    private String category,available,issued_by,issued_date,return_date;

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setIssued_date(String issued_date) {
        this.issued_date = issued_date;
    }

    public String getIssued_date() {
        return issued_date;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getIssued_by() {
        return issued_by;
    }

    
    
    
    
    public Book()
    {
        this.name="";
        this.author="";
        this.category="";
        this.available="";
        this.issued_by="";
        
    }
    public Book(String name,String author,String category,String available,String issued_by,String issued_date)
    {
        this.name=name;
        this.author=author;
        this.category=category;
        this.available=available;
        this.issued_by=issued_by;
        this.issued_date=issued_date;
        
    }
    public Book(String name,String author,String category,String available)
    {
        this.name=name;
        this.author=author;
        this.category=category;
        this.available=available;
        
        
    }

    //getters and setters
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getAvailable() {
        return available;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
    
}
