package med.voll.api.domain.medicos;

import med.voll.api.domain.enderecos.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, Especialidade especialidade, Endereco endereco) {
	
	public DadosDetalhamentoMedico(Medico medico) {
		
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getEspecialidade(), medico.getEndereco());
	}

}
