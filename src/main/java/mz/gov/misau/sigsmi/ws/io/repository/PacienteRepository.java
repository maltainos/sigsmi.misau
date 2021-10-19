package mz.gov.misau.sigsmi.ws.io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.gov.misau.sigsmi.ws.io.model.PacienteEntity;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long>{

	Optional<PacienteEntity> findByPacienteId(String pacienteId);

}
