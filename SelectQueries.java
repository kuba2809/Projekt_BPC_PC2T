package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectQueries {

  public SelectQueries() {}


  public void select() {
	  Connection conn = DBConnection.getDBConnection();
      String sql = "SELECT id, name FROM studenti";
      try {
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql);
           while (rs.next()) {
              	System.out.println(rs.getInt("id") +  "\t" +  
                rs.getString("name"));
          }
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }

  }
}


