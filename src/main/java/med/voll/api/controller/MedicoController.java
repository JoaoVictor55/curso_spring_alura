package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medicos.DadosAtualizacaoMedicos;
import med.voll.api.medicos.DadosCadastroMedicos;
import med.voll.api.medicos.DadosListagemMedico;
import med.voll.api.medicos.Medico;
import med.voll.api.medicos.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired //deixamos à cargo do Spring instaciar o repository
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional //indicamos que esse método realiza transaçcoes
	//indicando que queremos validar usando o bean validation
	public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) {
		
		repository.save(new Medico(dados));
	}
	
	@GetMapping
	public Page<DadosListagemMedico> listar(Pageable paginacao){
		
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
		
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	/*
	@Deprecated
	@DeleteMapping("/{id}") //estamos criando um parâmetro dinâmico. O endereço para esse método é /id onde id é o nome do parâmetro
	@Transactional
	public void excluir(@PathVariable Long id) //estamos indicando que id é o mesmo pâmetro da url
	{
		repository.deleteById(id);
	}*/
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluirLogico(@PathVariable Long id) {
		
		repository.getReferenceById(id).excluir();
	}

}