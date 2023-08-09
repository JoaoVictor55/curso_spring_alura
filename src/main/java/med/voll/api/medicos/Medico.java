package med.voll.api.medicos;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.enderecos.Endereco;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	public Medico(){}
	
	public Medico(DadosCadastroMedicos dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.endereco = new Endereco(dados.endereco());
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.telefone = dados.telefone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;
	private Boolean ativo;
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;

	public void atualizarInformacoes(@Valid DadosAtualizacaoMedicos dados) {
		
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

	public void excluir() {
		this.ativo = false;
		
	}

	
}
