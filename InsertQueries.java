package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Pavel Šeda (154208)
 *
 */
public class InsertQueries {

  public InsertQueries() {}


  public void performInsertQuery(String insertQuery) {
    if (insertQuery == null) {
      throw new NullPointerException("query must not be null!");
    } else if (insertQuery.isEmpty()) {
      throw new IllegalArgumentException("query must not be empty!");
    }
    Connection conn = DBConnection.getDBConnection();
    try (PreparedStatement prStmt = conn.prepareStatement(insertQuery);) {
      int rowsInserted = prStmt.executeUpdate();
      // System.out.println("Bylo vloženo uživatelů: " + rowsInserted);
      System.out.println("Byl vložen uživatel s emailem: " + "myname123@stud.feec.vutbr.cz");
    } catch (SQLException e) {
      System.out.println("Uživatel s emailem: " + "myname123@stud.feec.vutbr.cz "
          + "již byl vložen nemusíte jej vkládat znovu");
      // e.printStackTrace();
    }
  }

  public void insertNewUser(String name, String surname, String birthday, float average, int cyber) {
    if (name == null || surname == null || birthday == null)
      throw new NullPointerException("Musi byt zadany vsechny parametry.");

    Connection conn = DBConnection.getDBConnection();

    String insertUser = "INSERT INTO studenti(name,surname,birthday,average,cyber) VALUES(?,?,?,?,?)";

    try (PreparedStatement prStmt = conn.prepareStatement(insertUser)) {
      prStmt.setString(1, name);
      prStmt.setString(2, surname);
      prStmt.setString(3, birthday);
      prStmt.setDouble(4, average);
      prStmt.setInt(5, cyber);

      prStmt.executeUpdate();

      System.out.println("Nový uživatel byl vložen do databáze!");
    } catch (SQLException e) {
      System.out.println("Uživatel už byl vložen nebo jste zadali špatně SQL příkaz INSERT");
      // e.printStackTrace();
    }
  }
  
  public boolean createTable(){
		Connection conn = DBConnection.getDBConnection();
	    if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS studenti (" + "id integer PRIMARY KEY," + "name varchar(255) NOT NULL,"+ "surname varchar(255), " + "birthday varchar(50),"+ "average real" + "cyber integer" + ");";
	    try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}
  
  public boolean delete(int id) {
      String sql = "DELETE FROM studenti WHERE id = ?";
      Connection conn = DBConnection.getDBConnection();

      try {
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, id);
          // execute the delete statement
          pstmt.executeUpdate();
          return true;

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
      return false;
  }



}
