create table quotes
(
    id        integer not null
        constraint quotes_pk
            primary key,
    user_name varchar not null,
    quote     varchar not null
);

alter table quotes
    owner to "dmsApp";

