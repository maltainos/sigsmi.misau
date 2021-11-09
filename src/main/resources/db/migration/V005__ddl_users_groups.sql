CREATE TABLE users_levels (
	users_id BIGINT NOT NULL,
	levels_id BIGINT NOT NULL,
	FOREIGN KEY(users_id) REFERENCES users(id),
	FOREIGN KEY(levels_id) REFERENCES levels(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;