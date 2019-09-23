create table if not exists vacancy
(
    id          serial primary key,
    name        varchar(256) not null,
    description text         not null,
    link        varchar(256) not null,
    created     bigint       not null
);

create table if not exists application_log
(
    id      serial primary key,
    name    varchar(256) not null,
    created bigint       not null
);
