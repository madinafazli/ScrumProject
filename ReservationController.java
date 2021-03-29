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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.modelReservation;
import static project.dbConnection.connection;

/**
 * FXML Controller class
 *
 * @author Manizha Nizami
 */
public class ReservationController implements Initializable {
///Admin reservation controll page checks who has reserved the table at what time and date    
    
 @FXML
    private ImageView btnDashboard1;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXTextField tbSearchDate;

    @FXML
    private TableView<modelReservation> tbReservation;

    @FXML
    private TableColumn<modelReservation, Integer> tbID;

    @FXML
    private TableColumn<modelReservation, String> tbName;

    @FXML
    private TableColumn<modelReservation, Integer> tbNumber;

    @FXML
    private TableColumn<modelReservation, Integer> tbGuest;

    @FXML
    private TableColumn<modelReservation, String> tbTime;

    @FXML
    private TableColumn<modelReservation, String> tbDate;

    @FXML
    private JFXButton btnSearch;
////closes the current pages, goes to the login page
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
      Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs;
//search the date by entering the date 
    @FXML
    void btnSearch(ActionEvent event) throws SQLException {
     ObservableList<modelReservation> List =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT * FROM `reservation` WHERE Date=?";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
             pst.setString(1, tbSearchDate.getText());
             
            
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 modelReservation m= new modelReservation();
 int id= rs.getInt("ID");
  String name= rs.getString("Name");
    int tbNum=rs.getInt("TableName");
    int tbGuestNum=rs.getInt("GuestNumber");
    String time= rs.getString("Time");
    String date= rs.getString("Date");
 
 m.setId(id);
m.setName(name);
m.setTbNum(tbNum);
m.setTbGuestNum(tbGuestNum);
m.setTime(time);
m.setDate(date);
 List.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  tbReservation.setItems(List);      
    }
    


  
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
          ObservableList<modelReservation> li= dbConnection.getRecordReservation();
          tbReservation.setItems(li);
      } catch (SQLException ex) {
          Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
      }
      tbID.setCellValueFactory(new PropertyValueFactory<>("id"));
     tbName.setCellValueFactory(new PropertyValueFactory<>("name"));
     tbNumber.setCellValueFactory(new PropertyValueFactory<>("tbNum"));
      tbGuest.setCellValueFactory(new PropertyValueFactory<>("tbGuestNum"));
     tbTime.setCellValueFactory(new PropertyValueFactory<>("time"));
     tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
     
     
     
    }  
    
}
