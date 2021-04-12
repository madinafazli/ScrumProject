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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.modelReservation;

/**
 *
 * @author Manizha Nizami
 */
public class CustomersAccount implements Initializable{
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////Customers Account Page Admin
   
    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXTextField tbSearchDate;

    @FXML
    private TableView<modelReservation> tbCustomersAccount;

    @FXML
    private TableColumn<modelReservation, Integer> tbID2;

    @FXML
    private TableColumn<modelReservation, String> tbName2;

    @FXML
    private TableColumn<modelReservation, String> tbEmail;

    @FXML
    private TableColumn<modelReservation, String> tbAddress;

    @FXML
    private TableColumn<modelReservation, String> tbPhone;

    @FXML
    private TableColumn<modelReservation, String> tbPassword;

    @FXML
    private JFXButton btnDelete;
       Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs;
//by clicking this it will close the page and will go back to the main Admin page 
      
  @FXML
    private JFXButton btnShow;
///by clicking this it will show the data inside the Register table inside the table view 
   @FXML
    void btnShow(ActionEvent event) {
    try {
         ObservableList<modelReservation> li = dbConnection.getRecordCustomersAccount();
          tbCustomersAccount.setItems(li); 
      } catch (SQLException ex) {
          Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }  
///this button will take the id and will delete the whole row based on the id which it takes from the user 
    @FXML
    void btnDelete(ActionEvent event) throws SQLException {
 try {
             con= dbConnection.connection();
             String sql= "DELETE FROM `register` WHERE ID=?";
             
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, tbSearchDate.getText());
            pst.executeUpdate();
           tbSearchDate.clear();
           JOptionPane.showMessageDialog(null, "Row Deleted");
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(CustomersAccount.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
//log out from the page and goes the login page 
    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
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
      //these codes is for the table view when the page loads the table of reservation will appear in the table view 
      
     try {
         ObservableList<modelReservation> li = dbConnection.getRecordCustomersAccount();
          tbCustomersAccount.setItems(li); 
      } catch (SQLException ex) {
          Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
      }
         
      tbID2.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName2.setCellValueFactory(new PropertyValueFactory<>("name"));
     tbEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
      tbAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
     tbPhone.setCellValueFactory(new PropertyValueFactory<>("time"));
     tbPassword.setCellValueFactory(new PropertyValueFactory<>("date"));
     
    }
    
}
