package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medicos.Especialidade;

public record DadosAgendamentoConsulta(Long idMedico, 
		@NotNull
		Long idPaciente,
		
		@NotNull
		@Future //faz a data ser sempre no futuro
		LocalDateTime data,
		
		Especialidade especialidade
		) {
	
}
