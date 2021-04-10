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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.waiter;
import static project.dbConnection.connection;

/**
 * FXML Controller class
 *
 * @author Manizha Nizami
 */
public class SeeAttendenceWaiterController implements Initializable {
 @FXML
    private JFXButton btnLogOut;

    @FXML
    private ImageView btnDashboard1;

    @FXML
    private JFXTextField tbSearchID;

    @FXML
    private JFXButton btnSearch;

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
    void btnLogOut(ActionEvent event)throws IOException {
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
    void btnSearch(ActionEvent event) throws ClassNotFoundException, SQLException {
         ObservableList<waiter> waiterAttendenceList =FXCollections.observableArrayList();
        String IDWaiter= tbSearchID.getText();
  Connection con= connection();
  String sql= "SELECT * FROM `waiter-attendance` WHERE ID=?";
  PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
   pst.setString(1, IDWaiter);
       ResultSet rs=  pst.executeQuery();
       
               while(rs.next()){
                waiter m= new waiter();
                int id= rs.getInt("ID");
                String name= rs.getString("Name");
                
                 String phone= rs.getString("Starting Time");
                
                String password= rs.getString("Ending Time");
                String birth= rs.getString("date");
                String attendence= rs.getString("Attendance");
                m.setId(id);
                m.setName(name);
        
              m.setBirth(birth);
      
                m.setPhone(phone);
              
                m.setPassword(password);
                m.setAttendence(attendence);
                
                waiterAttendenceList.addAll(m);
                }
         TableView.setItems(waiterAttendenceList);
         
     tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName.setCellValueFactory(new PropertyValueFactory<>("name"));
     tbStartTime.setCellValueFactory(new PropertyValueFactory<>("phone"));
     tbEndTime.setCellValueFactory(new PropertyValueFactory<>("password"));
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

    }    
    
}
