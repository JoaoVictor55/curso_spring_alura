package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsultas{

	public void validar(DadosAgendamentoConsulta dados) {
		
		if (Duration.between(LocalDateTime.now(), dados.data()).toMinutes() < 30) {
			throw new ValidacaoException("É necessário antecedência de 30 min");
		}
	}
}
