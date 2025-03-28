
-- auto-generated definition
create table account_history
(
    id              bigint auto_increment
        primary key,
    uuid            varchar(255) null,
    account_number  varchar(255) not null,
    current_balance bigint       null,
    delta_balance   bigint       null,
    prev_balance    bigint       null,
    created_at      datetime(6)  null
) engine = InnoDB;