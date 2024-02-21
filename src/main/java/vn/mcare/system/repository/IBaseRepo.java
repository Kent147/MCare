package vn.mcare.system.repository;

import java.io.Serializable;
import java.util.List;
import org.jooq.Record;
import org.jooq.Table;

public interface IBaseRepo<K extends Serializable, R extends Record> {

  List<R> fetchAll();

  int save(R record);

  int update(R record);

  int delete(K key);

  R findOne(K id);

  Integer count(Table table);
}
