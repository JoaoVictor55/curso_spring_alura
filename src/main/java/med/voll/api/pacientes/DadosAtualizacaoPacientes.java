package med.voll.api.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.enderecos.DadosEndereco;

public record DadosAtualizacaoPacientes(
		@NotNull
		Long id,
		String nome,
		String telefone,
		@Valid
		DadosEndereco endereco
		) {}



