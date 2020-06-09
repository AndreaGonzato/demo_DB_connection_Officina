import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Assegnazione implements Insertable {
  String codiceFiscale;
  int lavoro;

  public Assegnazione(String codiceFiscale, int lavoro) {
    this.codiceFiscale = codiceFiscale;
    this.lavoro = lavoro;
  }

  public void insert(Connection conn) throws SQLException {
    try (
            CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiAssegnazione(?, ?)}")
    ) {
      callableStatement.setString(1, codiceFiscale);
      callableStatement.setInt(2, lavoro);
      boolean result = callableStatement.execute();
      if (result) {
        throw new SQLException("a result was provided by the query when it was not supposed to");
      }
    }
  }

}
