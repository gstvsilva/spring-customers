create table addresses(

    id bigint not null auto_increment,
    customer_id bigint not null,
    street varchar(100) null,
    numeral varchar(10) not null,
    complement varchar(100) null,
    neighborhood varchar(100) null,
    city varchar(100) null,
    state varchar(100) null,
    country varchar(100) null,
    zipcode varchar(8) not null,

    primary key(id)

);