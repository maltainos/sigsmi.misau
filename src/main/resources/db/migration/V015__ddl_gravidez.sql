CREATE TABLE gravidezes (
	id BIGINT NOT NULL AUTO_INCREMENT,
	gravidez_id VARCHAR(30) NOT NULL,
	gravidez_status	VARCHAR(120) NOT NULL,
	aborto VARCHAR(255), 
    local_parto VARCHAR(255) NOT NULL,
	data_engravida DATETIME NOT NULL, 
    data_aborto DATETIME,
	data_parto DATETIME,
    paciente_id BIGINT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY(paciente_id) REFERENCES pacientes(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;