/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite_login.helperClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DbConnect {
    private DbConnect(){
    
    }
    
    public static DbConnect getInstance(){
    return new DbConnect();
    }
    
    public Connection getConnection(){
    String connect_string="jdbc:sqlite:data.db";
 
    Connection connection = null;
    
        try {
               Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection(connect_string);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
