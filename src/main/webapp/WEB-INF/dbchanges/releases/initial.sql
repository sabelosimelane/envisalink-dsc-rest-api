--liquibase formatted sql
--changeset sabside:initial

begin;
create table users (
    id serial primary key not null,
    name text not null,
    email text not null,
    cellphone text not null,
    active boolean not null default false,
    password text,
    role int not null,
    lastlogin timestamp with time zone,
    created timestamp with time zone not null
);
alter table users add constraint user_email_unique unique (email);

CREATE INDEX user_email_i0
ON users (email, active);

end;
--rollback