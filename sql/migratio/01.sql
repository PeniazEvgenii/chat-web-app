ALTER TABLE IF EXISTS app.messages
    ADD COLUMN update_at timestamp without time zone;

UPDATE app.messages
    SET update_at = now() ;

ALTER TABLE app.messages
    ALTER COLUMN update_at SET NOT NULL;
