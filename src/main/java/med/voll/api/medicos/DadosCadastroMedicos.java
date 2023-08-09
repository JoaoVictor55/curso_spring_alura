package med.voll.api.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enderecos.DadosEndereco;

public record DadosCadastroMedicos(
		@NotBlank //não pode string vazia
		String nome,
		@NotBlank
		@Email
		String email, 
		@NotBlank
		@Pattern(regexp = "\\d{4,6}") //digito de 4 a 6 digitos
		String crm, 
		@NotNull 
		Especialidade especialidade,
		
		@NotNull
		String telefone,
		
		@NotNull
		@Valid //pedindo para validar também endereço
		DadosEndereco endereco) {

}
