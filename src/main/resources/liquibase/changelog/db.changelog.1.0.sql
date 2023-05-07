--liquibase formatted sql
--checksums: 1:0b1b0b0b0b0b0b0b0b0b0b0b0b0b0b0b

--changeset manguberdi:1
create table if not exists auth_user (
    id serial not null,
    username varchar(255) unique not null,
    password varchar(255) not null,
    enabled boolean not null DEFAULT true,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    role varchar(255) not null,
    account_non_expired boolean not null default true,
    account_non_locked boolean not null default true,
    credentials_non_expired boolean not null default true,
    created_at timestamp default now(),
    updated_at timestamp ,
    created_by_id int,
    updated_by_id int,
    deleted boolean not null default false,
    primary key (id)
);
--changeset manguberdi:2
create table if not exists city (
    id serial not null,
    name varchar(255) not null  unique ,
    temperature_celsius int not null default 0,
    temperature_fahrenheit int not null default 0,
    windSpeedKmh int not null default 0,
    windSpeedMph int not null default 0,
    windDirection varchar(255) not null default 'N',
    humidity int not null default 0,
    enabled boolean not null default true,
    created_at timestamp default now(),
    updated_at timestamp ,
    created_by_id int references auth_user(id),
    updated_by_id int references auth_user(id) ,
    deleted boolean not null default false,
    primary key (id)
);
--changeset manguberdi:3
INSERT INTO auth_user (username, password, first_name, last_name, role) VALUES('admin','admin','admin','admin','ADMIN');
--changeset manguberdi:4
create table if not exists subscription(
                                           id serial not null,
                                           city_id int not null references city(id),
                                           user_id int not null references auth_user(id),
                                           active boolean not null default true,
                                           created_at timestamp default now(),
                                           updated_at timestamp ,
                                           created_by_id int references auth_user(id),
                                           updated_by_id int references auth_user(id) ,
                                           deleted boolean not null default false,
                                           primary key (id)

)