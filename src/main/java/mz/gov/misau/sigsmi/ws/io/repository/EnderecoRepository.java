package mz.gov.misau.sigsmi.ws.io.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.gov.misau.sigsmi.ws.io.model.EnderecoEntity;
import mz.gov.misau.sigsmi.ws.io.model.PacienteEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
	
	public List<EnderecoEntity> findByPaciente(PacienteEntity paciente);
	public Optional<EnderecoEntity> findByEnderecoId(String enderecoId);
}
