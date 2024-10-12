CREATE TABLE app.users
(
    id uuid,
    login character varying(64) NOT NULL,
    password character varying(64) NOT NULL,
    "name" character varying NOT NULL,
    birth_date date NOT NULL,
    registration_at timestamp NOT NULL,
    update_at timestamp NOT NULL,
    role character varying(32) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id),
    UNIQUE (login)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;