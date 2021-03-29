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
import model.model;

/**
 * FXML Controller class
 *
 * 
 */
public class ItemsController implements Initializable {
//Raw material page 
 

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXTextField tbSearchID;

    @FXML
    private TableView<model> tbItemsTable;

    @FXML
    private TableColumn<model, Integer> tbID3;

    @FXML
    private TableColumn<model, String> tbName3;

    @FXML
    private TableColumn<model, String> tbQuantity;

    @FXML
    private TableColumn<model, String> tbTotal;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnShow;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextField tbSearchName;

    @FXML
    private JFXTextField tbSearchQuantity;

    @FXML
    private JFXTextField tbSearchTotal;
        Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs;
///when clicked the raw item will be add with name quantity and total to the items table in dbrestaurant database
    @FXML
    void btnAdd(ActionEvent event) {
  try{          
                        con= dbConnection.connection();
                        String sql= "INSERT INTO `items`(`Items-Name`, `Quantity`, `Total`) VALUES (?,?,?);";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                       
                            
                          pst.setString(1, tbSearchName.getText());
                          pst.setString(2, tbSearchQuantity.getText());
                         pst.setString(3, tbSearchTotal.getText());
                         
                         pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                         tbSearchName.clear();
                         tbSearchQuantity.clear();
                         tbSearchTotal.clear();
                     
                            
                        }
                        catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                        
                        }
    }

//when clicked it will get the id from admin and will delete the one that specific row with this id 
    @FXML
    void btnDelete(ActionEvent event) throws SQLException {
try {
             con= dbConnection.connection();
             String sql= "DELETE FROM `items` WHERE ID=?";
             
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, tbSearchID.getText());
            pst.executeUpdate();
           tbSearchID.clear();
           JOptionPane.showMessageDialog(null, "Row Deleted");
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
///when clicked the current page will get close and will go the the login page 
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
///showes the columns from the items table in table view of fxml file 
    @FXML
    void btnShow(ActionEvent event) {
 try {
         ObservableList<model> li = dbConnection.getRecordItemsPage();
          tbItemsTable.setItems(li); 
      } catch (SQLException ex) {
          Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
///update the quantity and total of the id which has been inserted by the admin
    @FXML
    void btnUpdate(ActionEvent event) throws SQLException {
 try {
            
             con= dbConnection.connection();
             String check = "UPDATE `items` SET `Quantity`=?, `Total`=? WHERE ID=?";
              pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
             pst.setString(1, tbSearchQuantity.getText());
             pst.setString(2, tbSearchTotal.getText());
             pst.setString(3, tbSearchID.getText());
             
            
             
           pst.executeUpdate();
           tbSearchID.clear();
           tbSearchQuantity.clear();
         tbSearchTotal.clear();
          JOptionPane.showMessageDialog(null, "Column Updated");
          
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
         ObservableList<model> li = dbConnection.getRecordItemsPage();
          tbItemsTable.setItems(li); 
      } catch (SQLException ex) {
          Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
      }
         tbID3.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName3.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
     tbQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
      tbTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        
    }    
    
}
