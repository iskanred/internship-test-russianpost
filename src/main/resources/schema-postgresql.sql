create table if not exists addresses(
    id uuid primary key ,
    plain_address text not null ,
    instruction text ,
    index varchar(9) ,
    region varchar(200) ,
    area varchar(200) ,
    place varchar(200) ,
    district varchar(200) ,
    street varchar(200) ,
    house varchar(60) ,
    letter varchar(2) ,
    slash varchar(8) ,
    corpus varchar(8) ,
    building varchar(8) ,
    room varchar(60)
);