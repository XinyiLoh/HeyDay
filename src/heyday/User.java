/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

/**
 *
 * @author Xin Yi
 */
public class User {
    protected String username;
    protected String password;
    protected char gender;
    protected String phoneNo;

    protected User(String username, String password, char gender, String phoneNo) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String toString(){
        return "Username : " + username + "\n" +
                "Gender : " + gender + "\n" +
                "Phone No. : " + phoneNo + "\n";
    }
}

