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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import model.model;
import static project.dbConnection.connection;

/**
 *
 * @author Manizha Nizami
 */
public class menuController implements Initializable{
    
    //////////////////////////////////////////////////////////////////
    //////////Menu Page


    @FXML
    private JFXButton btnLogOut;
    @FXML
    private ImageView imgFood;

    @FXML
    private ImageView imgDessert;

    @FXML
    private ImageView imgDrinks;

    @FXML
    private TableView<model> tbMenu;

    @FXML
    private TableColumn<model, String> foodColunm;

    @FXML
    private TableColumn<model, Integer> priceColumn;
  
///log out of the current page and go to the log in page 
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
 
//by clicking the image it will show only the dessert rows 
    @FXML
    private void imgDessert(Event event) throws SQLException {
 ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ProName`, `Price` FROM `menu` WHERE Category='Dessert'";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 
 String FoodName= rs.getString("ProName");
 int Price= rs.getInt("Price");
 
 m.setFoodName(FoodName);
 m.setPrice(Price);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  tbMenu.setItems(menuList); 
    }
//by clicking the image it will show only the drinks rows 
    @FXML
    public void imgDrinks(Event event) throws SQLException {
ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ProName`, `Price` FROM `menu` WHERE Category='juice'";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 
 String FoodName= rs.getString("ProName");
 int Price= rs.getInt("Price");
 
 m.setFoodName(FoodName);
 m.setPrice(Price);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  tbMenu.setItems(menuList);  
    }
//by clicking the image it will show only the meal rows 
    @FXML
   public void imgFood(Event event) throws SQLException {
ObservableList<model> menuList =FXCollections.observableArrayList();
        try {
            Connection con= connection();
            String sql= "SELECT `ProName`, `Price` FROM `menu` WHERE Category='Meal'";       
                        
             PreparedStatement pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
 ResultSet rs=  pst.executeQuery();
 while(rs.next()){
 model m= new model();
 
 String FoodName= rs.getString("ProName");
 int Price= rs.getInt("Price");
 
 m.setFoodName(FoodName);
 m.setPrice(Price);
 menuList.addAll(m);
 
 }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  tbMenu.setItems(menuList);          
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        foodColunm.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
     priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }
    
}
