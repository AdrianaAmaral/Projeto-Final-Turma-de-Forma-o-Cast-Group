package br.com.projetofinal.cadastro.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cadastro.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
