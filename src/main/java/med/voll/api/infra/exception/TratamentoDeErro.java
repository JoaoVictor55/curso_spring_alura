package med.voll.api.infra.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

//indicamos ao Spring que essa classe lida com erros
@RestControllerAdvice
public class TratamentoDeErro {

	//Registramos que esse método lida com EntityNotFoundException
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarError404() {
		
		return ResponseEntity.notFound().build();
	}
	
	//se vc colocar a exceção capturada no argumento da função, o Spring retorna a exceção para vc
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarError400(MethodArgumentNotValidException ex) {
		
		List<FieldError> erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErrorValidacao::new).toList());
	}
	
	private record DadosErrorValidacao(String campo, String mensagem) {
		
		public DadosErrorValidacao(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	};
}
