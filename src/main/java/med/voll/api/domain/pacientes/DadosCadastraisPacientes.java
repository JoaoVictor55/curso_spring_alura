package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.enderecos.DadosEndereco;

public record DadosCadastraisPacientes(
		@NotBlank
		String nome,
		@NotBlank
		String email,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String cpf, 
		@NotBlank
		String telefone,
		@Valid
		DadosEndereco endereco
		) {

}
