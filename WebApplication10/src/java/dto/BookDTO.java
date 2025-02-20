/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Dell
 */
public class BookDTO {
    private String BookID;
    private String Title;
    private String Author;
    private int PublishYear;
    private double  Price;
    private int Quantity;

    public BookDTO() {
    }

    public BookDTO(String BookID, String Title, String Author, int PublishYear, double Price, int Quantity) {
        this.BookID = BookID;
        this.Title = Title;
        this.Author = Author;
        this.PublishYear = PublishYear;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setPublishYear(int PublishYear) {
        this.PublishYear = PublishYear;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public int getPublishYear() {
        return PublishYear;
    }

    public double getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    @Override
    public String toString() {
        return "BookDTO{" + "BookID=" + BookID + ", Title=" + Title + ", Author=" + Author + ", PublishYear=" + PublishYear + ", Price=" + Price + ", Quantity=" + Quantity + '}';
    }
    
    
}
