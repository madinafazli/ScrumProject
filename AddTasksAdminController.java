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
import java.awt.HeadlessException;
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
 * @author Manizha Nizami
 */
public class AddTasksAdminController implements Initializable {
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
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField addTableNum;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton refresh;
    
        @FXML
    private JFXButton delete;

    @FXML
    private TableView<waiter> TableView1;

    @FXML
    private TableColumn<waiter, Integer> tbID1;

    @FXML
    private TableColumn<waiter, String> tbName1;

    @FXML
    private TableColumn<waiter, String> tbDate;

    @FXML
    private TableColumn<waiter, String> tbAttendence;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXButton search;

    @FXML
    void add(ActionEvent event) {
  try{          
                        con= dbConnection.connection();
                        String sql= "INSERT INTO `waiter-tasks`(`ID`, `Name`, `Table-Number`, `Status`) VALUES (?,?,?,'');";
        
                        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
                          
                          pst.setString(1, id.getText());
                          pst.setString(2, name.getText());
                         pst.setString(3, addTableNum.getText());
                         
                        
                         pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Inserted Successfully");  
                  
                     id.clear();
                     name.clear();
                     addTableNum.clear();
                  
                     
                     
                            
                        }
                        catch(HeadlessException | ClassNotFoundException | SQLException ex){
                        JOptionPane.showMessageDialog(null, ex);
                        
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
    
      @FXML
    void deleteBtm(ActionEvent event) throws ClassNotFoundException, SQLException {
 con= dbConnection.connection();
             String sql= "DELETE FROM `waiter-tasks` WHERE ID=?";
             
             pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, id.getText());
            pst.executeUpdate();
           id.clear();
           JOptionPane.showMessageDialog(null, "Row Deleted");
    }
       @FXML
    void searchBtn(ActionEvent event) throws ClassNotFoundException, SQLException {
         ObservableList<waiter> waiterAttendenceList =FXCollections.observableArrayList();
        String dateSearch= date.getValue().toString();
   con= connection();
  String sql= "SELECT * FROM `waiter-attendance` WHERE date=?";
   pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
   pst.setString(1, dateSearch);
    rs=  pst.executeQuery();
       
               while(rs.next()){
                waiter m= new waiter();
                int id= rs.getInt("ID");
                String name= rs.getString("Name");
                String birth= rs.getString("date");
                String attendence= rs.getString("Attendance");
                m.setId(id);
                m.setName(name);
        
              m.setBirth(birth);
      
                m.setAttendence(attendence);
                
                waiterAttendenceList.addAll(m);
                }
         TableView1.setItems(waiterAttendenceList);
         
     tbID1.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName1.setCellValueFactory(new PropertyValueFactory<>("name"));
  
     tbDate.setCellValueFactory(new PropertyValueFactory<>("birth"));  
     tbAttendence.setCellValueFactory(new PropertyValueFactory<>("attendence"));  

    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
