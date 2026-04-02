CREATE TABLE users(
    id UUID PRIMARY KEY,
    creation_date TIMESTAMPTZ NOT NULL,
    user_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    password_hash VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    coins INT NOT NULL
);

CREATE TABLE cards(
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    description TEXT NOT NULL,
    rarity VARCHAR NOT NULL,
    icon_url VARCHAR
);

CREATE TABLE cases(
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    common_chance INT NOT NULL,
    uncommon_chance INT NOT NULL,
    rare_chance INT NOT NULL,
    epic_chance INT NOT NULL,
    legendary_chance INT NOT NULL,
    mythic_chance INT NOT NULL,
    eternal_chance INT NOT NULL
);

CREATE TABLE refresh_tokens(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    token VARCHAR NOT NULL,
    expires_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE user_cards(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    card_id UUID NOT NULL REFERENCES cards(id),
    amount INT NOT NULL
);