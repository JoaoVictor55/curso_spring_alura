package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medicos.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsultas{
	
	@Autowired
	private MedicoRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() == null) {
			return;
		}
		
		if(!repository.findAtivoId(dados.idMedico())) {
			throw new ValidacaoException("Consulta não pode ser agendada sem médico ativo");
		}
	}
}
