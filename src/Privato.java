import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Privato extends Cliente implements Insertable {

  String cognome;

  public Privato(String nome, String cognome, String telefono, String email) {
    super(nome, telefono, email);
    this.cognome = cognome;
  }

  public void insert(Connection conn) throws SQLException {
    CallableStatement callableStatement = conn.prepareCall("{call sp_aggiungiPrivato(?, ?, ?, ?)}");
    callableStatement.setString(1, nome);
    callableStatement.setString(2, cognome);
    callableStatement.setString(3, telefono);
    callableStatement.setString(4, email);
    boolean result = callableStatement.execute();
    if (result) {
      throw new SQLException();
    }
  }

}
