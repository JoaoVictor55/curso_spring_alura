package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.pacientes.DadosAtualizacaoPacientes;
import med.voll.api.pacientes.DadosCadastraisPacientes;
import med.voll.api.pacientes.DadosListagemPacientes;
import med.voll.api.pacientes.Paciente;
import med.voll.api.pacientes.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastraisPacientes dados) {
		
		pacienteRepository.save(new Paciente(dados));
	}
	
	@GetMapping
	public Page<DadosListagemPacientes> listar(@PageableDefault(page = 10, size = 10, sort = {"nome"}) Pageable paginacao){
		
		return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new);
		
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoPacientes dados) {
	
		Paciente paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluirPaciente(@PathVariable Long id) {
		
		pacienteRepository.getReferenceById(id).excluir();
	}
}
