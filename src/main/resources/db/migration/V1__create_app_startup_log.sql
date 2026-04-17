CREATE TABLE app_startup_log (
                                 id BIGSERIAL PRIMARY KEY,
                                 message VARCHAR(255) NOT NULL,
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);