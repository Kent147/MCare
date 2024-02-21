package vn.mcare.system.common.context;

import javax.sql.DataSource;
import org.jooq.ExecuteContext;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

public class ExceptionTranslator extends DefaultExecuteListener {

  /** Generated UID */
  private static final long serialVersionUID = 6914082794499325841L;

  private final DataSource ds;

  public ExceptionTranslator(DataSource ds) {
    this.ds = ds;
  }

  @Override
  public void exception(ExecuteContext ctx) {
    ctx.exception(translator().translate("jOOQ", ctx.sql(), ctx.sqlException()));
  }

  private SQLExceptionTranslator translator() {
    return ds == null
        ? new SQLStateSQLExceptionTranslator()
        : new SQLErrorCodeSQLExceptionTranslator(ds);
  }
}
