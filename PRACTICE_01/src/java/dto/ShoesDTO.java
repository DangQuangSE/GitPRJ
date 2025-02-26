/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class ShoesDTO {

    private String ShoesID;
    private String ShoesName;
    private String Trademark;
    private int PublishYear;
    private int Quantity;

    public ShoesDTO(String ShoesID, String ShoesName, String Trademark, int PublishYear, int Quantity) {
        this.ShoesID = ShoesID;
        this.ShoesName = ShoesName;
        this.Trademark = Trademark;
        this.PublishYear = PublishYear;
        this.Quantity = Quantity;
    }

    public ShoesDTO() {
    }

    public String getShoesID() {
        return ShoesID;
    }

    public void setShoesID(String ShoesID) {
        this.ShoesID = ShoesID;
    }

    public String getShoesName() {
        return ShoesName;
    }

    public void setShoesName(String ShoesName) {
        this.ShoesName = ShoesName;
    }

    public String getTrademark() {
        return Trademark;
    }

    public void setTrademark(String Trademark) {
        this.Trademark = Trademark;
    }

    public int getPublishYear() {
        return PublishYear;
    }

    public void setPublishYear(int PublishYear) {
        this.PublishYear = PublishYear;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "ShoesDTO{" + "ShoesID=" + ShoesID + ", ShoesName=" + ShoesName + ", Trademark=" + Trademark + ", PublishYear=" + PublishYear + ", Quantity=" + Quantity + '}';
    }

}
