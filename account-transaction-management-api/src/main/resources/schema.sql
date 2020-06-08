CREATE SEQUENCE IF NOT EXISTS CUSTOMER_ID_SEQ;
CREATE SEQUENCE IF NOT EXISTS ACCOUNT_ID_SEQ;
CREATE SEQUENCE IF NOT EXISTS TRANSACTION_ID_SEQ;

CREATE TABLE IF NOT EXISTS Customer (ID IDENTITY NOT NULL PRIMARY KEY,
                                     FIRST_NAME VARCHAR(35) NOT NULL,
                                     LAST_NAME VARCHAR(35) NOT NULL,
                                     SOCIAL_SECURITY_NUMBER INT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Account (ID IDENTITY NOT NULL PRIMARY KEY,
                                    CUSTOMER_ID INT NOT NULL,
                                    ACCOUNT_TYPE VARCHAR(10) NOT NULL,
                                    BALANCE DECIMAL(19,4) NOT NULL,
                                    CONSTRAINT FK_Account_Customer FOREIGN KEY (CUSTOMER_ID) REFERENCES Customer(ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Transaction (ID IDENTITY NOT NULL PRIMARY KEY,
                                        ACCOUNT_ID INT NOT NULL,
                                        AMOUNT DECIMAL(19,4) NOT NULL,
                                        TRANSACTION_DATE DATE NOT NULL,
                                        DESCRIPTION VARCHAR(255) NULL,
                                        CONSTRAINT FK_Transaction_Account FOREIGN KEY (ACCOUNT_ID) REFERENCES Account(ID) ON DELETE CASCADE
);
