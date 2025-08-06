CREATE TABLE IF NOT EXISTS private_keys
(
    key_name  VARCHAR(255) PRIMARY KEY,
    key_value VARCHAR(255) NOT NULL
);

INSERT INTO private_keys (key_name, key_value)
VALUES ('card_key', 'U3h7gP9kTmVb2LdC6qWjXeRfZcYa1BtM'),
       ('jwt_signing_key', 'XrJt9cVhLpN2wYzqKmFgHaBeTdRsQxW3');
