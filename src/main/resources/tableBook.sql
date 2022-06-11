create table book
(
    book_id     serial
        constraint book_pk
            primary key,
    book_name   varchar(40) not null,
    author      varchar(40) not null,
    description text,
    pages       integer
);
