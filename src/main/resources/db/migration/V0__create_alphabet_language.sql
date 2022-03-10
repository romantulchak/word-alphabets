CREATE TABLE IF NOT EXISTS language
(
    id   bigserial   not null primary key,
    code varchar(3)  not null,
    name varchar(35) not null
);
CREATE TABLE IF NOT EXISTS alphabet
(
    id          bigserial  not null primary key,
    code        varchar(3) not null,
    language_id bigint     not null references language
);

CREATE TABLE IF NOT EXISTS alphabet_letters
(
    alphabet_id       bigint  not null references alphabet,
    letter_lower_case char(1) not null,
    letter_upper_case char(1) not null,
    ascii_code        int     not null
);