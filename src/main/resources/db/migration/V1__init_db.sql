---While this database schema might be good for the given challenges, it will be difficult when two or more users share  the same address
--Better to create a mapping table where user and address id are the only attributes in the table, this would be better for long run
--Since User is to Address relationship is one to one , but address to user relationship is one to many
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
    addressId bigint not null,
    foreign key (addressId) references Address(id)
);
--overwritten the create table since only 2 records
--As alter query requires update query also for user table
insert into User
    (id, firstName, lastName, username, password, addressId)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123',1) ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234',2);