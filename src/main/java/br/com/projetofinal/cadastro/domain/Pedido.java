package br.com.projetofinal.cadastro.domain;

	import java.io.Serializable;
	import java.time.LocalDate;
	
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;

	import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.projetofinal.cadastro.domain.enums.Status;
	
	@Entity
	public class Pedido implements Serializable {
		private static final long serialVersionUID = 1L;


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@JsonFormat(pattern = "dd/MM/yyyy")
		private LocalDate dataPedido = LocalDate.now();
		@JsonFormat(pattern = "dd/MM/yyyy")
		private LocalDate dataFechamento;
		
		private Status status;
		private String titulo;
		private String observacoes;

		@ManyToOne
		@JoinColumn(name = "funcionario_id")
		private Funcionario funcionario;
		
		@ManyToOne
		@JoinColumn(name = "cliente_id")
		private Cliente cliente;

		public Pedido() {
			super();

		}

		public Pedido(Integer id, Status status, String titulo, String observacoes, Funcionario funcionario,
				Cliente cliente) {
			super();
			this.id = id;
			this.status = status;
			this.titulo = titulo;
			this.observacoes = observacoes;
			this.funcionario = funcionario;
			this.cliente = cliente;
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
		
		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
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

		public Funcionario getFuncionario() {
			return funcionario;
		}

		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pedido other = (Pedido) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

	}

