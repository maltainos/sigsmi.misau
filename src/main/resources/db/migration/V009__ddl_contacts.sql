create table contactos (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	contacto_id VARCHAR(30) NOT NULL, 
	numero_telefone VARCHAR(13) NOT NULL, 
	paciente_id BIGINT(20) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(paciente_id) REFERENCES pacientes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;