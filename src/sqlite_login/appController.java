package sqlite_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sqlite_login.helperClass.DbConnect;

public class appController implements Initializable {

    @FXML
    private TableView<tableModel> table;
    @FXML
    private TableColumn<tableModel, String> col_id;
    @FXML
    private TableColumn<tableModel, String> col_name;
    @FXML
    private TableColumn<tableModel, String> col_password;
    @FXML
    private TableColumn<tableModel, String> col_email;
    @FXML
    private JFXTextField tf_user;
    @FXML
    private JFXTextField id;
     @FXML
    private JFXTextField tf_password;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXButton bt_update;
    
    
    ObservableList<tableModel> oblist = FXCollections.observableArrayList();
    @FXML
    private JFXButton bt_delete;
 
 
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       loadTable();
        
    } 
    
    private void loadTable(){
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery("select * from user");
            
            while (rs.next()){
                oblist.add(new tableModel( rs.getString("id"),rs.getString("username"),rs.getString("password"), rs.getString("email")));
            }
            rs.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        // TODO
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        table.setItems(oblist);
    }

    @FXML
    private void tablbeView(MouseEvent event) {
        if(table.getSelectionModel().getSelectedItems() != null){
            tableModel sel= table.getSelectionModel().getSelectedItem();
            tf_user.setText(sel.getName());
            tf_password.setText(sel.getPassword());
            tf_email.setText(sel.getEmail());
            id.setText(sel.getId());
        }
    }

    @FXML
    private void do_update(MouseEvent event) throws SQLException {
        String user_id = id.getText();
        String name = tf_user.getText();
         String pass = tf_password.getText();
          String email = tf_email.getText();
          
          Connection connection = DbConnect.getInstance().getConnection();
          
          String sql= "UPDATE user set username ='"+name+"',password='"+pass+"',email='"+email+"' where id ='"+user_id+"'";
          Statement st = connection.createStatement();
          int status = st.executeUpdate(sql);
           if(status>0){
                 System.out.println("User Updated");
             }else{
               System.out.println("Unable to update");
             }
           //clear table
         table.getItems().clear();
         //load table again
           loadTable();
          
    }

    @FXML
    private void do_delete(MouseEvent event) throws SQLException {
              String user_id = id.getText();
      
          
          Connection connection = DbConnect.getInstance().getConnection();
          
          String sql= "Delete From user  where id ='"+user_id+"'";
          Statement st = connection.createStatement();
          int status = st.executeUpdate(sql);
           if(status>0){
                 System.out.println("User Removed");
             }else{
               System.out.println("User Removed");
             }
           //clear table
         table.getItems().clear();
         //load table again
           loadTable();
    }

}