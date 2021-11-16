package br.com.projetofinal.cadastro.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.projetofinal.cadastro.domain.Pedido;


public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPedido = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@NotNull(message = "O campo STATUS é obrigatório")
	private Integer status;
	@NotNull(message = "O campo TITULO é obrigatório")
	private String titulo;
	@NotNull(message = "O campo OBSERVAÇÕES é obrigatório")
	private String observacoes;
	@NotNull(message = "O campo FUNCIONARIO é obrigatório")
	private Integer funcionario;
	@NotNull(message = "O campo CLIENTE é obrigatório")
	private Integer cliente;
	private String nomeFuncionario;
	private String nomeCliente;

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Pedido obj) {
		this.id = obj.getId();
		this.dataPedido = obj.getDataPedido();
		this.dataFechamento = obj.getDataFechamento();
		this.status = obj.getStatus().getCodigo();
		this.titulo = obj.getTitulo();
		this.observacoes = obj.getObservacoes();
		this.funcionario = obj.getFuncionario().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeCliente = obj.getCliente().getNome();
		this.nomeFuncionario = obj.getFuncionario().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Integer funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	
}