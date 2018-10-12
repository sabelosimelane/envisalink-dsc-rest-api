--liquibase formatted sql
--changeset sabside:2018.06.28.adduser.sql

insert into users (name, email, cellphone, active, password, role, created)
values ('Sabelo Simelane', 'sabside@gmail.com', '0827876988', true, '995b54333a696db9a891e40250629c6d', 2, now());

