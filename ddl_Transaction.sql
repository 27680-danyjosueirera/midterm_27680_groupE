CREATE TABLE transactions
(
    transaction_id   UUID NOT NULL,
    account_id       UUID,
    amount           DOUBLE PRECISION,
    transaction_type VARCHAR(255),
    CONSTRAINT pk_transactions PRIMARY KEY (transaction_id)
);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (account_id);
CREATE TABLE transactions
(
    transaction_id   UUID NOT NULL,
    account_id       UUID,
    amount           DOUBLE PRECISION,
    transaction_type VARCHAR(255),
    CONSTRAINT pk_transactions PRIMARY KEY (transaction_id)
);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (account_id);