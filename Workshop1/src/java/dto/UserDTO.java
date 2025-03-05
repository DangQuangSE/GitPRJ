
package dto;


public class UserDTO {
    private String userName;
    private String name;
    private String password;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String userName, String name, String passWord, String role) {
        this.userName = userName;
        this.name = name;
        this.password = passWord;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userName=" + userName + ", name=" + name + ", passWord=" + password + ", role=" + role + '}';
    }
    
}
