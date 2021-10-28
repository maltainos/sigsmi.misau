CREATE TABLE pacientes (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	paciente_id VARCHAR(30) not null,
	primeiro_nome VARCHAR(30) NOT NULL, 
	sobre_nome VARCHAR(30) NOT NULL, 
	anos_idade TINYINT , 
	grupo_sanguineo VARCHAR(255), 
	data_nascimento DATE, 
	nome_mae VARCHAR(35) NOT NULL, 
	nome_pai VARCHAR(35) NOT NULL, 
	ocupacao_mae VARCHAR(35), 
	ocupacao_pai VARCHAR(35), 
	estado_civil VARCHAR(40) NOT NULL,  
	nome_pessoa_referencia VARCHAR(30) NOT NULL, 
	telefone_emergencia VARCHAR(13) NOT NULL,
	cargo VARCHAR(255), 
	local_trabalho VARCHAR(255), 
	profissao VARCHAR(255), 
	criado_em DATETIME NOT NULL,
	atualizado_em DATETIME,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	
	
	
	
	