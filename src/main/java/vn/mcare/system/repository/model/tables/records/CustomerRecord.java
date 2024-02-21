/*
 * This file is generated by jOOQ.
 */
package vn.mcare.system.repository.model.tables.records;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row16;
import org.jooq.impl.UpdatableRecordImpl;

import vn.mcare.system.repository.model.tables.Customer;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends UpdatableRecordImpl<CustomerRecord> implements Record16<String, String, Integer, LocalDate, String, String, String, Integer, Integer, String, Integer, Integer, Integer, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 2130648399;

    /**
     * Setter for <code>thangvtm_mcare.customer.cif</code>.
     */
    public CustomerRecord setCif(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.cif</code>.
     */
    public String getCif() {
        return (String) get(0);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.full_name</code>.
     */
    public CustomerRecord setFullName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.full_name</code>.
     */
    public String getFullName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.gender</code>.
     */
    public CustomerRecord setGender(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.gender</code>.
     */
    public Integer getGender() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.bod</code>.
     */
    public CustomerRecord setBod(LocalDate value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.bod</code>.
     */
    public LocalDate getBod() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.card_id</code>.
     */
    public CustomerRecord setCardId(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.card_id</code>.
     */
    public String getCardId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.phone</code>.
     */
    public CustomerRecord setPhone(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.phone</code>.
     */
    public String getPhone() {
        return (String) get(5);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.email</code>.
     */
    public CustomerRecord setEmail(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.email</code>.
     */
    public String getEmail() {
        return (String) get(6);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.nation</code>.
     */
    public CustomerRecord setNation(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.nation</code>.
     */
    public Integer getNation() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.career</code>.
     */
    public CustomerRecord setCareer(Integer value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.career</code>.
     */
    public Integer getCareer() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.street</code>.
     */
    public CustomerRecord setStreet(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.street</code>.
     */
    public String getStreet() {
        return (String) get(9);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.ward</code>.
     */
    public CustomerRecord setWard(Integer value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.ward</code>.
     */
    public Integer getWard() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.district</code>.
     */
    public CustomerRecord setDistrict(Integer value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.district</code>.
     */
    public Integer getDistrict() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.province</code>.
     */
    public CustomerRecord setProvince(Integer value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.province</code>.
     */
    public Integer getProvince() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.description</code>.
     */
    public CustomerRecord setDescription(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.description</code>.
     */
    public String getDescription() {
        return (String) get(13);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.created_at</code>.
     */
    public CustomerRecord setCreatedAt(LocalDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>thangvtm_mcare.customer.modified_at</code>.
     */
    public CustomerRecord setModifiedAt(LocalDateTime value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>thangvtm_mcare.customer.modified_at</code>.
     */
    public LocalDateTime getModifiedAt() {
        return (LocalDateTime) get(15);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record16 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row16<String, String, Integer, LocalDate, String, String, String, Integer, Integer, String, Integer, Integer, Integer, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    @Override
    public Row16<String, String, Integer, LocalDate, String, String, String, Integer, Integer, String, Integer, Integer, Integer, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row16) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Customer.CUSTOMER.CIF;
    }

    @Override
    public Field<String> field2() {
        return Customer.CUSTOMER.FULL_NAME;
    }

    @Override
    public Field<Integer> field3() {
        return Customer.CUSTOMER.GENDER;
    }

    @Override
    public Field<LocalDate> field4() {
        return Customer.CUSTOMER.BOD;
    }

    @Override
    public Field<String> field5() {
        return Customer.CUSTOMER.CARD_ID;
    }

    @Override
    public Field<String> field6() {
        return Customer.CUSTOMER.PHONE;
    }

    @Override
    public Field<String> field7() {
        return Customer.CUSTOMER.EMAIL;
    }

    @Override
    public Field<Integer> field8() {
        return Customer.CUSTOMER.NATION;
    }

    @Override
    public Field<Integer> field9() {
        return Customer.CUSTOMER.CAREER;
    }

    @Override
    public Field<String> field10() {
        return Customer.CUSTOMER.STREET;
    }

    @Override
    public Field<Integer> field11() {
        return Customer.CUSTOMER.WARD;
    }

    @Override
    public Field<Integer> field12() {
        return Customer.CUSTOMER.DISTRICT;
    }

    @Override
    public Field<Integer> field13() {
        return Customer.CUSTOMER.PROVINCE;
    }

    @Override
    public Field<String> field14() {
        return Customer.CUSTOMER.DESCRIPTION;
    }

    @Override
    public Field<LocalDateTime> field15() {
        return Customer.CUSTOMER.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field16() {
        return Customer.CUSTOMER.MODIFIED_AT;
    }

    @Override
    public String component1() {
        return getCif();
    }

    @Override
    public String component2() {
        return getFullName();
    }

    @Override
    public Integer component3() {
        return getGender();
    }

    @Override
    public LocalDate component4() {
        return getBod();
    }

    @Override
    public String component5() {
        return getCardId();
    }

    @Override
    public String component6() {
        return getPhone();
    }

    @Override
    public String component7() {
        return getEmail();
    }

    @Override
    public Integer component8() {
        return getNation();
    }

    @Override
    public Integer component9() {
        return getCareer();
    }

    @Override
    public String component10() {
        return getStreet();
    }

    @Override
    public Integer component11() {
        return getWard();
    }

    @Override
    public Integer component12() {
        return getDistrict();
    }

    @Override
    public Integer component13() {
        return getProvince();
    }

    @Override
    public String component14() {
        return getDescription();
    }

    @Override
    public LocalDateTime component15() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component16() {
        return getModifiedAt();
    }

    @Override
    public String value1() {
        return getCif();
    }

    @Override
    public String value2() {
        return getFullName();
    }

    @Override
    public Integer value3() {
        return getGender();
    }

    @Override
    public LocalDate value4() {
        return getBod();
    }

    @Override
    public String value5() {
        return getCardId();
    }

    @Override
    public String value6() {
        return getPhone();
    }

    @Override
    public String value7() {
        return getEmail();
    }

    @Override
    public Integer value8() {
        return getNation();
    }

    @Override
    public Integer value9() {
        return getCareer();
    }

    @Override
    public String value10() {
        return getStreet();
    }

    @Override
    public Integer value11() {
        return getWard();
    }

    @Override
    public Integer value12() {
        return getDistrict();
    }

    @Override
    public Integer value13() {
        return getProvince();
    }

    @Override
    public String value14() {
        return getDescription();
    }

    @Override
    public LocalDateTime value15() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value16() {
        return getModifiedAt();
    }

    @Override
    public CustomerRecord value1(String value) {
        setCif(value);
        return this;
    }

    @Override
    public CustomerRecord value2(String value) {
        setFullName(value);
        return this;
    }

    @Override
    public CustomerRecord value3(Integer value) {
        setGender(value);
        return this;
    }

    @Override
    public CustomerRecord value4(LocalDate value) {
        setBod(value);
        return this;
    }

    @Override
    public CustomerRecord value5(String value) {
        setCardId(value);
        return this;
    }

    @Override
    public CustomerRecord value6(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public CustomerRecord value7(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public CustomerRecord value8(Integer value) {
        setNation(value);
        return this;
    }

    @Override
    public CustomerRecord value9(Integer value) {
        setCareer(value);
        return this;
    }

    @Override
    public CustomerRecord value10(String value) {
        setStreet(value);
        return this;
    }

    @Override
    public CustomerRecord value11(Integer value) {
        setWard(value);
        return this;
    }

    @Override
    public CustomerRecord value12(Integer value) {
        setDistrict(value);
        return this;
    }

    @Override
    public CustomerRecord value13(Integer value) {
        setProvince(value);
        return this;
    }

    @Override
    public CustomerRecord value14(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public CustomerRecord value15(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public CustomerRecord value16(LocalDateTime value) {
        setModifiedAt(value);
        return this;
    }

    @Override
    public CustomerRecord values(String value1, String value2, Integer value3, LocalDate value4, String value5, String value6, String value7, Integer value8, Integer value9, String value10, Integer value11, Integer value12, Integer value13, String value14, LocalDateTime value15, LocalDateTime value16) {
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
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerRecord
     */
    public CustomerRecord() {
        super(Customer.CUSTOMER);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(String cif, String fullName, Integer gender, LocalDate bod, String cardId, String phone, String email, Integer nation, Integer career, String street, Integer ward, Integer district, Integer province, String description, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(Customer.CUSTOMER);

        set(0, cif);
        set(1, fullName);
        set(2, gender);
        set(3, bod);
        set(4, cardId);
        set(5, phone);
        set(6, email);
        set(7, nation);
        set(8, career);
        set(9, street);
        set(10, ward);
        set(11, district);
        set(12, province);
        set(13, description);
        set(14, createdAt);
        set(15, modifiedAt);
    }
}