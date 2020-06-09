import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Transazione implements Insertable {
  Timestamp data;
  float importo;
  int intervento;

  public Transazione(Timestamp data, float importo, int intervento) {
    this.data = data;
    this.importo = importo;
    this.intervento = intervento;
  }

  public void insert(Connection conn) throws SQLException {
    try (
            CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiTransazione(?, ?, ?)}")
    ) {
      callableStatement.setTimestamp(1, data);
      callableStatement.setFloat(2, importo);
      callableStatement.setInt(3, intervento);
      boolean result = callableStatement.execute();
      if (result) {
        throw new SQLException("a result was provided by the query when it was not supposed to");
      }
    }
  }
}
