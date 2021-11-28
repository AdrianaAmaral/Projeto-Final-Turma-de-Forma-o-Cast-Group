package br.com.projetofinal.cadastro.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cadastro.domain.Cliente;
import br.com.projetofinal.cadastro.domain.Funcionario;
import br.com.projetofinal.cadastro.domain.Pedido;
import br.com.projetofinal.cadastro.domain.dto.PedidoDTO;
import br.com.projetofinal.cadastro.domain.enums.Status;
import br.com.projetofinal.cadastro.repositorios.PedidoRepository;
import br.com.projetofinal.cadastro.services.excecao.ObjectnotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private ClienteService clienteService;

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido create(PedidoDTO obj) {
		return repository.save(newPedido(obj));
	}

	public Pedido update(Integer id, @Valid PedidoDTO objDTO) { //objDto com as informações atualizadas
		objDTO.setId(id);
		Pedido oldObj = findById(id); //oldObj com as informações desatualizadas
		oldObj = newPedido(objDTO);
		return repository.save(oldObj);
	}

	private Pedido newPedido(PedidoDTO obj) { //esse novo pedido atualiza e cria
		Funcionario funcionario = funcionarioService.findById(obj.getFuncionario());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Pedido Pedido = new Pedido();
		if(obj.getId() != null) { //se for diferente de nulo quer dizer que quer atualizar
			Pedido.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) { //se o status for encerrado(2) então pega a data atual para data de fechamento
			Pedido.setDataFechamento(LocalDate.now());
		}
		
		Pedido.setFuncionario(funcionario);
		Pedido.setCliente(cliente);
		Pedido.setStatus(Status.toEnum(obj.getStatus()));
		Pedido.setTitulo(obj.getTitulo());
		Pedido.setObservacoes(obj.getObservacoes());
		return Pedido;
	}

}