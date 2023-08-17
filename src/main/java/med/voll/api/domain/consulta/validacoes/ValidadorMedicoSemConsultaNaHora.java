package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorMedicoSemConsultaNaHora implements ValidadorAgendamentoConsultas{
	
	@Autowired
	ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() == null) {
			return;
		}
		
		if(repository.existsByMedicoIdAndData(dados.idMedico(), dados.data())) {
			throw new ValidacaoException("O médico já possui uma consulta nesse horário e dia");
		}

	}
}
