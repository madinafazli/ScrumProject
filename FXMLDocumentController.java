/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class FXMLDocumentController implements Initializable {

////for DataBase Connection 
   Connection con=null;
    PreparedStatement pst, pst2=null;
    ResultSet rs;
    
    
    

    
     ///////////////////////////////////////////////////////////////////////////
////////////Admin main Page 
@FXML
    private Button btnDashboard;

    @FXML
    private Tooltip toolDash;

    @FXML
    private Button btnMenu;

    @FXML
    private Tooltip toolCheckMenu;

    @FXML
    private Button btnCustomer;

    @FXML
    private Tooltip toolCheckCustomer;

    @FXML
    private Button btnReservation;

    @FXML
    private Tooltip toolReservationCheck;

    @FXML
    private Button btnSeeMenu;

    @FXML
    private Tooltip toolMenu;

    @FXML
    private Button btnStock;

    @FXML
    private Tooltip toolItems;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private Tooltip toolLogout;
    
    @FXML
    private JFXButton imgSeeMenu;

    @FXML
    private JFXButton imgSeeCustomers;

    @FXML
    private JFXButton imgSeeReservation;

///////when clicked the Customer controller will get open for the admin to delete and search User Account
    @FXML
    void btnCustomer(ActionEvent event) throws IOException {
  
    }

    @FXML
    void btnDashboard(ActionEvent event) {

    }
/////////////////////////////////////////when clicked the main page will get closed and the admin and user will go to the
    //login page again
    
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
/////////when clicked the admin will go the Menu controller the Main page will get closed and the Menu controller page will appear 
    @FXML
    void btnMenu(ActionEvent event) throws IOException {
         

    }
////////when clicked the Admin will go the the reservation page where she checks the reservation table of customers 
    @FXML
    void btnReservation(ActionEvent event) throws IOException {
  
 
    }
////////////when clicked the Admin will go see which food dessert and drinks are in the menu. 
    @FXML
    void btnSeeMenu(ActionEvent event) throws IOException {
 
    }
//////when clicked the Admin will go the Raw Material page where she adds/Deletes/Update the data inside the table
    @FXML
    void btnStock(ActionEvent event) throws IOException {

    }




        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 /////these are the tool tip when the admin hovers over the button a tooltip apears saying what this button does       
      btnDashboard.setTooltip(toolDash);
    btnMenu.setTooltip(toolCheckMenu);

  btnCustomer.setTooltip(toolCheckCustomer);

   btnReservation.setTooltip(toolReservationCheck);
  
    btnSeeMenu.setTooltip(toolMenu);

    btnStock.setTooltip(toolItems);

  btnLogOut.setTooltip(toolLogout);

    
      
    }    
    
}
