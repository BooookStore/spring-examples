INSERT INTO users (id, username, password, emailaddress)
VALUES (1, 'bob', '12345', 'bob@sample.com')
ON CONFLICT DO NOTHING;

INSERT INTO roles (id, name)
VALUES (1, 'ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO users (id, username, password, emailaddress)
VALUES (2, 'alice', '12345', 'alice@sample.com')
ON CONFLICT DO NOTHING;

INSERT INTO roles (id, name)
VALUES (2, 'NORMAL')
ON CONFLICT DO NOTHING;
