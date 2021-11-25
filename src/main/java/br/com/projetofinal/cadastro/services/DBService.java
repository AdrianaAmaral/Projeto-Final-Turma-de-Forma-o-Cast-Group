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
import br.com.projetofinal.cadastro.domain.enums.Status;
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
		Funcionario func2 = new Funcionario(null, "Larissa Rodrigues", "44763823043", "larissarodrigues@gmail.com", encoder.encode("123"));
		Funcionario func3 = new Funcionario(null, "Luciana Santos", "45269187094", "lucianasantos@gmail.com", encoder.encode("123"));
		func1.addPerfil(Perfil.ADMIN);
		
			
		Cliente cli1 = new Cliente(null, "Mauricio do Amaral", "31327624826", "mauriciodoamaral1503@gmail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Rosana de Souza", "56094859081", "rosanadesouza@gmail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Andreia Soares", "26328347081", "andreiasoares@gmail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Marcia Campos", "89681465008", "marciacampos@gmail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Maria das Dores", "36404273004", "mariadasdores@gmail.com", encoder.encode("123"));
		
		Pedido p1 = new Pedido(null, Status.ANDAMENTO, "Pedido 1", "Trocar trilhos", func1, cli1);
		Pedido p2 = new Pedido(null, Status.ENCERRADO, "Pedido 2", "Trocar torneira", func2, cli3);
		Pedido p3 = new Pedido(null, Status.ABERTO, "Pedido 3", "Trocar chuveiro", func3, cli2);
		Pedido p4 = new Pedido(null, Status.ENCERRADO, "Pedido 4", "Consertar encanamento ", func2, cli4);
		Pedido p5 = new Pedido(null, Status.ENCERRADO, "Pedido 5", "Trocar vidros", func3, cli5);
		
		pessoaRepository.saveAll(Arrays.asList(func1, func2, func3, cli1, cli2, cli3, cli4, cli5));
		
	
	}
}
