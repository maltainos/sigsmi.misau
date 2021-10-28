CREATE TABLE levels_roles (
	levels_id BIGINT NOT NULL,
	roles_id BIGINT NOT NULL,
	FOREIGN KEY(levels_id) REFERENCES levels(id),
	FOREIGN KEY(roles_id) REFERENCES roles(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;