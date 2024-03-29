/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import vn.mcare.system.repository.model.Keys;
import vn.mcare.system.repository.model.ThangvtmMcare;
import vn.mcare.system.repository.model.tables.records.DataListRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DataList extends TableImpl<DataListRecord> {

    private static final long serialVersionUID = 1774379955;

    /**
     * The reference instance of <code>thangvtm_mcare.data_list</code>
     */
    public static final DataList DATA_LIST = new DataList();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DataListRecord> getRecordType() {
        return DataListRecord.class;
    }

    /**
     * The column <code>thangvtm_mcare.data_list.id</code>.
     */
    public final TableField<DataListRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>thangvtm_mcare.data_list.data_key</code>.
     */
    public final TableField<DataListRecord, String> DATA_KEY = createField(DSL.name("data_key"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.data_list.data_value</code>.
     */
    public final TableField<DataListRecord, String> DATA_VALUE = createField(DSL.name("data_value"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.data_list.label</code>.
     */
    public final TableField<DataListRecord, String> LABEL = createField(DSL.name("label"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.data_list.description</code>.
     */
    public final TableField<DataListRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>thangvtm_mcare.data_list</code> table reference
     */
    public DataList() {
        this(DSL.name("data_list"), null);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.data_list</code> table reference
     */
    public DataList(String alias) {
        this(DSL.name(alias), DATA_LIST);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.data_list</code> table reference
     */
    public DataList(Name alias) {
        this(alias, DATA_LIST);
    }

    private DataList(Name alias, Table<DataListRecord> aliased) {
        this(alias, aliased, null);
    }

    private DataList(Name alias, Table<DataListRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DataList(Table<O> child, ForeignKey<O, DataListRecord> key) {
        super(child, key, DATA_LIST);
    }

    @Override
    public Schema getSchema() {
        return ThangvtmMcare.THANGVTM_MCARE;
    }

    @Override
    public Identity<DataListRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DATA_LIST;
    }

    @Override
    public UniqueKey<DataListRecord> getPrimaryKey() {
        return Keys.KEY_DATA_LIST_PRIMARY;
    }

    @Override
    public List<UniqueKey<DataListRecord>> getKeys() {
        return Arrays.<UniqueKey<DataListRecord>>asList(Keys.KEY_DATA_LIST_PRIMARY);
    }

    @Override
    public DataList as(String alias) {
        return new DataList(DSL.name(alias), this);
    }

    @Override
    public DataList as(Name alias) {
        return new DataList(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DataList rename(String name) {
        return new DataList(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DataList rename(Name name) {
        return new DataList(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
