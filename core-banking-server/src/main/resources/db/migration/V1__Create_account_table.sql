create table account
(
    id             varchar(255) not null,
    account_number varchar(255) not null,
    username       varchar(255) not null,
    balance        bigint,
    created_at     datetime(6),
    updated_at     datetime(6),
    primary key (id)
) engine = InnoDB;

create index IDX66gkcp94endmotfwb8r4ocxm9
    on account (account_number);

alter table account
    add constraint UK66gkcp94endmotfwb8r4ocxm9 unique (account_number);

create table account_history
(
    id              bigint       not null auto_increment,
    uuid            varchar(255),
    account_id      varchar(255) not null,
    current_balance bigint,
    delta_balance   bigint,
    prev_balance    bigint,
    created_at      datetime(6),
    primary key (id)
) engine = InnoDB;

alter table account_history
    add constraint FKfomk97lcdrndxotso6yce14n2
        foreign key (account_id)
            references account (id);