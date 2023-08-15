package med.voll.api.domain.pacientes;

public record DadosListagemPacientes(String nome, String email, String cpf) {

	public DadosListagemPacientes(Paciente paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}
}
