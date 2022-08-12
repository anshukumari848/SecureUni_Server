package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// sql class
public class MysqlConnection {

    public static Connection connect(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");

           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/squiz","root","");
           return con;
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return null;
    }
}
