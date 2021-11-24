package br.com.projetofinal.cadastro.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetofinal.cadastro.domain.Pedido;
import br.com.projetofinal.cadastro.domain.dto.PedidoDTO;
import br.com.projetofinal.cadastro.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) {
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(new PedidoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<Pedido> list = service.findAll();
		List<PedidoDTO> listDTO = list.stream().map(obj -> new PedidoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO obj) {
		Pedido newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> update(@PathVariable Integer id, @Valid @RequestBody PedidoDTO objDTO) {
		Pedido newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new PedidoDTO(newObj));
	}
}

