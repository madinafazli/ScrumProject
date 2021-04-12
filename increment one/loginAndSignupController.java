/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Maihan Naimi
 */
public class loginAndSignupController implements Initializable {
    
  ////////////////////////////////////////////////////////////////
    
    ////////////////////////Log in Account Page
    @FXML
    private JFXTextField logName;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private JFXPasswordField logPassword;

    @FXML
    private ImageView logImg;
    Connection con=null;
    PreparedStatement pst, pst2=null;
    ResultSet rs;
    
 ////this button will open a signup/Register page for the user to create an account so they could use the application 
    @FXML
    void btnCreateAccount(ActionEvent event) throws IOException {
 Parent root;
            
             root= FXMLLoader.load(getClass().getResource("signup.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();   
    }
/*this button will get the text from the filed check them if the typed text is 
   Admin then will open the Admin page if not it will check with the table is exists then it will open the user page 
    if not then it will give you a warning that it doesnt exist and try again 
    */
    
    
    @FXML
    void btnSignIn(ActionEvent event) throws SQLException, IOException {
 try {              
             con= dbConnection.connection();
             String check = "SELECT * FROM `register` WHERE Name=? AND Password =?";
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
              pst.setString(1, logName.getText());
              pst.setString(2, logPassword.getText());
             rs= pst.executeQuery();
  ///checks if the typed text matches with the condition below            
             if (logName.getText().equals("Admin") && logPassword.getText().equals("Admin")){
          Parent root;
            
             root= FXMLLoader.load(getClass().getResource("MainAdmin.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
              s.setTitle("                             Admin Main Page");
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
         
         
         
         
         }
    //check if typed text exist in the database register table 
             else if(rs.next()){
              logName.clear();
              logPassword.clear();
             JOptionPane.showMessageDialog(null, "login Successfully");
             
              Parent root;
            
             root= FXMLLoader.load(getClass().getResource("userPage.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
              s.setTitle("                             User Main Page");
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
             }
             else{
             
             JOptionPane.showMessageDialog(null, "Login failed, Try again");  
              logName.clear();
              logPassword.clear();
             
             
             }
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////Sign Up Page User
 @FXML
    private ImageView signupImg;

    @FXML
    private JFXTextField textName;

    @FXML
    private JFXTextField textEmail;

    @FXML
    private JFXTextField textAddress;

    @FXML
    private JFXTextField textPhone;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnReturn;

    @FXML
    private Label alerttxt;

    @FXML
    private JFXPasswordField textPassword;

    @FXML
   ////when User clicked this button it will check if any filed is not empty and then it will insert the data to the table reservation         
    void btnRegisterClicked(ActionEvent event) {
 try{
                        con= dbConnection.connection();
                        String sql= "INSERT INTO `register`(`Name`, `Email`, `Address`, `Phone`, `Password`) VALUES (?,?,?,?,?)";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                        if(textName.getText().trim().isEmpty()){
                             alerttxt.setText("Name Field is Empty");
                        }
                        else if (textEmail.getText().trim().isEmpty()){
                        
                        alerttxt.setText("Email Field is Empty");
                        }
                        else if (textAddress.getText().trim().isEmpty()){
                        
                        alerttxt.setText("Address Field is Empty");
                        }
                       else if (textPhone.getText().trim().isEmpty()){
                        
                        alerttxt.setText("Phone Field is Empty");
                        }
                        else if (textPassword.getText().trim().isEmpty()){
                        
                        alerttxt.setText("Password Field is Empty");
                        }
                    
                        
                     else {
                          pst.setString(1, textName.getText());
                         pst.setString(2, textEmail.getText());
                         pst.setString(3, textAddress.getText());
                          pst.setString(4, textPhone.getText());
                           pst.setString(5, textPassword.getText());
                          pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                            
          ///this will clear up the fileds when the data gets inserted to the table                  
                    textName.clear();
                    textEmail.clear();
                    textAddress.clear();
                    textPhone.clear();
                    textPassword.clear();
                    alerttxt.setText("");   
                        }
                       
                            
                        }
                        catch(HeadlessException | ClassNotFoundException | SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                        
                        }
    }
////this button will close the signup/Register page and will go to the login page
    @FXML
    void btnReturnClicked(ActionEvent event) throws IOException {
  Parent root;
            
             root= FXMLLoader.load(getClass().getResource("login.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setScene(scene);
                s.show();
 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();   
    }    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
        
            }
    
    
}
