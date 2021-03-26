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

    }
///by clicking this it will go the User reservation page where user will reserve one table at a specific time and Date
    @FXML
    void btnUserReservation(ActionEvent event) throws IOException {

    }
///by clicking this the user will see all her/his account information
   @FXML
    void btnSeeAccountInfo(ActionEvent event) {

    }

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
    


                     
                            
                     


  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //when the user hover overs the dashboard, menu, reservation icons it will show what this specific button does
         btnDashboardUser.setTooltip(userDashboard);
    btnUserReservation.setTooltip(userCheckReservation);

    btnSeeMenu.setTooltip(userSeeMenu);

  
  
    }
    
}
