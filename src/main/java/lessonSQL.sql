CREATE TABLE customer
(
    customer_id          BIGSERIAL PRIMARY KEY,
    first_name           varchar(40) NOT NULL,
    last_name            varchar(40) NOT NULL,
    date_of_registration date
);

CREATE TABLE wallet
(
    wallet_id      BIGSERIAL PRIMARY KEY,
    currency       varchar(5),
    amount         numeric,
    fk_customer_id integer REFERENCES customer (customer_id) NOT NULL,
    created        date
);

ALTER TABLE wallet
    ADD CONSTRAINT fk_wallet_customer
        FOREIGN KEY (fk_customer_id) REFERENCES customer (customer_id);

INSERT INTO customer
VALUES (1, 'Mark', 'Peters', '2019-05-09'),
       (2, 'Simona', 'Robinson', '2020-01-04'),
       (3, 'Martin', 'Grant', '2021-08-14');

INSERT INTO wallet
VALUES (1, 'USD', 1500, 2, '2022-03-17'),
       (2, 'EUR', 4500, 1, '2020-10-15'),
       (3, 'CAD', 300, 1, '2022-04-12'),
       (4, 'CAD', 300, 3, '2021-08-15');

SELECT first_name,
       last_name,
       currency,
       amount
FROM wallet AS w
JOIN customer AS c
    ON w.fk_customer_id = c.customer_id;