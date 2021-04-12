/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.waiter;


/**
 * FXML Controller class
 *
 * @author Manizha Nizami
 */
public class AddWaiterAdminController implements Initializable {
    
       Connection con=null;
    PreparedStatement pst, pst2=null;
    ResultSet rs;
 @FXML
    private JFXButton btnLogOut;

    @FXML
    private TableView<waiter> TableView;

    @FXML
    private TableColumn<waiter, Integer> tbID;

    @FXML
    private TableColumn<waiter, String> tbName;

    @FXML
    private TableColumn<waiter, String> tbBirth;

    @FXML
    private TableColumn<waiter, String> tbLocation;

    @FXML
    private TableColumn<waiter, String> tbPhone;

    @FXML
    private TableColumn<waiter, String> tbPass;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField birth;

    @FXML
    private JFXTextField num;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton refresh;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton delete;

    @FXML
    void add(ActionEvent event) throws ClassNotFoundException, SQLException {
       con= dbConnection.connection();
        String sql= "INSERT INTO `waiter-register`(`ID`, `Name`, `Birth`, `Phone`, `Password`) VALUES (?,?,?,?,?);";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                       
                            
                          pst.setString(1, id.getText());
                          pst.setString(2, name.getText());
                         pst.setString(3, birth.getText());
                         pst.setString(4, num.getText());
                         pst.setString(5, password.getText());
                         
                         pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                         id.clear();
                         name.clear();
                         birth.clear();
                          num.clear();
                          password.clear();
                    
    }

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

    @FXML
    void delete(ActionEvent event) throws ClassNotFoundException, SQLException {
            con= dbConnection.connection();
             String sql= "DELETE FROM `waiter-register` WHERE ID=?";
             
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, id.getText());
            pst.executeUpdate();
           id.clear();
           JOptionPane.showMessageDialog(null, "Row Deleted");
    }

    @FXML
    void refresh(ActionEvent event) throws SQLException {
        
     try {
         ObservableList<waiter> i = dbConnection.getRecordWaiter();
          TableView.setItems(i); 
      } catch (SQLException ex) {
          Logger.getLogger(AddWaiterAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }
          
    
          
 }
              
              
           
    

    @FXML
    void update(ActionEvent event) throws ClassNotFoundException, SQLException {
               String phoneNum= num.getText();
             String ID= id.getText();
             con= dbConnection.connection();
             String check = "UPDATE `waiter-register` SET `Phone`='"+phoneNum+"' WHERE ID='"+ID+"'";
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
             
           pst.executeUpdate();
           id.clear();
           num.clear();
          JOptionPane.showMessageDialog(null, "Column Updated");
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

           try {
         ObservableList<waiter> i = dbConnection.getRecordWaiter();
          TableView.setItems(i); 
      } catch (SQLException ex) {
          Logger.getLogger(AddWaiterAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }

     tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName.setCellValueFactory(new PropertyValueFactory<>("Name"));
     tbBirth.setCellValueFactory(new PropertyValueFactory<>("Birth"));
     tbPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
     tbPass.setCellValueFactory(new PropertyValueFactory<>("Password"));  
    }    
    
}
