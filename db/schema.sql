create table cars(
    id serial primary key,
    model varchar(255),
    created timestamp
);

create table f_role (
                        id serial primary key,
                        name varchar(2000)
);

create table f_user (
                        id serial primary key,
                        name varchar(2000),
                        role_id int not null references f_role(id)
);

create table s_role (
                        id serial primary key,
                        name varchar(2000)
);

create table s_user (
                        id serial primary key,
                        name varchar(2000)
);

create table car_brand (
                        id serial primary key,
                        name varchar(2000)
);

create table car_model (
                        id serial primary key,
                        name varchar(2000)
);

create table engine (
    id serial primary key
);

create table car (
     id serial primary key,
     engine_id int not null references engine(id)
);

create table driver (
    id serial primary key
);

create table history_owner (
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);