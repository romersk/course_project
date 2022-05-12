CREATE SCHEMA IF NOT EXISTS evdokimov_r_d;

USE evdokimov_r_d;

CREATE TABLE audit
(
    id         bigint auto_increment
        primary key,
    date_end   datetime     null,
    date_start datetime     null,
    plan       varchar(255) null
);

CREATE TABLE auth_user
(
    users_id       bigint not null,
    authorities_id bigint not null,
    foreign key (users_id ) references user.id;
);

CREATE INDEX FK2vsc3wcrgt5ec4hnkpce4k5t8
    on auth_user (authorities_id);

CREATE INDEX FKgsq7k7rn2mc7n36frfsue6vua
    on auth_user (users_id);

CREATE  TABLE authority
(
    id               bigint auto_increment
        primary key,
    role_code        varchar(255) null,
    role_description varchar(255) null
);

CREATE TABLE document
(
    id         bigint auto_increment
        primary key,
    content    varchar(255) null,
    name       varchar(255) null,
    process_id bigint       null
);

CREATE INDEX FK1ne9cp7p7elpeskwec4m9l85p
    on document (process_id);

CREATE TABLE process
(
    id              bigint auto_increment
        primary key,
    name            varchar(255) null,
    recommendations varchar(255) null,
    start_id        bigint       null,
    status          varchar(255) null,
    user_id         bigint       null
);
