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