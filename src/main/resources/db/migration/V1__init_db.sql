create table Address
(
    id        bigint  auto_increment not null primary key,
    address1 varchar(255)        not null,
    address2 varchar(255),
    city  varchar(255)        not null,
    state  varchar(255) unique not null,
    postal  varchar(255)        not null
);

insert into Address (address1,address2, city, state, postal) values('462 Magnolia St','Apt 14', 'Houston', 'Texas','77001');
insert into Address (address1, city, state, postal) values('3782 Menlo Ave','Los Angeles', 'California','90001');


create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null, -- WHAT!? NOT ENCRYPTED!? ;-)
    address_id bigint not null,
    foreign key (address_id) references Address(id)
);

insert into User
    (id, firstName, lastName, username, password, address_id)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123',1) ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234',2);