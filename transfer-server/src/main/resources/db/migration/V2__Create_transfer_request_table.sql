CREATE TABLE IF NOT EXISTS transfer_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL,
    from_account_number VARCHAR(255) NOT NULL,
    to_account_number VARCHAR(255) NOT NULL,
    balance BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    requested_at DATETIME NOT NULL,
    completed_at DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 인덱스 추가
CREATE INDEX idx_uuid ON transfer_request (uuid);
CREATE INDEX idx_from_account_number ON transfer_request (from_account_number);
CREATE INDEX idx_to_account_number ON transfer_request (to_account_number);
CREATE INDEX idx_status ON transfer_request (status); 