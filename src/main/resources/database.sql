CREATE DATABASE IF NOT EXISTS thangvtm_mcare;
USE thangvtm_mcare;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    cif             VARCHAR(255)    PRIMARY KEY,
    email           VARCHAR(255)                                NOT NULL,
    password        VARCHAR(255)                                NOT NULL,
    salt            VARCHAR(255)                                NOT NULL,
    full_name       VARCHAR(255)                                NOT NULL,
    gender          INT             DEFAULT 0,
    bod             DATE                                        NOT NULL,
    address         VARCHAR(255),
    card_id         VARCHAR(12)                                 NOT NULL,
    role            INT                                         NOT NULL,
    description     VARCHAR(255),
    last_login      TIMESTAMP       DEFAULT current_timestamp,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
    cif             VARCHAR(255)    PRIMARY KEY,
    email           VARCHAR(255)                                NOT NULL,
    password        VARCHAR(255)                                NOT NULL,
    salt            VARCHAR(255)                                NOT NULL,
    full_name       VARCHAR(255)                                NOT NULL,
    description     VARCHAR(255),
    last_login      TIMESTAMP       DEFAULT current_timestamp,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS medicine;
CREATE TABLE medicine
(
    medicine_id     VARCHAR(255)    PRIMARY KEY,
    medicine_name   VARCHAR(255)                                NOT NULL,
    source          VARCHAR(255)                                NOT NULL,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS service;
CREATE TABLE service
(
    service_id      VARCHAR(255)    PRIMARY KEY,
    service_name    VARCHAR(255)                                NOT NULL,
    price           DOUBLE                                      NOT NULL,
    description     VARCHAR(255),
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS bill;
CREATE TABLE bill
(
    bill_id         VARCHAR(255)    PRIMARY KEY,
    bill_type       INT                                         NOT NULL,
    bill_date       TIMESTAMP                                   NOT NULL,
    sub_price       DOUBLE                                      NOT NULL,
    is_vat          BIT                                         NOT NULL,
    vat_rate        INT             DEFAULT 0                   NOT NULL,
    vat_price       DOUBLE                                      NOT NULL,
    total_price     DOUBLE                                      NOT NULL,
    customer_id     VARCHAR(255)                                NOT NULL,
    create_by       VARCHAR(255)                                NOT NULL,
    description     VARCHAR(255),
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS service_bill;
CREATE TABLE service_bill
(
    id              INT             AUTO_INCREMENT PRIMARY KEY,
    bill_id         VARCHAR(255)                                NOT NULL,
    service_id      VARCHAR(255)                                NOT NULL,
    from_date       DATE                                        NOT NULL,
    to_date         DATE                                        NOT NULL,
    quantity        INT                                         NOT NULL,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS medicine_bill;
CREATE TABLE medicine_bill
(
    id              INT             AUTO_INCREMENT PRIMARY KEY,
    bill_id         VARCHAR(255)                                NOT NULL,
    inventory_id    VARCHAR(255)                                NOT NULL,
    medicine_id     VARCHAR(255)                                NOT NULL,
    amount          INT                                         NOT NULL,
    cal_unit        INT                                         NOT NULL,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS inventory; -- thong tin kho thuoc
CREATE TABLE inventory
(
    inventory_id    VARCHAR(255)    PRIMARY KEY,
    medicine_id     VARCHAR(255)                                NOT NULL,
    lot_id          VARCHAR(255)                                NOT NULL,
    amount          INT                                         NOT NULL,
    import_price    DOUBLE                                      NOT NULL,
    sell_price      DOUBLE                                      NOT NULL,
    cal_unit        INT                                         NOT NULL,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS user_token;
CREATE TABLE user_token
(
    id              INT             AUTO_INCREMENT PRIMARY KEY,
    cif             VARCHAR(255)                                NOT NULL,
    token           VARCHAR(1024)                               NOT NULL,
    token_type      INT                                         NOT NULL,
    status          INT                                         NOT NULL,
    expire_date     TIMESTAMP                                   NOT NULL,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS register_examination; -- so thu tu kham benh
CREATE TABLE register_examination
(
    id              INT             AUTO_INCREMENT PRIMARY KEY,
    random_number   CHAR(3)                                     NOT NULL,
    full_name       VARCHAR(255)                                NOT NULL,
    status          INT                                         NOT NULL,
    email           VARCHAR(255)                                NOT NULL,
    phone           CHAR(10)                                    NOT NULL,
    from_date       TIMESTAMP                                   NOT NULL,
    to_date         TIMESTAMP                                   NOT NULL,
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    cif             VARCHAR(255)    PRIMARY KEY,
    full_name       VARCHAR(255)                                NOT NULL,
    gender          INT,
    bod             DATE,
    card_id         CHAR(12),
    phone           CHAR(10),
    email           VARCHAR(255),
    nation	        INT,
    career          INT,
    street	        VARCHAR(255),
    ward       	    INT,
    district	      INT,
    province        INT,
    description     VARCHAR(255),
    created_at      TIMESTAMP       DEFAULT current_timestamp,
    modified_at     TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS medical_report; -- ho so benh an
CREATE TABLE medical_report
(
    medical_report_id   VARCHAR(255)    PRIMARY KEY,
    cif                 VARCHAR(255)                                NOT NULL,
    blood_group		      INT,
    created_at          TIMESTAMP       DEFAULT current_timestamp,
    modified_at         TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS health_maintenance; -- cac chi so benh nhan co ban
CREATE TABLE health_maintenance
(
    id              	  INT             AUTO_INCREMENT PRIMARY KEY,
    examine_id 		      VARCHAR(255)    			                      NOT NULL,
    blood_pressure_min  FLOAT,
    blood_pressure_max  FLOAT,
    blood_sugar  	      FLOAT,
    cholesterol		      FLOAT,
    temperature		      FLOAT,
    weight		          FLOAT,
    height		          FLOAT,
    created_at          TIMESTAMP       DEFAULT current_timestamp,
    modified_at         TIMESTAMP       DEFAULT current_timestamp
);


DROP TABLE IF EXISTS examine_info; -- kham benh
CREATE TABLE examine_info
(
    examine_id 		      VARCHAR(255)   PRIMARY KEY,
    status 		          INT					                                NOT NULL,
    attend_doctor     	VARCHAR(255),
    diagnose		        VARCHAR(1024),
    check_in            TIMESTAMP,
    check_out           TIMESTAMP,
    created_at          TIMESTAMP       DEFAULT current_timestamp,
    modified_at         TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS medical_history; -- lich su benh
CREATE TABLE medical_history
(
    id              	  INT             AUTO_INCREMENT PRIMARY KEY,
    examine_id 		      VARCHAR(255)    			                      NOT NULL,
    pathological	      VARCHAR(1024)			                          NOT NULL,
    status		          INT					                                NOT NULL,
    note                TEXT,
    created_at          TIMESTAMP       DEFAULT current_timestamp,
    modified_at         TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS prescription; --lich su don thuoc
CREATE TABLE prescription
(
    id              	  INT             AUTO_INCREMENT PRIMARY KEY,
    examine_id 		      VARCHAR(255)    			                      NOT NULL,
    medicine_id		      VARCHAR(255)			                          NOT NULL,
    amount		          INT					                                NOT NULL,
    cal_unit        	  INT                                         NOT NULL,
    description     	  VARCHAR(255),
    created_at          TIMESTAMP       DEFAULT current_timestamp,
    modified_at         TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS medical_report_detail;
CREATE TABLE medical_report_detail
(
    id              	  INT             AUTO_INCREMENT PRIMARY KEY,
    medical_report_id   VARCHAR(255)    			                      NOT NULL,
    examine_id 		      VARCHAR(255)    			                      NOT NULL,
    status              INT             DEFAULT 0                   NOT NULL,
    created_at          TIMESTAMP       DEFAULT current_timestamp,
    modified_at         TIMESTAMP       DEFAULT current_timestamp
);

DROP TABLE IF EXISTS data_list; -- luu tru list data cua combobox
CREATE TABLE data_list
(
    id            INT           AUTO_INCREMENT PRIMARY KEY,
    data_key      VARCHAR(255)    			                            NOT NULL,
    data_value		VARCHAR(255)    			                            NOT NULL,
    label		      VARCHAR(255)    			                            NOT NULL,
    description		VARCHAR(255)    			                            NOT NULL
);

DROP TABLE IF EXISTS content_layout; -- luu tru format mail, content,...
CREATE TABLE content_layout
(
    content_key		VARCHAR(255)	PRIMARY KEY,
    title         VARCHAR(255)    			                            NOT NULL,
    content		    TEXT    			                                    NOT NULL,
    description		VARCHAR(255)    			                            NOT NULL
);

DROP TABLE IF EXISTS province;
CREATE TABLE province
(
    id            INT           PRIMARY KEY,
    name          VARCHAR(50)    			                            NOT NULL,
    code          VARCHAR(20)    			                            NOT NULL
);

DROP TABLE IF EXISTS district;
CREATE TABLE District
(
    id            INT           PRIMARY KEY,
    name          VARCHAR(50)    			                            NOT NULL,
    prefix        VARCHAR(20)    			                            NOT NULL,
    province_id   INT    			                                    NOT NULL
);

DROP TABLE IF EXISTS ward;
CREATE TABLE ward
(
    id            INT           PRIMARY KEY,
    name          VARCHAR(50)    			                            NOT NULL,
    prefix        VARCHAR(20)    			                            NOT NULL,
    district_id   INT    			                                    NOT NULL
);