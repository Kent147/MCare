/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import vn.mcare.system.repository.model.Keys;
import vn.mcare.system.repository.model.ThangvtmMcare;
import vn.mcare.system.repository.model.tables.records.DistrictRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class District extends TableImpl<DistrictRecord> {

    private static final long serialVersionUID = -1999114095;

    /**
     * The reference instance of <code>thangvtm_mcare.district</code>
     */
    public static final District DISTRICT = new District();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DistrictRecord> getRecordType() {
        return DistrictRecord.class;
    }

    /**
     * The column <code>thangvtm_mcare.district.id</code>.
     */
    public final TableField<DistrictRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.district.name</code>.
     */
    public final TableField<DistrictRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.district.prefix</code>.
     */
    public final TableField<DistrictRecord, String> PREFIX = createField(DSL.name("prefix"), org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.district.province_id</code>.
     */
    public final TableField<DistrictRecord, Integer> PROVINCE_ID = createField(DSL.name("province_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>thangvtm_mcare.district</code> table reference
     */
    public District() {
        this(DSL.name("district"), null);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.district</code> table reference
     */
    public District(String alias) {
        this(DSL.name(alias), DISTRICT);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.district</code> table reference
     */
    public District(Name alias) {
        this(alias, DISTRICT);
    }

    private District(Name alias, Table<DistrictRecord> aliased) {
        this(alias, aliased, null);
    }

    private District(Name alias, Table<DistrictRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> District(Table<O> child, ForeignKey<O, DistrictRecord> key) {
        super(child, key, DISTRICT);
    }

    @Override
    public Schema getSchema() {
        return ThangvtmMcare.THANGVTM_MCARE;
    }

    @Override
    public UniqueKey<DistrictRecord> getPrimaryKey() {
        return Keys.KEY_DISTRICT_PRIMARY;
    }

    @Override
    public List<UniqueKey<DistrictRecord>> getKeys() {
        return Arrays.<UniqueKey<DistrictRecord>>asList(Keys.KEY_DISTRICT_PRIMARY);
    }

    @Override
    public District as(String alias) {
        return new District(DSL.name(alias), this);
    }

    @Override
    public District as(Name alias) {
        return new District(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public District rename(String name) {
        return new District(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public District rename(Name name) {
        return new District(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
