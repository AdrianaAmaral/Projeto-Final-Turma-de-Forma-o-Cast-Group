package br.com.projetofinal.cadastro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projetofinal.cadastro.domain.dto.FuncionarioDTO;
import br.com.projetofinal.cadastro.domain.enums.Perfil;



@Entity
public class Funcionario extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<Pedido> pedidos = new ArrayList<>();

	//CONSTRUTOR SUPER CLASSE
	public Funcionario() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Funcionario(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public Funcionario(FuncionarioDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
		addPerfil(Perfil.CLIENTE);
	}
	//MÉTODOS GET E SET
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
		
}
