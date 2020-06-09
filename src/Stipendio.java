import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Stipendio implements Insertable {
  Date data;
  float importo;
  int oreLavorative;
  String lavoratore;

  public Stipendio(Date data, float importo, int oreLavorative, String lavoratore) {
    this.data = data;
    this.importo = importo;
    this.oreLavorative = oreLavorative;
    this.lavoratore = lavoratore;
  }

  public void insert(Connection conn) throws SQLException {
    try (
            CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiStipendio(?, ?, ?, ?)}")
    ) {
      callableStatement.setDate(1, data);
      callableStatement.setFloat(2, importo);
      callableStatement.setInt(3, oreLavorative);
      callableStatement.setString(4, lavoratore);
      boolean result = callableStatement.execute();
      if (result) {
        throw new SQLException("a result was provided by the query when it was not supposed to");
      }
    }
  }

}
