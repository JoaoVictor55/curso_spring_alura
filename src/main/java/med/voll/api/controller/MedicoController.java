package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medicos.DadosAtualizacaoMedicos;
import med.voll.api.domain.medicos.DadosCadastroMedicos;
import med.voll.api.domain.medicos.DadosDetalhamentoMedico;
import med.voll.api.domain.medicos.DadosListagemMedico;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired //deixamos à cargo do Spring instaciar o repository
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional //indicamos que esse método realiza transaçcoes
	//indicando que queremos validar usando o bean validation
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder) {
		
		Medico medico = new Medico(dados);
		repository.save(medico);
		
		//cria a url para esse método. A url no argumento é o complemento
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); 
		
		//201 - record created
		return ResponseEntity.created(uri) //1) regrea: precisamos dizer o endereço de onde o registro foi criado
				.body(new DadosDetalhamentoMedico(medico)); //2) regra: precisamos retornar no corpo o registro criado
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
		
		Page<DadosListagemMedico> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		
		return ResponseEntity.ok(page); //código 200
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(repository.getReferenceById(id)));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
		
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
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
	public ResponseEntity excluirLogico(@PathVariable Long id) {
		
		repository.getReferenceById(id).excluir();
		
		return ResponseEntity.noContent().build(); //código 204
	}

}