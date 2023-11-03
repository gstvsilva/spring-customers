create table users(

    id bigint not null auto_increment,
    login varchar(100) not null,
    password varchar(255) not null,

    primary key(id)

);

insert into users (login, password) values ('ana@gmail.com', '$2a$12$avNV7h7qqFLMgyly9RSN8eexilhy5N/gKt./p948r8gkBwgDBydwu');