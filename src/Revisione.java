import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Revisione implements Insertable {

  Date data;
  String targa;

  public Revisione(Date data, String targa) {
    this.data = data;
    this.targa = targa;
  }

  public void insert(Connection conn) throws SQLException {
    CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiRevisione(?, ?)}");
    callableStatement.setDate(1, data);
    callableStatement.setString(2, targa);
    boolean result = callableStatement.execute();
    if (result) {
      throw new SQLException();
    }
  }

}
