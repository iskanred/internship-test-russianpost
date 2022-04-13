create table if not exists addresses(
    id uuid primary key ,
    plain_address text ,
    instruction text ,
    index integer not null ,    -- each address in Russia MUST have index
    region text not null ,      -- each address in Russia MUST have region
    area text ,
    place text not null ,       -- each address in Russia MUST have place
    district text not null ,    -- each address in Russia MUST have district
    street text not null ,      -- each address in Russia MUST have street
    house integer not null ,    -- each address in Russia MUST have house number
    letter text ,
    slash integer ,
    corpus integer ,
    building integer ,
    room text
);