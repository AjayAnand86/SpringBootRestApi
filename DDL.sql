CREATE TABLE CUSTOMER_DETAILS (
		ACCOUNT_NUMBER NUMBER(20 , 0) NOT NULL,
		CUSTOMER_ID VARCHAR2(50) NOT NULL,
		CUSTOMER_NAME VARCHAR2(100),
		CUSTOMER_ROLE VARCHAR2(1),
		CUSTOMER_STATUS VARCHAR2(1),
		AVAILABLE_BALANCE NUMBER(20 , 2)
	);
update CUSTOMER_DETAILS set CURR_CDE ='EUR';
CREATE UNIQUE INDEX SYS_C007001 ON CUSTOMER_DETAILS (CUSTOMER_ID ASC);

ALTER TABLE CUSTOMER_DETAILS ADD CONSTRAINT SYS_C007001 PRIMARY KEY (CUSTOMER_ID);

ALTER TABLE CUSTOMER_DETAILS ADD CURR_CDE VARCHAR(3);

CREATE TABLE CUSTOMER_ACCOUNT_DETAILS (
	
		CUSTOMER_ID VARCHAR2(50) references CUSTOMER_DETAILS(CUSTOMER_ID) VARCHAR2(50),
		ACCOUNT_NUMBER VARCHAR2(100),
		BALANCE NUMBER(20 , 2),
		TRANSACTION_MODE VARCHAR2(1),
		TRANSACTION_DATE TIMESTAMP
	);

CREATE UNIQUE INDEX SYS_C007002 ON CUSTOMER_ACCOUNT_DETAILS (CUSTOMER_ID ASC);
