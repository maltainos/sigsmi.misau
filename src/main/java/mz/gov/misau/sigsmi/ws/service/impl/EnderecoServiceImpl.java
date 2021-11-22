package mz.gov.misau.sigsmi.ws.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.gov.misau.sigsmi.ws.exception.resource.EnderecoNotFoundException;
import mz.gov.misau.sigsmi.ws.io.model.entity.EnderecoEntity;
import mz.gov.misau.sigsmi.ws.io.model.entity.PacienteEntity;
import mz.gov.misau.sigsmi.ws.io.repository.EnderecoRepository;
import mz.gov.misau.sigsmi.ws.service.EnderecoService;
import mz.gov.misau.sigsmi.ws.shared.MyUtils;
import mz.gov.misau.sigsmi.ws.shared.dto.EnderecoDTO;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public EnderecoDTO create(EnderecoDTO enderecoDTO) {
		enderecoDTO.setEnderecoId(myUtils.generateUrlResource(30));
		ModelMapper mapper = new ModelMapper();
		EnderecoEntity endereco = mapper.map(enderecoDTO, EnderecoEntity.class);
		endereco = enderecoRepository.save(endereco);
		return mapper.map(endereco, EnderecoDTO.class);
	}

	@Override
	public List<EnderecoDTO> foundAll(PacienteEntity paciente) {
		List<EnderecoEntity> enderecos = enderecoRepository.findByPaciente(paciente);
		ModelMapper mapper = new ModelMapper();
		Type enderecoTypeList = new TypeToken<List<EnderecoDTO>>() {}.getType();
		return mapper.map(enderecos, enderecoTypeList);
	}

	@Override
	public EnderecoDTO update(EnderecoDTO enderecoDTO, String enderecoId) {

		EnderecoEntity endereco = foundEndereco(enderecoId);
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setEnderecoId(enderecoId);
		ModelMapper mapper = new ModelMapper();
		endereco = mapper.map(enderecoDTO, EnderecoEntity.class);
		endereco = enderecoRepository.save(endereco);
		
		return mapper.map(endereco, EnderecoDTO.class);
	}
	
	private EnderecoEntity foundEndereco(String enderecoId) {
		Optional<EnderecoEntity> endereco = enderecoRepository.findByEnderecoId(enderecoId);
		if(!endereco.isPresent())
			throw new EnderecoNotFoundException("EnderecoNotFoundException");
		return endereco.get();
	}
}











