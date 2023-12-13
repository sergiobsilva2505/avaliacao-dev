package br.com.sbs.avaliacaodevspring.dominio.funcionario.repository;

import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
