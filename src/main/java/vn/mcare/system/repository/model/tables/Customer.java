/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row16;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import vn.mcare.system.repository.model.Keys;
import vn.mcare.system.repository.model.ThangvtmMcare;
import vn.mcare.system.repository.model.tables.records.CustomerRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Customer extends TableImpl<CustomerRecord> {

    private static final long serialVersionUID = -443717820;

    /**
     * The reference instance of <code>thangvtm_mcare.customer</code>
     */
    public static final Customer CUSTOMER = new Customer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CustomerRecord> getRecordType() {
        return CustomerRecord.class;
    }

    /**
     * The column <code>thangvtm_mcare.customer.cif</code>.
     */
    public final TableField<CustomerRecord, String> CIF = createField(DSL.name("cif"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.full_name</code>.
     */
    public final TableField<CustomerRecord, String> FULL_NAME = createField(DSL.name("full_name"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.gender</code>.
     */
    public final TableField<CustomerRecord, Integer> GENDER = createField(DSL.name("gender"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.bod</code>.
     */
    public final TableField<CustomerRecord, LocalDate> BOD = createField(DSL.name("bod"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.card_id</code>.
     */
    public final TableField<CustomerRecord, String> CARD_ID = createField(DSL.name("card_id"), org.jooq.impl.SQLDataType.CHAR(12), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.phone</code>.
     */
    public final TableField<CustomerRecord, String> PHONE = createField(DSL.name("phone"), org.jooq.impl.SQLDataType.CHAR(10), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.email</code>.
     */
    public final TableField<CustomerRecord, String> EMAIL = createField(DSL.name("email"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.nation</code>.
     */
    public final TableField<CustomerRecord, Integer> NATION = createField(DSL.name("nation"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.career</code>.
     */
    public final TableField<CustomerRecord, Integer> CAREER = createField(DSL.name("career"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.street</code>.
     */
    public final TableField<CustomerRecord, String> STREET = createField(DSL.name("street"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.ward</code>.
     */
    public final TableField<CustomerRecord, Integer> WARD = createField(DSL.name("ward"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.district</code>.
     */
    public final TableField<CustomerRecord, Integer> DISTRICT = createField(DSL.name("district"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.province</code>.
     */
    public final TableField<CustomerRecord, Integer> PROVINCE = createField(DSL.name("province"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>thangvtm_mcare.customer.description</code>.
     */
    public final TableField<CustomerRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.created_at</code>.
     */
    public final TableField<CustomerRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>thangvtm_mcare.customer.modified_at</code>.
     */
    public final TableField<CustomerRecord, LocalDateTime> MODIFIED_AT = createField(DSL.name("modified_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>thangvtm_mcare.customer</code> table reference
     */
    public Customer() {
        this(DSL.name("customer"), null);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.customer</code> table reference
     */
    public Customer(String alias) {
        this(DSL.name(alias), CUSTOMER);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.customer</code> table reference
     */
    public Customer(Name alias) {
        this(alias, CUSTOMER);
    }

    private Customer(Name alias, Table<CustomerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Customer(Name alias, Table<CustomerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Customer(Table<O> child, ForeignKey<O, CustomerRecord> key) {
        super(child, key, CUSTOMER);
    }

    @Override
    public Schema getSchema() {
        return ThangvtmMcare.THANGVTM_MCARE;
    }

    @Override
    public UniqueKey<CustomerRecord> getPrimaryKey() {
        return Keys.KEY_CUSTOMER_PRIMARY;
    }

    @Override
    public List<UniqueKey<CustomerRecord>> getKeys() {
        return Arrays.<UniqueKey<CustomerRecord>>asList(Keys.KEY_CUSTOMER_PRIMARY);
    }

    @Override
    public Customer as(String alias) {
        return new Customer(DSL.name(alias), this);
    }

    @Override
    public Customer as(Name alias) {
        return new Customer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(String name) {
        return new Customer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(Name name) {
        return new Customer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row16 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row16<String, String, Integer, LocalDate, String, String, String, Integer, Integer, String, Integer, Integer, Integer, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row16) super.fieldsRow();
    }
}
