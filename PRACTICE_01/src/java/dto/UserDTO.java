/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class UserDTO {

    private String UserID;
    private String Name;
    private String UserName;
    private String Role;
    private String Password;

    public UserDTO() {
    }

    public UserDTO(String UserID, String Name, String UserName, String Role, String Password) {
        this.UserID = UserID;
        this.Name = Name;
        this.UserName = UserName;
        this.Role = Role;
        this.Password = Password;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "UserID=" + UserID + ", Name=" + Name + ", UserName=" + UserName + ", Role=" + Role + ", Password=" + Password + '}';
    }

}
