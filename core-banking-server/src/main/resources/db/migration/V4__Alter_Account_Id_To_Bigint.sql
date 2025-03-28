-- 기존 테이블 제거
DROP TABLE IF EXISTS account_history;
DROP TABLE IF EXISTS account;

-- 새로운 account 테이블 생성 
CREATE TABLE account (
    id             BIGINT AUTO_INCREMENT NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    username       VARCHAR(255) NOT NULL,
    balance        BIGINT,
    created_at     DATETIME(6),
    updated_at     DATETIME(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- 인덱스 생성
CREATE INDEX IDX66gkcp94endmotfwb8r4ocxm9 ON account (account_number);
ALTER TABLE account ADD CONSTRAINT UK66gkcp94endmotfwb8r4ocxm9 UNIQUE (account_number);

-- account_history 테이블 생성
CREATE TABLE account_history (
    id              BIGINT AUTO_INCREMENT NOT NULL,
    account_id      BIGINT NOT NULL,
    account_number  VARCHAR(255) NOT NULL,
    current_balance BIGINT,
    delta_balance   BIGINT,
    prev_balance    BIGINT,
    created_at      DATETIME(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- 외래 키 추가
ALTER TABLE account_history
    ADD CONSTRAINT FKfomk97lcdrndxotso6yce14n2
        FOREIGN KEY (account_id)
            REFERENCES account (id); 