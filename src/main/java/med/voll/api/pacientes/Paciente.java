package med.voll.api.pacientes;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import med.voll.api.enderecos.Endereco;
import med.voll.api.medicos.DadosAtualizacaoMedicos;

@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Boolean ativo;
	@Embedded
	private Endereco endereco;
	
	public Paciente() {super();};
	
	public Paciente(DadosCadastraisPacientes dados) {
		super();
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.endereco = new Endereco(dados.endereco());
		this.cpf = dados.cpf();
		this.telefone = dados.telefone();
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoPacientes dados) {
		
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco()); 
		}
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void excluir() {
		
		this.setAtivo(false);
		
	}

}
