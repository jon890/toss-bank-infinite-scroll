alter table account_history
    add account_number varchar(255) null after uuid;

alter table account_history
    drop foreign key FKfomk97lcdrndxotso6yce14n2;

drop index FKfomk97lcdrndxotso6yce14n2 on account_history;

alter table account_history
    drop column account_id;
