drop table if exists dog CASCADE;
create table dog (id integer AUTO_INCREMENT, age integer, breed varchar(255), is_male boolean, name varchar(255), primary key (id));