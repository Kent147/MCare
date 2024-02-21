package vn.mcare.system.common.converter;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.time.LocalDateTime;
import org.jooq.Binding;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingGetSQLInputContext;
import org.jooq.BindingGetStatementContext;
import org.jooq.BindingRegisterContext;
import org.jooq.BindingSQLContext;
import org.jooq.BindingSetSQLOutputContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Converter;
import org.jooq.impl.DefaultBinding;

public class TimestampToLongBinding implements Binding<LocalDateTime, Long> {

  private final Converter<LocalDateTime, Long> converter = new TimestampConverter();
  private final DefaultBinding<LocalDateTime, Long> defaultBinding = new DefaultBinding<>(
      converter());

  @Override
  public Converter<LocalDateTime, Long> converter() {
    return converter;
  }

  @Override
  public void sql(BindingSQLContext<Long> ctx) throws SQLException {
    defaultBinding.sql(ctx);
  }

  @Override
  public void register(BindingRegisterContext<Long> ctx) throws SQLException {
    defaultBinding.register(ctx);
  }

  @Override
  public void set(BindingSetStatementContext<Long> ctx) throws SQLException {
    defaultBinding.set(ctx);
  }

  @Override
  public void get(BindingGetResultSetContext<Long> ctx) throws SQLException {
    defaultBinding.get(ctx);
  }

  @Override
  public void get(BindingGetStatementContext<Long> ctx) throws SQLException {
    defaultBinding.get(ctx);
  }

  // Getting a value from a JDBC SQLInput (useful for Oracle OBJECT types)
  @Override
  public void get(BindingGetSQLInputContext<Long> ctx) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  // Setting a value from a JDBC SQLInput (useful for Oracle OBJECT types)
  @Override
  public void set(BindingSetSQLOutputContext<Long> ctx) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }
}
