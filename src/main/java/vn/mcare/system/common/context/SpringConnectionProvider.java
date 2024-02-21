package vn.mcare.system.common.context;

import java.sql.Connection;
import javax.sql.DataSource;
import org.jooq.ConnectionProvider;
import org.jooq.exception.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class SpringConnectionProvider implements ConnectionProvider {

  private final DataSource dataSource;

  public SpringConnectionProvider(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Connection acquire() throws DataAccessException {
    return DataSourceUtils.getConnection(dataSource);
  }

  @Override
  public void release(Connection connection) throws DataAccessException {
    DataSourceUtils.releaseConnection(connection, dataSource);
  }
}
