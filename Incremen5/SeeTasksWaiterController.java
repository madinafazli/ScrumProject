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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.waiter;
import static project.dbConnection.connection;

/**
 * FXML Controller class
 *
 * 
 */
public class SeeTasksWaiterController implements Initializable {
    Connection con=null;
    PreparedStatement pst=null;
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
    private TableColumn<waiter, String> tbTableNum;

    @FXML
    private TableColumn<waiter, String> tbStatus;
    
    @FXML
    private ChoiceBox<String> choice;


    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton refresh;
    
 ObservableList<String> tasksChoice= FXCollections.observableArrayList("Noticed","Work Done");
    @FXML
    void add(ActionEvent event) throws SQLException {
                 try {
             String status= choice.getValue();
             String nameWaiter= name.getText();
             con= dbConnection.connection();
             String check = "UPDATE `waiter-tasks` SET  `Status`='"+status+"' WHERE Name='"+nameWaiter+"';";
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(check);
             
           pst.executeUpdate();
                    id.clear();
                     name.clear();

          JOptionPane.showMessageDialog(null, "Column Updated");
          
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(addMenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }

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
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); }

    @FXML
    void enter(KeyEvent event) throws SQLException {
                 if (event.getCode().equals(KeyCode.ENTER)){
                String m= id.getText();
                try {

                 con= connection();
                String sql= "SELECT `Name` FROM `waiter-register` WHERE ID=?";
                pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, m);
                rs=  pst.executeQuery();
                while(rs.next()){
               
                String Name= rs.getString("Name");
                 name.setText(Name);
                }
            
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
                
               
                }

    }
      

    @FXML
    void refresh(ActionEvent event) throws SQLException {
        
     try {
         ObservableList<waiter> i = dbConnection.getWaiterTasksAdmin();
          TableView.setItems(i); 
      } catch (SQLException ex) {
          Logger.getLogger(AddWaiterAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        choice.setItems(tasksChoice);
        
           try {
         ObservableList<waiter> i = dbConnection.getWaiterTasksAdmin();
          TableView.setItems(i); 
      } catch (SQLException ex) {
          Logger.getLogger(AddWaiterAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }

     tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    
     tbTableNum.setCellValueFactory(new PropertyValueFactory<>("Phone"));
     tbStatus.setCellValueFactory(new PropertyValueFactory<>("Password")); 
    }    
    
}
