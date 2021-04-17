/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Manizha Nizami
 */
public class waiter {
    
///for register Waiter Admin controller
public waiter(){

}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

  

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
      private int id;
   private String name;
    private String birth;
      private String attendence;
    private String phone;
    private String password;
    
    
    

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }
  

    public waiter(int id, String name, String birth, String phone, String password) {
        this.id = id;
        this.name = name;
        this.birth = birth;
       
        this.phone = phone;
        this.password = password;
    }
   
}
