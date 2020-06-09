import java.sql.*;

public class Main {

  // complete this settings before running
  static final String USER = ""; // put your D.B. user here
  static final String PASSWORD = ""; // put your password here
  static final String DATABASENAME = "officina"; // put your D.B name here
  static final int PORTNUMBER = 3306; // put your port number D.B connection here

  public static void main(String[] args) {
    try (
            Connection conn = getConnection(USER, PASSWORD, DATABASENAME, PORTNUMBER)
    ) {

      // example of use
      Insertable insertable = new Privato("Luigi", "Marconi", "3414773069", "luigimarconi@gmail.com");
      //insertable.insert(conn); // uncomment this line to insert into the D.B.

    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Connection getConnection(String user, String password, String databaseName, int portNumber) throws ClassNotFoundException, SQLException {
    // Allocate a database 'Connection' object
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:" + portNumber + "/" + databaseName,
            user, password);   // For MySQL only
    // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
    System.out.println("Database Connected");
    return conn;
  }

}
