CREATE DATABASE team_1
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

\c team_1;

CREATE SCHEMA user_messages
    AUTHORIZATION postgres;

CREATE TABLE user_messages.users
(
    id uuid,
    username character varying(30) NOT NULL,
    password character varying(30) NOT NULL,
    full_name character varying(30) NOT NULL,
    birthday date NOT NULL,
    registration_date date NOT NULL,
    role character varying NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

ALTER TABLE IF EXISTS user_messages.users
    OWNER to postgres;

CREATE TABLE user_messages.messages
(
    id uuid,
    user_id_from uuid NOT NULL,
    user_id_to uuid NOT NULL,
    sent_date date NOT NULL,
    message_text text,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id_from)
        REFERENCES user_messages.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (user_id_to)
        REFERENCES user_messages.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS user_messages.messages
    OWNER to postgres;

INSERT INTO user_messages.users(
	id, username, password, full_name, birthday, registration_date, role
	)
VALUES (gen_random_uuid (), 'Admin', 'Admin', 'Admin A.A.', '2000-01-01', '2023-10-03', 'ADMIN');