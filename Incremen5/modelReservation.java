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
public class modelReservation {
     private int id;
    private String name;
    private int tbNum;
    private int tbGuestNum;
     private String time;
      private String date;
    private String Email;
    private String Address;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public modelReservation() {
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

    public int getTbNum() {
        return tbNum;
    }

    public void setTbNum(int tbNum) {
        this.tbNum = tbNum;
    }

    public int getTbGuestNum() {
        return tbGuestNum;
    }

    public void setTbGuestNum(int tbGuestNum) {
        this.tbGuestNum = tbGuestNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public modelReservation(int id, String name, int tbNum, int tbGuestNum, String time, String date,String Email, String Address) {
        this.id = id;
        this.name = name;
        this.tbNum = tbNum;
        this.tbGuestNum = tbGuestNum;
        this.time = time;
        this.date = date;
        this.Email = Email;
        this.Address = Address;
    }

  
}
