DROP DATABASE IF EXISTS news_portal;
CREATE DATABASE news_portal;
\c news_portal;

CREATE TABLE app_user (id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       email varchar NOT NULL UNIQUE,
                       password varchar NOT NULL);

CREATE TABLE category (id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       category_name varchar NOT NULL,
                       parent_id bigint DEFAULT NULL REFERENCES category (id));

CREATE TABLE article (id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      headline varchar NOT NULL,
                      content varchar NOT NULL,
                      created_date timestamp NOT NULL,
                      updated_date timestamp DEFAULT NULL,
                      user_id bigint NOT NULL REFERENCES app_user (id),
                      category_id bigint NOT NULL REFERENCES category (id));
