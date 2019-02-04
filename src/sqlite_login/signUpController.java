
package sqlite_login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sqlite_login.helperClass.DbConnect;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class signUpController implements Initializable {

     @FXML
    private JFXTextField pf_username;

    @FXML
    private JFXTextField pf_emal;

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
    private void signup(javafx.scene.input.MouseEvent event) {
        Connection connection = DbConnect.getInstance().getConnection();
       
         try {
              String email= pf_emal.getText();
              String username= pf_username.getText();
              String password= pf_password.getText();
              Random rand = new Random();
              int ids= rand.nextInt(4000)+1;
              String id= String.valueOf(ids);
              
             Statement statement = connection.createStatement();
             
             int status = statement.executeUpdate( "insert into user(username,password,email,id) values('"+username+"','"+password+"','"+email+"','"+id+"') ");
             if(status>0){
                 System.out.println("User Registered");
             }else{
             
             }
         } catch (SQLException ex) {
             Logger.getLogger(signUpController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void login(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Node node= (Node) event.getSource();
        Stage stage =(Stage) node.getScene().getWindow();
        stage.setScene(new Scene (root));
                
    }
    
}
