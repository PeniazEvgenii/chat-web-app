CREATE TABLE app.users
(
    id bigserial,
    login character varying(64) NOT NULL,
    password character varying(64) NOT NULL,
    name character varying(128) NOT NULL,
    birth_date date NOT NULL,
    registration_date date NOT NULL,
    role character varying(16) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (login)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;