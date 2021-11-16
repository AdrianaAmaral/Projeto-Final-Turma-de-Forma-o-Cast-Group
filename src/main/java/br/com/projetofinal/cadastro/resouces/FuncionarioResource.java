package br.com.projetofinal.cadastro.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetofinal.cadastro.domain.Funcionario;
import br.com.projetofinal.cadastro.domain.dto.FuncionarioDTO;
import br.com.projetofinal.cadastro.services.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id) {
	Funcionario obj = this.service.findById(id);
	return ResponseEntity.ok().body(new FuncionarioDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> findAll() {
		List<Funcionario> list = service.findAll();
		List<FuncionarioDTO> listDTO = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO objDTO) {
		Funcionario newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> update(@PathVariable Integer id, @Valid @RequestBody FuncionarioDTO objDTO) {
		Funcionario obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new FuncionarioDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
}