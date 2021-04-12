/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
 * FXML Controller class
 *
 * @author Manizha Nizami
 */
public class WaiterPageController implements Initializable {
 @FXML
    private Button btnDashboardUser;

    @FXML
    private Tooltip userDashboard;

    @FXML
    private Button navTask;

    @FXML
    private Tooltip userCheckReservation;

    @FXML
    private Button navMenu;

    @FXML
    private Tooltip userSeeMenu;

    @FXML
    private Button naveatten;

    @FXML
    private Tooltip userCheckReservation1;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton seeMenu;

    @FXML
    private JFXButton seeTasks;

    @FXML
    private JFXButton seeAttend;

    @FXML
    void btnDashboardUser2(ActionEvent event) {

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
    void btnSeeAttend(ActionEvent event) throws IOException {
 Parent root;
            
             root= FXMLLoader.load(getClass().getResource("seeAttendenceWaiter.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setScene(scene);
                s.show();
    }

    @FXML
    void btnSeeMenu(ActionEvent event)  throws IOException {
 Parent root;
            
             root= FXMLLoader.load(getClass().getResource("menu.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setScene(scene);
                s.show();

    }

    @FXML
    void btnSeeTasks(ActionEvent event)  throws IOException {
 Parent root;
            
             root= FXMLLoader.load(getClass().getResource("seeTasksWaiter.fxml"));
              Stage s= new Stage();
              Scene scene = new Scene(root);
               s.setScene(scene);
                s.show();

    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
