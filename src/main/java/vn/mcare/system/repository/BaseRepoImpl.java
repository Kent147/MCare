package vn.mcare.system.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DeleteWhereStep;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.UpdateSetMoreStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException.SQLExcuteError;

@Slf4j
public class BaseRepoImpl<K extends Serializable, R extends Record, T extends Table>
        implements IBaseRepo<K, R> {

  @Autowired
  private Connection connection;

  @Qualifier("getConfiguration")
  @Autowired
  private Configuration configuration;

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  protected AutoCloseableDSLContext getAutoCloseableDSLContext() throws Exception {
    //    return new AutoCloseableDSLContext(connection, SQLDialect.MYSQL);
    return new AutoCloseableDSLContext(configuration);
  }

  private UniqueKey getPrimaryKey() throws InstantiationException, IllegalAccessException {
    return getTable().getPrimaryKey();
  }

  private List<TableField<R, ?>> getPrimaryKeyFields()
          throws IllegalAccessException, InstantiationException {
    return getPrimaryKey().getFields();
  }

  private final Class<T> genericTable =
          (Class<T>)
                  ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];

  protected T getTable() throws IllegalAccessException, InstantiationException {
    return genericTable.newInstance();
  }

  private Condition equalKeys(List<TableField<R, ?>> fields, K id) {
    if (fields.size() == 1) { // single primary key
      TableField<R, Object> field = (TableField<R, Object>) fields.get(0);
      return field.equal(field.getDataType().convert(id));
    } else { // composite primary key
      log.debug("unsupported composite keys. Using the first primary key to compare");
      // TODO handle composite keys. Just return a false condition at this moment
      TableField<R, Object> field = (TableField<R, Object>) fields.get(0);
      return field.equal(field.getDataType().convert(id));
    }
  }

  @Override
  public List<R> fetchAll() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context.selectFrom(getTable()).fetch();
    } catch (Exception e) {
      throw new SQLExcuteError("fetch all error");
    }
  }

  @Override
  public int save(R record) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context.insertInto(getTable()).set(record).execute();
    } catch (Exception e) {
      log.error("failed to saveMed record: " + e.getMessage());
      throw new SQLExcuteError("failed to saveMed record");
    }
  }

  @Override
  public int update(R record) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      UpdateSetMoreStep query = context.update(getTable()).set(record);
      for (TableField<R, ?> keyField : getPrimaryKeyFields()) {
        query.where(equalKeys(getPrimaryKeyFields(), (K) record.getValue(keyField.getName())));
      }
      return query.execute();
    } catch (Exception e) {
      log.error("failed to updateMed record: " + e.getMessage());
      throw new SQLExcuteError("failed to updateMed record");
    }
  }

  public int delete(K key) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      DeleteWhereStep query = context.delete(getTable());
      for (TableField<R, ?> keyField : getPrimaryKeyFields()) {
        query.where(equalKeys(getPrimaryKeyFields(), key));
      }
      return query.execute();
    } catch (Exception e) {
      log.error("failed to deleteMed record: " + e.getMessage());
      throw new SQLExcuteError("failed to deleteMed record");
    }
  }

  @Override
  public R findOne(K id) {

    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return (R) context
              .selectFrom(getTable())
              .where(equalKeys(getPrimaryKeyFields(), id))
              .fetchOne();
    } catch (Exception e) {
      log.error("failed to find record: " + e.getMessage(), e);
      throw new SQLExcuteError("failed to find record");
    }

  }

  @Override
  public Integer count(Table table) throws SQLExcuteError {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
              .fetchCount(table);
    } catch (Exception e) {
      log.error("failed to count number of records: " + e.getMessage(), e);
      throw new SQLExcuteError("failed to count number of records");
    }
  }
}
