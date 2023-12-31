package med.voll.api.domain.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enderecos.DadosEndereco;

public record DadosAtualizacaoMedicos(
		@NotNull
		Long id,
		String nome,
		String telefone,
		@Valid
		DadosEndereco endereco
		) {

}
