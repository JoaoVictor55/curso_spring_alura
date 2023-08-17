package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.domain.pacientes.Paciente;
import med.voll.api.domain.pacientes.PacienteRepository;

@Service //executa regras de negócios e validações
public class AgendaDeConsultas {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorAgendamentoConsultas> validadores;
	
	public void agendar(DadosAgendamentoConsulta dados) {
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe");
		}
		if(dados.idMedico() != null &&  !medicoRepository.existsById(dados.idMedico())) {
			
		}
		
		validadores.forEach(v -> v.validar(dados));
		
		Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		Medico medico = escolherMedico(dados);
		
		Consulta consulta = new Consulta(null,medico, paciente,  dados.data());
		consultaRepository.save(consulta);
		
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() != null) {
		
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreData(dados.especialidade(), dados.data());
	}
	
}
