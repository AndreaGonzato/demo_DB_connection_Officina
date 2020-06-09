import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Lavoro implements Insertable {
  String descrizione;
  int intervento;

  public Lavoro(String descrizione, int intervento) {
    this.descrizione = descrizione;
    this.intervento = intervento;
  }

  public void insert(Connection conn) throws SQLException {
    if (SQLInjectionParser.detectSQLInjection(descrizione)) {
      throw new SQLException("detected a possible SQL Injection");
    }
    try (
            CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiLavoro(?, ?)}")
    ) {
      callableStatement.setString(1, descrizione);
      callableStatement.setInt(2, intervento);
      boolean result = callableStatement.execute();
      if (result) {
        throw new SQLException("a result was provided by the query when it was not supposed to");
      }
    }
  }
}
