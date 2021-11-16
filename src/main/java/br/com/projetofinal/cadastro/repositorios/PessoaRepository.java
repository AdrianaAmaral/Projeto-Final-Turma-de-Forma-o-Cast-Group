package br.com.projetofinal.cadastro.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cadastro.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
}
