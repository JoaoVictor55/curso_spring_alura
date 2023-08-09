package med.voll.api.enderecos;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	
	public Endereco() {
		
	}
	
    public Endereco(DadosEndereco endereco) {
		
    	logradouro = endereco.logradouro();
    	bairro = endereco.bairro();
    	cep = endereco.cep();
    	numero = endereco.numero();
    	complemento = endereco.complemento();
    	cidade = endereco.cidade();
    	uf = endereco.uf();
	}
	private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    
    public void atualizarInformacoes(DadosEndereco dados) {
    	
    	if(dados.logradouro() != null)
    		logradouro = dados.logradouro();
    	
    	if(dados.bairro() != null)
    		bairro = dados.bairro();
    	
    	if(dados.cep() != null)
    	cep = dados.cep();
    	
    	if(dados.numero() != null)
    		numero = dados.numero();
    	
    	if(dados.complemento() != null)
    		complemento = dados.complemento();
    	
    	if(dados.cidade() != null)
    		cidade = dados.cidade();
    	
    	if(dados.uf() != null)
    		uf = dados.uf();
    	
    }
}
