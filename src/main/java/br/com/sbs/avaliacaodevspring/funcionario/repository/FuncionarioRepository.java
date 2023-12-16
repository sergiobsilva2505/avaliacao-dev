package br.com.sbs.avaliacaodevspring.funcionario.repository;

import br.com.sbs.avaliacaodevspring.funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
