/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;

import vn.mcare.system.repository.model.tables.HealthMaintenance;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HealthMaintenanceRecord extends UpdatableRecordImpl<HealthMaintenanceRecord> implements Record11<Integer, String, Double, Double, Double, Double, Double, Double, Double, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -1864232476;

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.id</code>.
     */
    public HealthMaintenanceRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.examine_id</code>.
     */
    public HealthMaintenanceRecord setExamineId(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.examine_id</code>.
     */
    public String getExamineId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.blood_pressure_min</code>.
     */
    public HealthMaintenanceRecord setBloodPressureMin(Double value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.blood_pressure_min</code>.
     */
    public Double getBloodPressureMin() {
        return (Double) get(2);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.blood_pressure_max</code>.
     */
    public HealthMaintenanceRecord setBloodPressureMax(Double value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.blood_pressure_max</code>.
     */
    public Double getBloodPressureMax() {
        return (Double) get(3);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.blood_sugar</code>.
     */
    public HealthMaintenanceRecord setBloodSugar(Double value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.blood_sugar</code>.
     */
    public Double getBloodSugar() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.cholesterol</code>.
     */
    public HealthMaintenanceRecord setCholesterol(Double value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.cholesterol</code>.
     */
    public Double getCholesterol() {
        return (Double) get(5);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.temperature</code>.
     */
    public HealthMaintenanceRecord setTemperature(Double value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.temperature</code>.
     */
    public Double getTemperature() {
        return (Double) get(6);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.weight</code>.
     */
    public HealthMaintenanceRecord setWeight(Double value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.weight</code>.
     */
    public Double getWeight() {
        return (Double) get(7);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.height</code>.
     */
    public HealthMaintenanceRecord setHeight(Double value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.height</code>.
     */
    public Double getHeight() {
        return (Double) get(8);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.created_at</code>.
     */
    public HealthMaintenanceRecord setCreatedAt(LocalDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(9);
    }

    /**
     * Setter for <code>thangvtm_mcare.health_maintenance.modified_at</code>.
     */
    public HealthMaintenanceRecord setModifiedAt(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.health_maintenance.modified_at</code>.
     */
    public LocalDateTime getModifiedAt() {
        return (LocalDateTime) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<Integer, String, Double, Double, Double, Double, Double, Double, Double, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<Integer, String, Double, Double, Double, Double, Double, Double, Double, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return HealthMaintenance.HEALTH_MAINTENANCE.ID;
    }

    @Override
    public Field<String> field2() {
        return HealthMaintenance.HEALTH_MAINTENANCE.EXAMINE_ID;
    }

    @Override
    public Field<Double> field3() {
        return HealthMaintenance.HEALTH_MAINTENANCE.BLOOD_PRESSURE_MIN;
    }

    @Override
    public Field<Double> field4() {
        return HealthMaintenance.HEALTH_MAINTENANCE.BLOOD_PRESSURE_MAX;
    }

    @Override
    public Field<Double> field5() {
        return HealthMaintenance.HEALTH_MAINTENANCE.BLOOD_SUGAR;
    }

    @Override
    public Field<Double> field6() {
        return HealthMaintenance.HEALTH_MAINTENANCE.CHOLESTEROL;
    }

    @Override
    public Field<Double> field7() {
        return HealthMaintenance.HEALTH_MAINTENANCE.TEMPERATURE;
    }

    @Override
    public Field<Double> field8() {
        return HealthMaintenance.HEALTH_MAINTENANCE.WEIGHT;
    }

    @Override
    public Field<Double> field9() {
        return HealthMaintenance.HEALTH_MAINTENANCE.HEIGHT;
    }

    @Override
    public Field<LocalDateTime> field10() {
        return HealthMaintenance.HEALTH_MAINTENANCE.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field11() {
        return HealthMaintenance.HEALTH_MAINTENANCE.MODIFIED_AT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getExamineId();
    }

    @Override
    public Double component3() {
        return getBloodPressureMin();
    }

    @Override
    public Double component4() {
        return getBloodPressureMax();
    }

    @Override
    public Double component5() {
        return getBloodSugar();
    }

    @Override
    public Double component6() {
        return getCholesterol();
    }

    @Override
    public Double component7() {
        return getTemperature();
    }

    @Override
    public Double component8() {
        return getWeight();
    }

    @Override
    public Double component9() {
        return getHeight();
    }

    @Override
    public LocalDateTime component10() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component11() {
        return getModifiedAt();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getExamineId();
    }

    @Override
    public Double value3() {
        return getBloodPressureMin();
    }

    @Override
    public Double value4() {
        return getBloodPressureMax();
    }

    @Override
    public Double value5() {
        return getBloodSugar();
    }

    @Override
    public Double value6() {
        return getCholesterol();
    }

    @Override
    public Double value7() {
        return getTemperature();
    }

    @Override
    public Double value8() {
        return getWeight();
    }

    @Override
    public Double value9() {
        return getHeight();
    }

    @Override
    public LocalDateTime value10() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value11() {
        return getModifiedAt();
    }

    @Override
    public HealthMaintenanceRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value2(String value) {
        setExamineId(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value3(Double value) {
        setBloodPressureMin(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value4(Double value) {
        setBloodPressureMax(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value5(Double value) {
        setBloodSugar(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value6(Double value) {
        setCholesterol(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value7(Double value) {
        setTemperature(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value8(Double value) {
        setWeight(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value9(Double value) {
        setHeight(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value10(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord value11(LocalDateTime value) {
        setModifiedAt(value);
        return this;
    }

    @Override
    public HealthMaintenanceRecord values(Integer value1, String value2, Double value3, Double value4, Double value5, Double value6, Double value7, Double value8, Double value9, LocalDateTime value10, LocalDateTime value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HealthMaintenanceRecord
     */
    public HealthMaintenanceRecord() {
        super(HealthMaintenance.HEALTH_MAINTENANCE);
    }

    /**
     * Create a detached, initialised HealthMaintenanceRecord
     */
    public HealthMaintenanceRecord(Integer id, String examineId, Double bloodPressureMin, Double bloodPressureMax, Double bloodSugar, Double cholesterol, Double temperature, Double weight, Double height, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(HealthMaintenance.HEALTH_MAINTENANCE);

        set(0, id);
        set(1, examineId);
        set(2, bloodPressureMin);
        set(3, bloodPressureMax);
        set(4, bloodSugar);
        set(5, cholesterol);
        set(6, temperature);
        set(7, weight);
        set(8, height);
        set(9, createdAt);
        set(10, modifiedAt);
    }
}
