CREATE TABLE if not exists users.users (
    id UUID,
    email VARCHAR(250),
    password VARCHAR(250),
    name VARCHAR(250),
    role VARCHAR(250),
    PRIMARY KEY (id)
    );