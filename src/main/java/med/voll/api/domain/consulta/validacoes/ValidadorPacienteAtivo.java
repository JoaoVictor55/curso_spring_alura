package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.pacientes.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsultas {
	
	@Autowired
	PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		if(!repository.findAtivoById(dados.idPaciente())) {
			throw new ValidacaoException("Consulta não pode ser agendada sem paciente ativo");
		}

	}
}
