package br.com.projetofinal.cadastro.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cadastro.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
