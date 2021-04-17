/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.model;
import model.modelReservation;
import model.waiter;

/**
 *
 * @author Manizha Nizami
 */
public class dbConnection {
////for database connection my database is called dbrestaurant    
    public static Connection connection() throws ClassNotFoundException{
    Connection con=null;
    PreparedStatement pst=null;
    
    Class.forName("com.mysql.jdbc.Driver");
        try {
            con= (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/dbrestaurant", "root","");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    
 
    }
 //////////
    /////for view menu
  public static ObservableList<model> getRecords() throws SQLException{
     ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ID`, `ProName`, `Price` FROM `menu` WHERE Category='Meal'";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 int id= rs.getInt("ID");
 String FoodName= rs.getString("ProName");
 int Price= rs.getInt("Price");
 m.setId(id);
 m.setFoodName(FoodName);
 m.setPrice(Price);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  return menuList; 
  }
 public static ObservableList<model> getRecord2() throws SQLException{
     ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ID`, `ProName`, `Price` FROM `menu` WHERE Category='Dessert'";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 int id= rs.getInt("ID");
 String FoodName= rs.getString("ProName");
 int Price= rs.getInt("Price");
 m.setId(id);
 m.setFoodName(FoodName);
 m.setPrice(Price);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  return menuList; 
  }
  public static ObservableList<model> getRecord3() throws SQLException{
     ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ID`, `ProName`, `Price` FROM `menu` WHERE Category='juice'";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 int id= rs.getInt("ID");
 String FoodName= rs.getString("ProName");
 int Price= rs.getInt("Price");
 m.setId(id);
 m.setFoodName(FoodName);
 m.setPrice(Price);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  return menuList; 
  }
////////////////////////////////////////////////////
//for reservation   
   public static ObservableList<modelReservation> getRecordReservation() throws SQLException{
     ObservableList<modelReservation> List =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ID`, `Name`, `TableName`, `GuestNumber`, `Time`, `Date` FROM `reservation`";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 modelReservation m= new modelReservation();
 int id= rs.getInt("ID");
  String name= rs.getString("Name");
    int tbNum=rs.getInt("TableName");
    int tbGuestNum=rs.getInt("GuestNumber");
    String time= rs.getString("Time");
    String date= rs.getString("Date");
 
 m.setId(id);
m.setName(name);
m.setTbNum(tbNum);
m.setTbGuestNum(tbGuestNum);
m.setTime(time);
m.setDate(date);
 List.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  return List; 
  }
////////////////////////////////////////////////////////////////////////////////////
////Customers Account Admin
      public static ObservableList<modelReservation> getRecordCustomersAccount() throws SQLException{
     ObservableList<modelReservation> List =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ID`, `Name`, `Email`, `Address`, `Phone`, `Password` FROM `register`";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 modelReservation m= new modelReservation();
 int id= rs.getInt("ID");
  String name= rs.getString("Name");
  
    String Email=rs.getString("Email");
    String Address=rs.getString("Address");
   
    String time= rs.getString("Phone");
    String date= rs.getString("Password");
 
 m.setId(id);
m.setName(name);
m.setEmail(Email);
m.setAddress(Address);
m.setTime(time);
m.setDate(date);
 List.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  return List; 
  }
///////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////Raw Items page Admin
  public static ObservableList<model> getRecordItemsPage() throws SQLException{
     ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ID`, `Items-Name`, `Quantity`, `Total` FROM `items`";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 int id= rs.getInt("ID");
 String FoodName= rs.getString("Items-Name");
 String quantity= rs.getString("Quantity");
 String total= rs.getString("Total");
 m.setId(id);
 m.setFoodName(FoodName);
m.setQuantity(quantity);
m.setTotal(total);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  return menuList; 
  }
//////////////////////////////////////////////Waiter Registeration from Admin//////////////////////////////////////
    public static ObservableList<waiter> getRecordWaiter() throws SQLException {
         ObservableList<waiter> List =FXCollections.observableArrayList();
        try {
           
            
            Connection con= connection();
            String sql= "SELECT `ID`, `Name`, `Birth`, `Phone`, `Password` FROM `waiter-register`";
                PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                ResultSet rs=  pst.executeQuery();
                while(rs.next()){
                waiter m= new waiter();
                int id= rs.getInt("ID");
                String Name= rs.getString("Name");
                
                 String Birth= rs.getString("Birth");
                
                String Phone= rs.getString("Phone");
                String Password= rs.getString("Password");
                
                m.setId(id);
                m.setName(Name);
                m.setBirth(Birth);
             
                m.setPhone(Phone);
                m.setPassword(Password);
                
                List.addAll(m);
                }
            
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
          return List;
  }
    
    
    
    
    //////////////////////////////////////////////Waiter Attendence from Admin//////////////////////////////////////
    public static ObservableList<waiter> getWaiterRecord() throws SQLException {
         ObservableList<waiter> waiterAttendenceList =FXCollections.observableArrayList();
        try {
           
            
            Connection con= connection();
            String sql= "SELECT `ID`, `Name`, `Starting Time`, `Ending Time`, `date`, `Attendance` FROM `waiter-attendance`";
                PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                ResultSet rs=  pst.executeQuery();
                while(rs.next()){
                waiter m= new waiter();
                int id= rs.getInt("ID");
                String name= rs.getString("Name");
                
                 String phone= rs.getString("Starting Time");
                
                String password= rs.getString("Ending Time");
                String birth= rs.getString("date");
                String attendence= rs.getString("Attendance");
                m.setId(id);
                m.setName(name);
                 /*in the setter the variable name is birth so i used the exact same name for the date in the database */ 
              m.setBirth(birth);
               /*in the setter the variable name is phone so i used the exact same name for the Starting Time in the database  */ 
                m.setPhone(phone);
                /*in the setter the variable name is password so i used the exact same name for the Ending time in the database */  
                m.setPassword(password);
                m.setAttendence(attendence);
                
                waiterAttendenceList.addAll(m);
                }
            
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
          return waiterAttendenceList;
  }
    
     //////////////////////////////////////////////Waiter Attendence from Admin//////////////////////////////////////
    public static ObservableList<waiter> getWaiterTasksAdmin() throws SQLException {
         ObservableList<waiter> waiterAttendenceList =FXCollections.observableArrayList();
        try {
           
            
            Connection con= connection();
            String sql= "SELECT * FROM `waiter-tasks`";
                PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                ResultSet rs=  pst.executeQuery();
                while(rs.next()){
                waiter m= new waiter();
                int id= rs.getInt("ID");
                String name= rs.getString("Name");
                
                 String phone= rs.getString("Table-Number");
                
                String password= rs.getString("Status");
              
                m.setId(id);
                m.setName(name);   
                m.setPhone(phone);        
                m.setPassword(password);
              
                
                waiterAttendenceList.addAll(m);
                }
            
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
          return waiterAttendenceList;
  }
    
}
