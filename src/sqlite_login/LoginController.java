/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite_login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sqlite_login.helperClass.DbConnect;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField tf_username;
    @FXML
    private JFXPasswordField pf_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(MouseEvent event) throws IOException {
        String username = tf_username.getText(), password= pf_password.getText();
        
        Connection connection= DbConnect.getInstance().getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where username = '"+username+"' and password='"+password+"' ");
            
            if(resultSet.next()){
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        Node node= (Node) event.getSource();
        Stage stage =(Stage) node.getScene().getWindow();
        stage.setScene(new Scene (root));
         resultSet.close();
         connection.close(); //always close all connections else you will get an error that sqlite is locked
            }else{
                System.out.println("Invalid user Input");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void signup(MouseEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Node node= (Node) event.getSource();
        Stage stage =(Stage) node.getScene().getWindow();
        stage.setScene(new Scene (root));
    }
    
}
