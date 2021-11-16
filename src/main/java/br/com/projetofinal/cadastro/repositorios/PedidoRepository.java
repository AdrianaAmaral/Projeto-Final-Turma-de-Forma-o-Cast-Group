package br.com.projetofinal.cadastro.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cadastro.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	
}
