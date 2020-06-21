/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBConnection {
    
    
    private static String userName = "dbhussein";
    private static String password = "husseindb";
    private static String link = "jdbc:mysql://localhost/class";
    private static String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
    
    public static Connection condb() throws SQLException{
        
        return DriverManager.getConnection(link + unicode, userName, password);
      
    }
    
}
