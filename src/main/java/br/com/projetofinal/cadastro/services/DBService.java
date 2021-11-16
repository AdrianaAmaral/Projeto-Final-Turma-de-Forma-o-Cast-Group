package br.com.projetofinal.cadastro.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cadastro.domain.Cliente;
import br.com.projetofinal.cadastro.domain.Funcionario;
import br.com.projetofinal.cadastro.domain.Pedido;
import br.com.projetofinal.cadastro.domain.Pessoa;
import br.com.projetofinal.cadastro.domain.enums.Perfil;
import br.com.projetofinal.cadastro.repositorios.ClienteRepository;
import br.com.projetofinal.cadastro.repositorios.FuncionarioRepository;
import br.com.projetofinal.cadastro.repositorios.PedidoRepository;
import br.com.projetofinal.cadastro.repositorios.PessoaRepository;

@Service
public class DBService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder; 

	public void instanciaDB() {
		
		Funcionario func1 = new Funcionario(null, "Adriana Amaral", "33625585855", "adrianafsamaral@gmail.com", encoder.encode("123"));
		func1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Mauricio do Amaral", "31327624826", "mauriciodoamaral1503@gmail.com", encoder.encode("123"));
		
		Pedido p1 = new Pedido(null, "Pedido 01", "Primeiro Pedido", func1, cli1);
	
		pessoaRepository.saveAll(Arrays.asList(func1, cli1));
		pedidoRepository.saveAll(Arrays.asList(p1));
	
	}
}
