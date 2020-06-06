import java.sql.Connection;
import java.sql.SQLException;

public interface Insertable {
  void insert(Connection conn) throws SQLException;
}
