create table enderecos (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	endereco_id VARCHAR(30) NOT NULL, 
	avenida VARCHAR(50), 
	telefone_casa VARCHAR(15), 
	districto VARCHAR(30) NOT NULL, 
	nr_casa VARCHAR(15), 
	residencia VARCHAR(30) NOT NULL, 
	rua VARCHAR(30), 
	bairro VARCHAR(30),
	paciente_id BIGINT(20) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(paciente_id) REFERENCES pacientes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;