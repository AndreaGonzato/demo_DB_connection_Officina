import java.sql.*;

public class Intervento implements Insertable {
  Timestamp inizio, fine;
  String targa;

  public Intervento(Timestamp inizio, Timestamp fine, String targa) {
    this.inizio = inizio;
    this.fine = fine;
    this.targa = targa;
  }

  public void insert(Connection conn) throws SQLException {
    if (SQLInjectionParser.detectSQLInjection(targa)) {
      throw new SQLException("detected a possible SQL Injection");
    }
    try (
            CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiIntervento(?, ?, ?)}")
    ) {
      callableStatement.setTimestamp(1, inizio);
      callableStatement.setTimestamp(2, fine);
      callableStatement.setString(3, targa);
      boolean result = callableStatement.execute();
      if (result) {
        throw new SQLException("a result was provided by the query when it was not supposed to");
      }
    }
  }

}
