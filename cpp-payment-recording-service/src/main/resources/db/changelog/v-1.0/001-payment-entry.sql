CREATE TABLE cpp_payment_schema.payments
(
    id                  UUID PRIMARY KEY,
    amount              NUMERIC(19, 4) NOT NULL,
    currency            CHAR(3)        NOT NULL,
    merchant_id         UUID           NOT NULL,
    email               VARCHAR(255)   NOT NULL,
    transaction_id      UUID           NOT NULL UNIQUE,
    bank_transaction_id UUID           NOT NULL UNIQUE,
    is_approved         BOOLEAN        NOT NULL,
    reason              TEXT,
    created_at          TIMESTAMP      NOT NULL DEFAULT now()
);