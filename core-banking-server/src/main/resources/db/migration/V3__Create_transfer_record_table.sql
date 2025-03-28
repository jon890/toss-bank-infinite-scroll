CREATE TABLE IF NOT EXISTS transfer_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transfer_id VARCHAR(255) NOT NULL,
    from_account_number VARCHAR(255) NOT NULL,
    to_account_number VARCHAR(255) NOT NULL,
    balance BIGINT NOT NULL,
    requested_at DATETIME NOT NULL,
    processed_at DATETIME,
    status VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 인덱스 추가
CREATE INDEX idx_transfer_id ON transfer_record (transfer_id);
CREATE INDEX idx_from_account_number ON transfer_record (from_account_number);
CREATE INDEX idx_to_account_number ON transfer_record (to_account_number);
CREATE INDEX idx_status ON transfer_record (status); 