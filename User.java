/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author tbash
 */
public class User {
    protected String userId;
    protected String name;
    protected String password;
    protected String email;
    protected String phone;
    protected String role;

    public User(String userId, String name, String password, String email, String phone, String role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
    
    public User(){
        
    }
}
