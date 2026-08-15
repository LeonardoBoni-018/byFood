CREATE TABLE restaurant (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(120) NOT NULL,
    description     VARCHAR(500),
    address         VARCHAR(255) NOT NULL,
    phone           VARCHAR(30) NOT NULL,
    whatsapp_number VARCHAR(30) NOT NULL,
    opening_hours   VARCHAR(255),
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL
);

CREATE TABLE menu_item (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description VARCHAR(500),
    price       NUMERIC(10, 2) NOT NULL,
    category    VARCHAR(80) NOT NULL,
    image_url   VARCHAR(500),
    available   BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL
);

CREATE INDEX idx_menu_item_category  ON menu_item (category);
CREATE INDEX idx_menu_item_available ON menu_item (available);

INSERT INTO restaurant (name, description, address, phone, whatsapp_number, opening_hours, created_at, updated_at)
VALUES ('My Restaurant', 'Best food in town', 'Rua Exemplo, 123', '+5511999999999',
        '+5511988888888', 'Mon-Fri 11:00-22:00', NOW(), NOW());
