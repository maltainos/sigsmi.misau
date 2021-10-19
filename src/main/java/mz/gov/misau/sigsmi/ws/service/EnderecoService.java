package mz.gov.misau.sigsmi.ws.service;

import java.util.List;

import mz.gov.misau.sigsmi.ws.io.model.PacienteEntity;
import mz.gov.misau.sigsmi.ws.shared.dto.EnderecoDTO;

public interface EnderecoService {
	
	public EnderecoDTO update(EnderecoDTO enderecoDTO, String enderecoId);
	public List<EnderecoDTO> foundAll(PacienteEntity paciente);
	public EnderecoDTO create(EnderecoDTO enderecoDTO);

}
