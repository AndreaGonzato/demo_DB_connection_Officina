import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Lavoratore implements Insertable {
  String codiceFiscale, nome, cognome, ruolo, telefono, email;

  public Lavoratore(String codiceFiscale, String nome, String cognome, String ruolo, String telefono, String email) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.ruolo = ruolo;
    this.telefono = telefono;
    this.email = email;
  }

  public void insert(Connection conn) throws SQLException {
    if (SQLInjectionParser.detectSQLInjection(codiceFiscale, nome, cognome, ruolo, telefono, email)) {
      throw new SQLException("detected a possible SQL Injection");
    }
    try (
            CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiLavoratore(?, ?, ?, ?, ?, ?)}")
    ) {
      callableStatement.setString(1, codiceFiscale);
      callableStatement.setString(2, nome);
      callableStatement.setString(3, cognome);
      callableStatement.setString(4, ruolo);
      callableStatement.setString(5, telefono);
      callableStatement.setString(6, email);
      boolean result = callableStatement.execute();
      if (result) {
        throw new SQLException("a result was provided by the query when it was not supposed to");
      }
    }
  }

}
