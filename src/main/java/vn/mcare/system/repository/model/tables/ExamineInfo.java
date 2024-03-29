/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import vn.mcare.system.repository.model.Keys;
import vn.mcare.system.repository.model.ThangvtmMcare;
import vn.mcare.system.repository.model.tables.records.ExamineInfoRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ExamineInfo extends TableImpl<ExamineInfoRecord> {

    private static final long serialVersionUID = 1268585210;

    /**
     * The reference instance of <code>thangvtm_mcare.examine_info</code>
     */
    public static final ExamineInfo EXAMINE_INFO = new ExamineInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ExamineInfoRecord> getRecordType() {
        return ExamineInfoRecord.class;
    }

    /**
     * The column <code>thangvtm_mcare.examine_info.examine_id</code>.
     */
    public final TableField<ExamineInfoRecord, String> EXAMINE_ID = createField(DSL.name("examine_id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.status</code>.
     */
    public final TableField<ExamineInfoRecord, Integer> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.attend_doctor</code>.
     */
    public final TableField<ExamineInfoRecord, String> ATTEND_DOCTOR = createField(DSL.name("attend_doctor"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.diagnose</code>.
     */
    public final TableField<ExamineInfoRecord, String> DIAGNOSE = createField(DSL.name("diagnose"), org.jooq.impl.SQLDataType.VARCHAR(1024), this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.check_in</code>.
     */
    public final TableField<ExamineInfoRecord, LocalDateTime> CHECK_IN = createField(DSL.name("check_in"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.check_out</code>.
     */
    public final TableField<ExamineInfoRecord, LocalDateTime> CHECK_OUT = createField(DSL.name("check_out"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.created_at</code>.
     */
    public final TableField<ExamineInfoRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>thangvtm_mcare.examine_info.modified_at</code>.
     */
    public final TableField<ExamineInfoRecord, LocalDateTime> MODIFIED_AT = createField(DSL.name("modified_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>thangvtm_mcare.examine_info</code> table reference
     */
    public ExamineInfo() {
        this(DSL.name("examine_info"), null);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.examine_info</code> table reference
     */
    public ExamineInfo(String alias) {
        this(DSL.name(alias), EXAMINE_INFO);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.examine_info</code> table reference
     */
    public ExamineInfo(Name alias) {
        this(alias, EXAMINE_INFO);
    }

    private ExamineInfo(Name alias, Table<ExamineInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private ExamineInfo(Name alias, Table<ExamineInfoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> ExamineInfo(Table<O> child, ForeignKey<O, ExamineInfoRecord> key) {
        super(child, key, EXAMINE_INFO);
    }

    @Override
    public Schema getSchema() {
        return ThangvtmMcare.THANGVTM_MCARE;
    }

    @Override
    public UniqueKey<ExamineInfoRecord> getPrimaryKey() {
        return Keys.KEY_EXAMINE_INFO_PRIMARY;
    }

    @Override
    public List<UniqueKey<ExamineInfoRecord>> getKeys() {
        return Arrays.<UniqueKey<ExamineInfoRecord>>asList(Keys.KEY_EXAMINE_INFO_PRIMARY);
    }

    @Override
    public ExamineInfo as(String alias) {
        return new ExamineInfo(DSL.name(alias), this);
    }

    @Override
    public ExamineInfo as(Name alias) {
        return new ExamineInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ExamineInfo rename(String name) {
        return new ExamineInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ExamineInfo rename(Name name) {
        return new ExamineInfo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, Integer, String, String, LocalDateTime, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
