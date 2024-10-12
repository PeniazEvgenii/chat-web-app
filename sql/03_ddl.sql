CREATE TABLE app.messages
(
    id uuid,
    create_at timestamp with time zone NOT NULL,
    user_id_from uuid NOT NULL,
    user_id_to uuid NOT NULL,
    body text,
    CONSTRAINT pk_messages PRIMARY KEY (id),
    CONSTRAINT fk_user_from FOREIGN KEY (user_id_from) REFERENCES app.users (id),
    CONSTRAINT fk_user_to FOREIGN KEY (user_id_to) REFERENCES app.users (id)
);

ALTER TABLE IF EXISTS app.messages
    OWNER to postgres;