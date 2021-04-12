/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import static project.dbConnection.connection;

/**
 *
 * @author Manizha Nizami
 */
public class userAccountInfoController implements Initializable{

    /////////////////////////user account infor.fxml java file below

   
 
 @FXML
    private AnchorPane account;

    @FXML
    private Button btnDashboardUser;

    @FXML
    private Button btnUserReservation;

    @FXML
    private Button btnSeeMenu;

    @FXML
    private Button btnSeeAccountInfo;

    @FXML
    private Tooltip userSeeMenu1;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXTextField newPassword;

    @FXML
    private JFXTextField confirmPassword;

    @FXML
    private JFXButton save;

    @FXML
    private JFXTextField password;

    @FXML
    void btnDashboardUser2(ActionEvent event) throws IOException {
Parent root;
   root= FXMLLoader.load(getClass().getResource("userPage.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
              s.setTitle("                           Log In");
               s.setScene(scene);
                s.show();
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }

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

    @FXML
    void btnSeeAccountInfo(ActionEvent event) {

    }

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
  Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs;


    @FXML
    void saveBtn(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

    try {
             String newPasswordvalue= confirmPassword.getText();
             String oldPassword= password.getText();
            
             con= dbConnection.connection();
             String check = "UPDATE `register` SET `Password`='"+newPasswordvalue+"' WHERE Password='"+oldPassword+"'";
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
             pst.executeUpdate();
           
          JOptionPane.showMessageDialog(null, "Column Updated");
          password.clear();
          newPassword.clear();
          confirmPassword.clear();
          
          Parent root;
   root= FXMLLoader.load(getClass().getResource("login.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setTitle("               User Reservation Page");
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
          
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   

   
    }
    
}
