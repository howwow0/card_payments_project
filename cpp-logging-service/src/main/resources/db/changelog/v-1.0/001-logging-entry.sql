CREATE TABLE IF NOT EXISTS cpp_logging_schema.logs
(
    id        BIGSERIAL PRIMARY KEY,
    level     VARCHAR(10)   NOT NULL,
    message   VARCHAR(1000) NOT NULL,
    service   VARCHAR(100)  NOT NULL,
    timestamp TIMESTAMPTZ     NOT NULL,
    trace_id  VARCHAR(100)  NOT NULL
);