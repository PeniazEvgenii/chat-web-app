INSERT INTO app.users
(id, login, password, name, birth_date, registration_at, update_at, role)
VALUES
(gen_random_uuid (),'admin', 'admin', 'petrovich', '1990-07-31', now(), now() ,'ADMIN');