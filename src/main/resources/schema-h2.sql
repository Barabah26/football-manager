CREATE TABLE IF NOT EXISTS team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL,
    commission_percentage DOUBLE NOT NULL
    );

CREATE TABLE IF NOT EXISTS player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    experience_months INT NOT NULL,
    age INT NOT NULL,
    team_id BIGINT,
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES team (id) ON DELETE CASCADE
    );
