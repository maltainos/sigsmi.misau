CREATE TABLE recem_nascido(
	id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
	recem_nascido_id VARCHAR(30) NOT NULL UNIQUE,
	estado_geral TEXT NOT  NULL,
	dispneia VARCHAR(255),
	coloracao VARCHAR(255) NOT NULL,
	icteria VARCHAR(255),
	temperatura VARCHAR(20),
	chupa_bem TINYINT NOT NULL,
	estado_coto_umbilical VARCHAR(255),
	irritabilidade TINYINT NOT NULL,
	fortenala VARCHAR(75),
	tem_mal_formacao TINYINT NOT NULL,
	mal_formacao_nome VARCHAR(50),
	gravidez_id BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(gravidez_id) REFERENCES gravidezes(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;






