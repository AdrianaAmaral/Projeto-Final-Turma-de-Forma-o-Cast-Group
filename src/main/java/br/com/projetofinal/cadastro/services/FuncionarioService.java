package br.com.projetofinal.cadastro.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cadastro.domain.Funcionario;
import br.com.projetofinal.cadastro.domain.Pessoa;
import br.com.projetofinal.cadastro.domain.dto.FuncionarioDTO;
import br.com.projetofinal.cadastro.domain.enums.Status;
import br.com.projetofinal.cadastro.repositorios.FuncionarioRepository;
import br.com.projetofinal.cadastro.repositorios.PessoaRepository;
import br.com.projetofinal.cadastro.services.excecao.ObjectnotFoundException;
import br.com.projetofinal.cadastro.services.excecao.DataIntegrityViolationException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	public Funcionario findById(Integer id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario create(FuncionarioDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Funcionario newObj = new Funcionario(objDTO);
		return repository.save(newObj);
	}
	
	public Funcionario update(Integer id, @Valid FuncionarioDTO objDTO) {
		objDTO.setId(id);
		Funcionario oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha()))
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
	
		validaPorCpfEEmail(objDTO);
		oldObj = new Funcionario(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Funcionario obj = findById(id);

		if (obj.getPedidos().size() > 0) {
			throw new DataIntegrityViolationException("Funcionario possui um pedido aberto e não pode ser deletado!");
		} if (obj.getPedidos().equals(Status.ENCERRADO)) {		
		repository.deleteById(id);
			}
	}
	
	private void validaPorCpfEEmail(FuncionarioDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF Cadastrado!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail Cadastrado!");
		}
	}

}
