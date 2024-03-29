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
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import vn.mcare.system.repository.model.Keys;
import vn.mcare.system.repository.model.ThangvtmMcare;
import vn.mcare.system.repository.model.tables.records.InventoryRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Inventory extends TableImpl<InventoryRecord> {

    private static final long serialVersionUID = -283377795;

    /**
     * The reference instance of <code>thangvtm_mcare.inventory</code>
     */
    public static final Inventory INVENTORY = new Inventory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InventoryRecord> getRecordType() {
        return InventoryRecord.class;
    }

    /**
     * The column <code>thangvtm_mcare.inventory.inventory_id</code>.
     */
    public final TableField<InventoryRecord, String> INVENTORY_ID = createField(DSL.name("inventory_id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.medicine_id</code>.
     */
    public final TableField<InventoryRecord, String> MEDICINE_ID = createField(DSL.name("medicine_id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.lot_id</code>.
     */
    public final TableField<InventoryRecord, String> LOT_ID = createField(DSL.name("lot_id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.amount</code>.
     */
    public final TableField<InventoryRecord, Integer> AMOUNT = createField(DSL.name("amount"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.import_price</code>.
     */
    public final TableField<InventoryRecord, Double> IMPORT_PRICE = createField(DSL.name("import_price"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.sell_price</code>.
     */
    public final TableField<InventoryRecord, Double> SELL_PRICE = createField(DSL.name("sell_price"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.cal_unit</code>.
     */
    public final TableField<InventoryRecord, Integer> CAL_UNIT = createField(DSL.name("cal_unit"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.created_at</code>.
     */
    public final TableField<InventoryRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>thangvtm_mcare.inventory.modified_at</code>.
     */
    public final TableField<InventoryRecord, LocalDateTime> MODIFIED_AT = createField(DSL.name("modified_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>thangvtm_mcare.inventory</code> table reference
     */
    public Inventory() {
        this(DSL.name("inventory"), null);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.inventory</code> table reference
     */
    public Inventory(String alias) {
        this(DSL.name(alias), INVENTORY);
    }

    /**
     * Create an aliased <code>thangvtm_mcare.inventory</code> table reference
     */
    public Inventory(Name alias) {
        this(alias, INVENTORY);
    }

    private Inventory(Name alias, Table<InventoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private Inventory(Name alias, Table<InventoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Inventory(Table<O> child, ForeignKey<O, InventoryRecord> key) {
        super(child, key, INVENTORY);
    }

    @Override
    public Schema getSchema() {
        return ThangvtmMcare.THANGVTM_MCARE;
    }

    @Override
    public UniqueKey<InventoryRecord> getPrimaryKey() {
        return Keys.KEY_INVENTORY_PRIMARY;
    }

    @Override
    public List<UniqueKey<InventoryRecord>> getKeys() {
        return Arrays.<UniqueKey<InventoryRecord>>asList(Keys.KEY_INVENTORY_PRIMARY);
    }

    @Override
    public Inventory as(String alias) {
        return new Inventory(DSL.name(alias), this);
    }

    @Override
    public Inventory as(Name alias) {
        return new Inventory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventory rename(String name) {
        return new Inventory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventory rename(Name name) {
        return new Inventory(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<String, String, String, Integer, Double, Double, Integer, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
