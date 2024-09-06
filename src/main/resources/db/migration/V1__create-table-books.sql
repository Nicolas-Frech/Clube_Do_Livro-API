create table books(

    id bigint not null auto_increment,
    title varchar(100) not null,
    author varchar(100) not null,
    synopsis varchar(2500) not null,
    release_date varchar(50) not null,
    genre varchar(100) not null,
    price varchar(100) not null,

    primary key(id)

);