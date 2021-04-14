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
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.waiter;
import static project.dbConnection.connection;
/**
 * FXML Controller class
 *
 * 
 */
public class AddAttendenceAdminController implements Initializable {
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
    private TableColumn<waiter, String> tbStartTime;

    @FXML
    private TableColumn<waiter, String> tbEndTime;

    @FXML
    private TableColumn<waiter, String> tbDate;

    @FXML
    private TableColumn<waiter, String> tbAttendence;


    @FXML
    private JFXTextField Enterid;
    
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTimePicker starting;

    @FXML
    private JFXTimePicker ending;

    @FXML
    private JFXDatePicker date;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private JFXButton add;
    
       @FXML
    private JFXButton refresh;
       
       
    
    ObservableList<String> AttendenceChoice= FXCollections.observableArrayList("Present","Absent");
    
     @FXML
    void enter(KeyEvent event) throws SQLException {
                if (event.getCode().equals(KeyCode.ENTER)){
                String m= Enterid.getText();
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
    void add(ActionEvent event) throws SQLException {
               try{          
                        con= dbConnection.connection();
                        String sql= "INSERT INTO `waiter-attendance`(`ID`, `Name`, `Starting Time`, `Ending Time`, `date`, `Attendance`) VALUES (?,?,?,?,?,?);";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                          
                          pst.setString(1, Enterid.getText());
                          pst.setString(2, name.getText());
                         pst.setString(3, starting.getValue().toString());
                          pst.setString(4, ending.getValue().toString());
                           pst.setString(5, date.getValue().toString());
                            pst.setString(6, choice.getValue());
                         pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                  
                     Enterid.clear();
                     name.clear();
                     starting.getEditor().clear();
                     ending.getEditor().clear();
                     date.getEditor().clear();
                     
                     
                            
                        }
                        catch(HeadlessException | ClassNotFoundException | SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                        
                        }
    

    }
 @FXML
    void refresh(ActionEvent event) throws SQLException {
        
     try {
         ObservableList<waiter> i = dbConnection.getWaiterRecord();
          TableView.setItems(i); 
      } catch (SQLException ex) {
          Logger.getLogger(AddWaiterAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }

    
  
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      choice.setItems(AttendenceChoice);
      
         try {
         ObservableList<waiter> i = dbConnection.getWaiterRecord();
          TableView.setItems(i); 
      } catch (SQLException ex) {
          Logger.getLogger(AddWaiterAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }

     tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName.setCellValueFactory(new PropertyValueFactory<>("name"));
     tbStartTime.setCellValueFactory(new PropertyValueFactory<>("phone"));
     tbEndTime.setCellValueFactory(new PropertyValueFactory<>("password"));
     tbDate.setCellValueFactory(new PropertyValueFactory<>("birth"));  
     tbAttendence.setCellValueFactory(new PropertyValueFactory<>("attendence"));  
    }    
    
}
