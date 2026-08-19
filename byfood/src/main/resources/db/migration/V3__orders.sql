CREATE TABLE customer_order (
    id               BIGSERIAL PRIMARY KEY,
    customer_name    VARCHAR(120) NOT NULL,
    customer_phone   VARCHAR(30)  NOT NULL,
    customer_address VARCHAR(255) NOT NULL,
    status           VARCHAR(20)  NOT NULL,
    total            NUMERIC(10, 2) NOT NULL,
    created_at       TIMESTAMP    NOT NULL,
    updated_at       TIMESTAMP    NOT NULL
);

CREATE TABLE order_item (
    id         BIGSERIAL PRIMARY KEY,
    order_id   BIGINT        NOT NULL REFERENCES customer_order (id) ON DELETE CASCADE,
    item_name  VARCHAR(150)  NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL,
    quantity   INT           NOT NULL,
    created_at TIMESTAMP     NOT NULL,
    updated_at TIMESTAMP     NOT NULL
);

CREATE INDEX idx_customer_order_status   ON customer_order (status);
CREATE INDEX idx_customer_order_created  ON customer_order (created_at);
CREATE INDEX idx_order_item_order        ON order_item (order_id);