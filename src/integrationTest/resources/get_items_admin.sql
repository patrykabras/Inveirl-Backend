--@formatter:off
SET REFERENTIAL_INTEGRITY = 0;
TRUNCATE TABLE items;
SET REFERENTIAL_INTEGRITY = 1;

INSERT INTO items (id, name, description, type, bar_code, image_url, creation_date, modification_date) VALUES
('7bd55304-d00c-4c52-990e-8a6631aa9b08', 'Apple', 'Apple fruit', 'FOOD', '7423652082562', 'https://some-image-url.com/images/Apple.jpg', '2025-01-17 17:13:14.307','2025-01-17 17:13:14.307'),
('521eaed1-17ef-4c8f-93b4-cd0d280bdd80', 'Potato', 'Potato vegetable', 'FOOD', '6543652082123', 'https://some-image-url.com/images/Potato.jpg', '2025-01-18 17:13:14.307', '2025-01-18 17:13:14.307');