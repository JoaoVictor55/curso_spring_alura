package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

	public void validar(DadosAgendamentoConsulta dados) {
		
		LocalDateTime dataConsulta = dados.data();
		
		Boolean ehDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		
		
		Boolean ehAntesDaAbertura = dataConsulta.getHour() < 7;
		Boolean ehDepoisDaAbertura = dataConsulta.getHour() > 18;
		
		if(ehDomingo || ehAntesDaAbertura || ehDepoisDaAbertura) {
			throw new ValidacaoException("Consulta fora do horário ou dia de funcionamento");
		}
	}
}
