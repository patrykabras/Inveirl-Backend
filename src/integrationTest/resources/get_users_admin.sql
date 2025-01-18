--@formatter:off
SET REFERENTIAL_INTEGRITY = 0;
TRUNCATE TABLE users;
SET REFERENTIAL_INTEGRITY = 1;

INSERT INTO users (id, username, email, password, role, creation_date, modification_date) VALUES
('b2910f7d-ede9-472b-9fdf-4c82ee8c2619', 'Admin', 'admin@admin.com', 'secured password for admin', 'ADMIN', now(), now()),
('91a6d603-7075-461c-a68c-e1221d7c1829', 'User', 'user@user.com', 'secured password for user', 'USER', now(), now());