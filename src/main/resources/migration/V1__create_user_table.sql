CREATE TABLE users(
    id UUID PRIMARY KEY,
    creation_date TIMESTAMPTZ NOT NULL,
    user_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    password_hash VARCHAR NOT NULL,
    role VARCHAR NOT NULL
)