DROP TABLE IF EXISTS item;
-- DROP TABLE IF EXISTS order_product;
-- DROP TABLE IF EXISTS product;
-- DROP TABLE IF EXISTS order;


CREATE TABLE item
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255),
    amount   INTEGER,
    price    BIGINT,
    rows     VARCHAR(255),
    "column" INTEGER,
    CONSTRAINT pk_item PRIMARY KEY (id)
);


ALTER TABLE item
    OWNER TO og;

--
-- CREATE TABLE item
-- (
--     id     INT8 NOT NULL,
--     amount INT4,
--     column INT4,
--     name   VARCHAR(255),
--     price  INT8,
--     rows   VARCHAR(255),
--     PRIMARY KEY (id)
-- );
--
-- CREATE TABLE session
-- (
--     id               UUID NOT NULL,
--     deposited        BIGINT,
--     datetime_started TIMESTAMP WITHOUT TIME ZONE,
--     datetime_stopped TIMESTAMP WITHOUT TIME ZONE,
--     item_id          BIGINT,
--     CONSTRAINT pk_session PRIMARY KEY (id)
-- );
--
-- ALTER TABLE session
--     ADD CONSTRAINT FK_SESSION_ON_ITEM FOREIGN KEY (item_id) REFERENCES item (id);