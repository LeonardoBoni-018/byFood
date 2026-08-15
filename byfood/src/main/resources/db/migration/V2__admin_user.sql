CREATE TABLE admin_user (
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    updated_at    TIMESTAMP    NOT NULL
);

INSERT INTO admin_user (username, password_hash, created_at, updated_at)
VALUES ('admin', '$2a$10$46oO6DgJkJBYKweXJztPIezFZ9wlnWKE9DBQ/gJ/.tEjPkksoggBa', NOW(), NOW());
