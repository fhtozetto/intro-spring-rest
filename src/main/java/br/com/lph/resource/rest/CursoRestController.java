package br.com.lph.resource.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lph.domain.Curso;
import br.com.lph.service.CursoService;

@RestController
@RequestMapping(
		value = "/cursos",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
		)
public class CursoRestController {
	@Autowired
	private CursoService service;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	private Curso getCurso(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Curso curso) {
		service.save(curso);
		
		URI location  = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(curso.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Curso> listar() {
		return service.findAll();
	}
}
