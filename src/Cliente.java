import java.sql.Connection;
import java.sql.SQLException;

public abstract class Cliente {

  String nome;
  String telefono;
  String email;

  public Cliente(String nome, String telefono, String email) {
    this.nome = nome;
    this.telefono = telefono;
    this.email = email;
  }

  abstract void insert(Connection conn) throws SQLException;

}
