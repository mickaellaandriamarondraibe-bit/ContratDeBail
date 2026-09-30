CREATE TABLE IF NOT EXISTS bien_photo (
    bien_id BIGINT NOT NULL REFERENCES bien(id),
    position INTEGER NOT NULL,
    url TEXT NOT NULL,
    PRIMARY KEY (bien_id, position)
);
