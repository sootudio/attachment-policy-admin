CREATE TABLE fixed_extension (
    extension VARCHAR(20) NOT NULL,
    blocked TINYINT(1) NOT NULL DEFAULT 0,
    updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (extension)
);

CREATE TABLE custom_extension (
    id BIGINT NOT NULL AUTO_INCREMENT,
    extension VARCHAR(20) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id),
    CONSTRAINT uk_custom_extension UNIQUE (extension)
);
