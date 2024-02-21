package vn.mcare.system.common.context;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultDSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AutoCloseableDSLContext extends DefaultDSLContext implements AutoCloseable {

  private static Logger LOGGER = LoggerFactory.getLogger(AutoCloseableDSLContext.class);
  private Connection connection;
  private SQLDialect dialect;
  private DataSource dataSource;
  private ConnectionProvider connectionProvider;
  private Configuration configuration;
  private Settings settings;

  public AutoCloseableDSLContext(SQLDialect dialect) {
    super(dialect);
    this.dialect = dialect;
  }

  public AutoCloseableDSLContext(Connection connection, SQLDialect dialect) {
    super(connection, dialect);
    this.connection = connection;
    this.dialect = dialect;
    System.out.println("new DSL");
  }

  public AutoCloseableDSLContext(Connection connection, SQLDialect dialect, Settings settings) {
    super(connection, dialect, settings);
    this.settings = settings;
    this.connection = connection;
    this.dialect = dialect;
  }

  public AutoCloseableDSLContext(DataSource datasource, SQLDialect dialect) {
    super(datasource, dialect);
    this.dataSource = datasource;
    this.dialect = dialect;
  }

  public AutoCloseableDSLContext(ConnectionProvider connectionProvider, SQLDialect dialect) {
    super(connectionProvider, dialect);
    this.connectionProvider = connectionProvider;
    this.dialect = dialect;
  }

  public AutoCloseableDSLContext(Configuration configuration) {
    super(configuration);
    this.configuration = configuration;
  }

  @Override
  public void close() {
    if (connection != null) {
      try {
        connection.close();
        LOGGER.info("connection close");
      } catch (SQLException e) {
        LOGGER.debug(e.getMessage());
      }
    }
  }
}