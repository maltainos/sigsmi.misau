package mz.gov.misau.sigsmi.ws.service.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.gov.misau.sigsmi.ws.exception.resource.DenidOperationAndInvalidResourceException;
import mz.gov.misau.sigsmi.ws.exception.resource.PacienteNotFoundException;
import mz.gov.misau.sigsmi.ws.io.model.entity.EnderecoEntity;
import mz.gov.misau.sigsmi.ws.io.model.entity.PacienteEntity;
import mz.gov.misau.sigsmi.ws.io.repository.PacienteRepository;
import mz.gov.misau.sigsmi.ws.service.EnderecoService;
import mz.gov.misau.sigsmi.ws.service.PacienteService;
import mz.gov.misau.sigsmi.ws.shared.MyUtils;
import mz.gov.misau.sigsmi.ws.shared.dto.EnderecoDTO;
import mz.gov.misau.sigsmi.ws.shared.dto.PacienteDTO;

@Service
public class PacienteServiceImpl implements PacienteService{
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<PacienteDTO> search() {
		List<PacienteEntity> pacientes = pacienteRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		Type pacienteTypeList = new TypeToken<List<PacienteDTO>>() {}.getType();
		return mapper.map(pacientes, pacienteTypeList);
	}

	@Override
	public PacienteDTO create(PacienteDTO pacienteDTO) {
		pacienteDTO.setPacienteId(myUtils.generateUrlResource(30));
		pacienteDTO.setCriadoEm(LocalDateTime.now());
		ModelMapper mapper = new ModelMapper();
		PacienteEntity paciente = mapper.map(pacienteDTO, PacienteEntity.class);
		paciente = pacienteRepository.save(paciente);
		return mapper.map(paciente, PacienteDTO.class);
	}

	@Override
	public PacienteDTO update(PacienteDTO pacienteDTO, String pacienteId) {
		
		PacienteEntity foundPaciente = findByPacienteId(pacienteId);
		ModelMapper mapper = new ModelMapper();
		PacienteEntity paciente = mapper.map(pacienteDTO, PacienteEntity.class);
		paciente.setId(foundPaciente.getId());
		paciente.setPacienteId(pacienteId);
		paciente.setCriadoEm(foundPaciente.getCriadoEm());
		paciente.setAtualizadoEm(LocalDateTime.now());
		int index = 0;
		for(EnderecoEntity endereco : paciente.getEnderecos()) {
			endereco.setId(foundPaciente.getEnderecos().get(index).getId());
			endereco.setEnderecoId(myUtils.generateUrlResource(30));
			endereco.setPaciente(paciente);
			index++;
		}
		paciente = pacienteRepository.save(paciente);
		
		return mapper.map(paciente, PacienteDTO.class);
	}

	public EnderecoDTO create(EnderecoDTO enderecoDTO, String pacienteId) {

		ModelMapper mapper = new ModelMapper();
		PacienteDTO pacienteDTO = mapper.map(findByPacienteId(pacienteId), PacienteDTO.class);
		enderecoDTO.setPaciente(pacienteDTO);
		return enderecoService.create(enderecoDTO);
	}

	public List<EnderecoDTO> searchAddresses(String pacienteId) {
		List<EnderecoDTO> enderecos= enderecoService.foundAll(findByPacienteId(pacienteId));
		return enderecos;
	}

	private PacienteEntity findByPacienteId(String pacienteId) {
		Optional<PacienteEntity> foundPaciente = pacienteRepository.findByPacienteId(pacienteId);
		if(!foundPaciente.isPresent())
			throw new PacienteNotFoundException("PacienteNotFoundException");
		return foundPaciente.get();
	}
	
	private PacienteEntity operationPacienteId(String pacienteId) {
		Optional<PacienteEntity> foundPaciente = pacienteRepository.findByPacienteId(pacienteId);
		if(!foundPaciente.isPresent())
			throw new DenidOperationAndInvalidResourceException("DenidOperationAndInvalidResourceException");
		return foundPaciente.get();
	}

	public EnderecoDTO update(EnderecoDTO enderecoDTO, String pacienteId, String enderecoId) {
		PacienteEntity paciente = operationPacienteId(pacienteId);
		ModelMapper mapper = new ModelMapper();
		enderecoDTO.setPaciente(mapper.map(paciente, PacienteDTO.class));
		EnderecoDTO returnValue = enderecoService.update(enderecoDTO, enderecoId);
		return returnValue;
	}
	
}









