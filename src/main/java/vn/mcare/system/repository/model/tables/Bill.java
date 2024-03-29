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
import org.jooq.Row13;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import vn.mcare.system.repository.model.Keys;
import vn.mcare.system.repository.model.ThangvtmMcare;
import vn.mcare.system.repository.model.tables.records.BillRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Bill extends TableImpl<BillRecord> {

    private static final long serialVersionUID = 1848783577;

    /**
     * The reference instance of <code>thangvtm_mcare.bill</code>
     */
    public static final Bill BILL = new Bill();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BillRecord> getRecordType() {
        return BillRecord.class;
    }

    /**
     * The column <code>thangvtm_mcare.bill.bill_id</code>.
     */
    public final TableField<BillRecord, String> BILL_ID = createField(DSL.name("bill_id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.bill_type</code>.
     */
    public final TableField<BillRecord, Integer> BILL_TYPE = createField(DSL.name("bill_type"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.bill_date</code>.
     */
    public final TableField<BillRecord, LocalDateTime> BILL_DATE = createField(DSL.name("bill_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.sub_price</code>.
     */
    public final TableField<BillRecord, Double> SUB_PRICE = createField(DSL.name("sub_price"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.is_vat</code>.
     */
    public final TableField<BillRecord, Boolean> IS_VAT = createField(DSL.name("is_vat"), org.jooq.impl.SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.vat_rate</code>.
     */
    public final TableField<BillRecord, Integer> VAT_RATE = createField(DSL.name("vat_rate"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.vat_price</code>.
     */
    public final TableField<BillRecord, Double> VAT_PRICE = createField(DSL.name("vat_price"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.total_price</code>.
     */
    public final TableField<BillRecord, Double> TOTAL_PRICE = createField(DSL.name("total_price"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.customer_id</code>.
     */
    public final TableField<BillRecord, String> CUSTOMER_ID = createField(DSL.name("customer_id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.create_by</code>.
     */
    public final TableField<BillRecord, String> CREATE_BY = createField(DSL.name("create_by"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.description</code>.
     */
    public final TableField<BillRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.created_at</code>.
     */
    public final TableField<BillRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>thangvtm_mcare.bill.modified_at</code>.
     */
    public final TableField<BillRecord, LocalDateTime> MODIFIED_AT = createField(DSL.name("modified_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>thangvtm_mcare.bill</code> table reference
     */
    public Bill() {
        this(DSL.name("bill"), null);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.bill</code> table reference
     */
    public Bill(String alias) {
        this(DSL.name(alias), BILL);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.bill</code> table reference
     */
    public Bill(Name alias) {
        this(alias, BILL);
    }

    private Bill(Name alias, Table<BillRecord> aliased) {
        this(alias, aliased, null);
    }

    private Bill(Name alias, Table<BillRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Bill(Table<O> child, ForeignKey<O, BillRecord> key) {
        super(child, key, BILL);
    }

    @Override
    public Schema getSchema() {
        return ThangvtmMcare.THANGVTM_MCARE;
    }

    @Override
    public UniqueKey<BillRecord> getPrimaryKey() {
        return Keys.KEY_BILL_PRIMARY;
    }

    @Override
    public List<UniqueKey<BillRecord>> getKeys() {
        return Arrays.<UniqueKey<BillRecord>>asList(Keys.KEY_BILL_PRIMARY);
    }

    @Override
    public Bill as(String alias) {
        return new Bill(DSL.name(alias), this);
    }

    @Override
    public Bill as(Name alias) {
        return new Bill(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Bill rename(String name) {
        return new Bill(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Bill rename(Name name) {
        return new Bill(name, null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row13<String, Integer, LocalDateTime, Double, Boolean, Integer, Double, Double, String, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row13) super.fieldsRow();
    }
}
