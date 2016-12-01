drop database if exists users;
create database users;
use users;

create table users(
  username char(20),
  password char(20),
  email char(30)
);

insert into users(username, password, email) values ('Ricardo', 'olabebe', 'ricardo@live.com');