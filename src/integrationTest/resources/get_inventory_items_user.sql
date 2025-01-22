--@formatter:off
SET REFERENTIAL_INTEGRITY = 0;
TRUNCATE TABLE users;
TRUNCATE TABLE items;
TRUNCATE TABLE users_inventories;
SET REFERENTIAL_INTEGRITY = 1;

INSERT INTO users (id, username, email, password, role, creation_date, modification_date) VALUES
('12ce85c9-ac6c-47e6-a3f5-6ab263e04dee', 'Admin', 'admin@admin.com', 'password', 'ADMIN', now(), now()),
('f60d259b-924a-43b7-9b6e-6eab9479ee3e', 'User', 'user@user.com', 'password', 'USER', now(), now());

INSERT INTO items (id, name, description, type, bar_code, image_url, creation_date, modification_date) VALUES
('2debcd49-e660-46c6-ae58-778218852fb5', 'Apple', 'Apple fruit', 'FOOD', '7423652082562', 'https://some-image-url.com/images/Apple.jpg', now(), now()),
('7bd55304-d00c-4c52-990e-8a6631aa9b08', 'Potato', 'Potato vegetable', 'FOOD', '6543652082123', 'https://some-image-url.com/images/Potato.jpg', now(), now());

INSERT INTO users_inventories (id, user_id, item_id, quantity_type, quantity, is_exists, expiration_date, creation_date, modification_date) VALUES
('727eb6e8-3c30-4871-aba6-66202d119c49', '12ce85c9-ac6c-47e6-a3f5-6ab263e04dee', '2debcd49-e660-46c6-ae58-778218852fb5', 'QUANTITATIVE', 1.0, true, NULL, now(), now()),
('552096a0-ed28-4700-8e61-a4916615dd0a', '12ce85c9-ac6c-47e6-a3f5-6ab263e04dee', '7bd55304-d00c-4c52-990e-8a6631aa9b08', 'QUANTITATIVE', 2.0, true, NULL, now(), now()),
('54d2567d-5b9a-4194-b1e4-e6266a11dffd', 'f60d259b-924a-43b7-9b6e-6eab9479ee3e', '2debcd49-e660-46c6-ae58-778218852fb5', 'KILOGRAM', 2.0, true, NULL, now(), now());