/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import vn.mcare.system.repository.model.tables.MedicalHistory;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MedicalHistoryRecord extends UpdatableRecordImpl<MedicalHistoryRecord> implements Record7<Integer, String, String, Integer, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 910644422;

    /**
     * Setter for <code>thangvtm_mcare.medical_history.id</code>.
     */
    public MedicalHistoryRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>thangvtm_mcare.medical_history.examine_id</code>.
     */
    public MedicalHistoryRecord setExamineId(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.examine_id</code>.
     */
    public String getExamineId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>thangvtm_mcare.medical_history.pathological</code>.
     */
    public MedicalHistoryRecord setPathological(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.pathological</code>.
     */
    public String getPathological() {
        return (String) get(2);
    }

    /**
     * Setter for <code>thangvtm_mcare.medical_history.status</code>.
     */
    public MedicalHistoryRecord setStatus(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.status</code>.
     */
    public Integer getStatus() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>thangvtm_mcare.medical_history.note</code>.
     */
    public MedicalHistoryRecord setNote(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.note</code>.
     */
    public String getNote() {
        return (String) get(4);
    }

    /**
     * Setter for <code>thangvtm_mcare.medical_history.created_at</code>.
     */
    public MedicalHistoryRecord setCreatedAt(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>thangvtm_mcare.medical_history.modified_at</code>.
     */
    public MedicalHistoryRecord setModifiedAt(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.medical_history.modified_at</code>.
     */
    public LocalDateTime getModifiedAt() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, String, String, Integer, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, String, String, Integer, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return MedicalHistory.MEDICAL_HISTORY.ID;
    }

    @Override
    public Field<String> field2() {
        return MedicalHistory.MEDICAL_HISTORY.EXAMINE_ID;
    }

    @Override
    public Field<String> field3() {
        return MedicalHistory.MEDICAL_HISTORY.PATHOLOGICAL;
    }

    @Override
    public Field<Integer> field4() {
        return MedicalHistory.MEDICAL_HISTORY.STATUS;
    }

    @Override
    public Field<String> field5() {
        return MedicalHistory.MEDICAL_HISTORY.NOTE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return MedicalHistory.MEDICAL_HISTORY.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return MedicalHistory.MEDICAL_HISTORY.MODIFIED_AT;
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
    public String component3() {
        return getPathological();
    }

    @Override
    public Integer component4() {
        return getStatus();
    }

    @Override
    public String component5() {
        return getNote();
    }

    @Override
    public LocalDateTime component6() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component7() {
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
    public String value3() {
        return getPathological();
    }

    @Override
    public Integer value4() {
        return getStatus();
    }

    @Override
    public String value5() {
        return getNote();
    }

    @Override
    public LocalDateTime value6() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value7() {
        return getModifiedAt();
    }

    @Override
    public MedicalHistoryRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord value2(String value) {
        setExamineId(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord value3(String value) {
        setPathological(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord value4(Integer value) {
        setStatus(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord value5(String value) {
        setNote(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord value6(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord value7(LocalDateTime value) {
        setModifiedAt(value);
        return this;
    }

    @Override
    public MedicalHistoryRecord values(Integer value1, String value2, String value3, Integer value4, String value5, LocalDateTime value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MedicalHistoryRecord
     */
    public MedicalHistoryRecord() {
        super(MedicalHistory.MEDICAL_HISTORY);
    }

    /**
     * Create a detached, initialised MedicalHistoryRecord
     */
    public MedicalHistoryRecord(Integer id, String examineId, String pathological, Integer status, String note, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(MedicalHistory.MEDICAL_HISTORY);

        set(0, id);
        set(1, examineId);
        set(2, pathological);
        set(3, status);
        set(4, note);
        set(5, createdAt);
        set(6, modifiedAt);
    }
}