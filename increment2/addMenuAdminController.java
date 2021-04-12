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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class addMenuAdminController implements Initializable {
    
     Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs;

///////////////////////////////////////////////////////////////////////////
//// Add Menu Page 
    ObservableList<String> FoodItems= FXCollections.observableArrayList("Meal","Desserts","Drinks");

     @FXML
    private JFXButton btnLogOut;

 
    
    @FXML
    private ComboBox<String> ChoiceBox;

     @FXML
    private TableView<model> tableViewAdd;
    
    @FXML
    private TableColumn<model, Integer> tableID;

    @FXML
    private TableColumn<model, String> tableName;

    @FXML
    private TableColumn<model, Integer> tablePrice;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private Button btnAdd;

    @FXML
    private JFXTextField txtDelete;

    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnUpdate;
       @FXML
    private JFXTextField txtCatagory;
///   by clicking the option of combobox if the choice is meal then it will shows those rows with the meal catagory and so on 
    @FXML
    void ChoiceBox(ActionEvent event) {
        
        if(ChoiceBox.getValue()== "Meal"){
             try {
             ObservableList<model> li= dbConnection.getRecords();
             tableViewAdd.setItems(li);
         } catch (SQLException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        else if(ChoiceBox.getValue()== "Desserts"){
        
         try {
  
             
             ObservableList<model> li= dbConnection.getRecord2();
             
             tableViewAdd.setItems(li);
         } catch (SQLException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        }   
        else{
        
         try {
  
             
             ObservableList<model> li= dbConnection.getRecord3();
             
             tableViewAdd.setItems(li);
         } catch (SQLException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
        }
        
    }

    ///adds to the menu by enter the name, price and catagory of the item 

    @FXML
    void btnAdd(ActionEvent event) {
         try{          
                        con= dbConnection.connection();
                        String sql= "INSERT INTO `menu`(`ProName`, `Category`, `Price`) VALUES (?,?,?);";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                       
                            
                          pst.setString(1, txtName.getText());
                          pst.setString(2, txtCatagory.getText());
                         pst.setString(3, txtPrice.getText());
                         
                         pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                         txtName.clear();
                         txtPrice.clear();
                         txtCatagory.clear();
                     
                            
                        }
                        catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                        
                        }
    }
//update the price of the menu item by specifying the name of the item 
    @FXML
    void btnUpdate(ActionEvent event) throws SQLException {
         try {
             String NameFood= txtName.getText();
             String PriceFood= txtPrice.getText();
             con= dbConnection.connection();
             String check = "UPDATE `menu` SET `Price`='"+PriceFood+"' WHERE ProName='"+NameFood+"';";
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
             
           pst.executeUpdate();
           txtName.clear();
           txtPrice.clear();
          JOptionPane.showMessageDialog(null, "Column Updated");
          
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
             
    }
///gets the id and delete the only row which has that id     
    @FXML
    void btnDelete(ActionEvent event) throws SQLException {
         try {
             con= dbConnection.connection();
             String sql= "DELETE FROM `menu` WHERE ID=?";
             
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, txtDelete.getText());
            pst.executeUpdate();
           txtDelete.clear();
           JOptionPane.showMessageDialog(null, "Row Deleted");
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
//logout of the current page and go to the login page 
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
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /////////////for menu admin
     ChoiceBox.setItems(FoodItems);
     
     tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
     tableName.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
     tablePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
     
          
     
     
     
    }
    
}
