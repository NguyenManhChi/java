/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author leduc
 */
public class modelAccount {
    private int userID;
    private String username;
    private String password;

    // Constructors
    public modelAccount() {}
    
    public modelAccount( String username, String password) {
        
        this.username = username;
        this.password = password;
    }
    public modelAccount(int id, String username, String password) {
        this.userID = id;
        this.username = username;
        this.password = password;
    }
    // Getters và Setters
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
