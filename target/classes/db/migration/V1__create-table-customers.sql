create table customers(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,

    primary key(id)

);