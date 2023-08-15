package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import med.voll.api.domain.pacientes.DadosAtualizacaoPacientes;
import med.voll.api.domain.pacientes.DadosCadastraisPacientes;
import med.voll.api.domain.pacientes.DadosDetalhamentoPaciente;
import med.voll.api.domain.pacientes.DadosListagemPacientes;
import med.voll.api.domain.pacientes.Paciente;
import med.voll.api.domain.pacientes.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastraisPacientes dados, UriComponentsBuilder uriBuilder) {
		
		Paciente paciente = new Paciente(dados);
		
		pacienteRepository.save(new Paciente(dados));
		
		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPacientes>> listar(@PageableDefault(page = 10, size = 10, sort = {"nome"}) Pageable paginacao){
		
		return ResponseEntity.
				ok(pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalharPaciente(@PathVariable Long id) {
		
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(pacienteRepository.getReferenceById(id)));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPacientes dados) {
	
		Paciente paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirPaciente(@PathVariable Long id) {
		
		pacienteRepository.getReferenceById(id).excluir();
		
		return ResponseEntity.noContent().build();
	}
}
