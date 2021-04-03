/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Manizha Nizami
 */
public class userController implements Initializable {
////for DataBase Connection 
   Connection con=null;
    PreparedStatement pst, pst2=null;
    ResultSet rs;
    

///////////////////////////////////////////////////////////////////////////////////////
    //////////////User Main Page

   
 
    @FXML
    private Button btnDashboardUser;

    @FXML
    private Tooltip userDashboard;

    @FXML
    private Button btnUserReservation;

    @FXML
    private Tooltip userCheckReservation;

    @FXML
    private Button btnSeeMenu;
     @FXML
    private Button btnSeeAccountInfo;

    @FXML
    private Tooltip userSeeMenu;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton seeMenu;

    @FXML
    private JFXButton seeReservation;

  ////by clickin this button it will sign out and will go back to the log in page 

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
Parent root;
   root= FXMLLoader.load(getClass().getResource("login.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
              s.setTitle("                           Log In");
               s.setScene(scene);
                s.show();
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }
///by clicking this it will go the the menu page to see the menu of the restaurant
    @FXML
    void btnSeeMenu(ActionEvent event) throws IOException {
Parent root;
   root= FXMLLoader.load(getClass().getResource("menu.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
              s.setTitle("                   Restaurant Menu");
               s.setScene(scene);
                s.show();
    }
///by clicking this it will go the User reservation page where user will reserve one table at a specific time and Date
    @FXML
    void btnUserReservation(ActionEvent event) throws IOException {
Parent root;
   root= FXMLLoader.load(getClass().getResource("userReservationPage.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setTitle("               User Reservation Page");
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
    }
///by clicking this the user will see all her/his account information
   @FXML
    void btnSeeAccountInfo(ActionEvent event) throws IOException {

    Parent root;
   root= FXMLLoader.load(getClass().getResource("userAccountInfo.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setTitle("               User Account information Page");
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
    }
   /////////////////////////////////////////////////////////////////////////
    //////////User Reservation Page
 

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTableNum;

    @FXML
    private JFXTextField txtGuestNum;

    @FXML
    private JFXButton btnSend;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTimePicker txtTime;
////by clicking this it close the page and go back to the main user page 
     @FXML
    void btnDashboardUser2(ActionEvent event) throws IOException{
 Parent root;
   root= FXMLLoader.load(getClass().getResource("userPage.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setTitle("               User Reservation Page");
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
    }
    

///when the user clicks the btnSend button all information will be saved in the reservation table inside the dbrestuarant Database
  
    @FXML
    void btnSend(ActionEvent event) throws SQLException {
         try {
            ///Also before inserting the data  it will check if the time table number and date exist in the database 
            ////if it exist then it will ask to choose another one 
             con= dbConnection.connection();
              String check = "SELECT * FROM `reservation` WHERE TableName=? AND Time =? AND Date=?";
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
              pst.setString(1, txtTableNum.getText());
              pst.setString(2, txtTime.getValue().toString());
               pst.setString(3, txtDate.getValue().toString());
             rs= pst.executeQuery();
             
             if(rs.next()){
             JOptionPane.showMessageDialog(null, "The Table has been reserved by another Customer At that \n '"+txtTime.getValue().toString()+"' time and  '"+txtDate.getValue().toString()+"'\n Date Please choose another one, Or change the Date and Time");
             }
             
             /////if the data doesnt exist in the table already then insert the data into the columns
             else{
             
                String sql= "INSERT INTO `reservation`(`Name`, `TableName`, `GuestNumber`,`Time`, `Date`) VALUES (?,?,?,?,?)";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                       
                          pst.setString(1, txtName.getText());
                          pst.setString(2, txtTableNum.getText());
                         pst.setString(3, txtGuestNum.getText());
                         pst.setString(4, txtTime.getValue().toString());
                          
                           pst.setString(5, txtDate.getValue().toString());
                         
                           pst.executeUpdate();
                          
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                         txtName.clear();
                         txtTableNum.clear();
                         txtGuestNum.clear();
            
             }
     
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
         }
                     
                     
                            
                     
                        
    }

  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //when the user hover overs the dashboard, menu, reservation icons it will show what this specific button does
         btnDashboardUser.setTooltip(userDashboard);
    btnUserReservation.setTooltip(userCheckReservation);

    btnSeeMenu.setTooltip(userSeeMenu);

  
  
    }
    
}
