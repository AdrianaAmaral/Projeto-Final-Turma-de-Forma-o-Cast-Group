package br.com.projetofinal.cadastro.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cadastro.domain.Cliente;
import br.com.projetofinal.cadastro.domain.Pessoa;
import br.com.projetofinal.cadastro.domain.dto.ClienteDTO;
import br.com.projetofinal.cadastro.domain.enums.Perfil;
import br.com.projetofinal.cadastro.domain.enums.Status;
import br.com.projetofinal.cadastro.repositorios.ClienteRepository;
import br.com.projetofinal.cadastro.repositorios.PessoaRepository;
import br.com.projetofinal.cadastro.services.excecao.ObjectnotFoundException;
import br.com.projetofinal.cadastro.services.excecao.DataIntegrityViolationException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha())); //quando salvar a senha no BD, ela vai encodada
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);

		if (!objDTO.getSenha().equals(oldObj.getSenha()))
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));

		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);

		if (obj.getPedidos().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui um pedido aberto e não pode ser deletado!");
		}
		if (obj.getPerfis().equals(Perfil.CLIENTE)) {
			throw new DataIntegrityViolationException("Perfil não habilitado!");
		}
		if (obj.getPedidos().equals(Status.ENCERRADO)) {
			repository.deleteById(id);
		} else {
			repository.deleteById(id);
		}
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF Cadastrado!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail Cadastrado!");
		}
	}

}
