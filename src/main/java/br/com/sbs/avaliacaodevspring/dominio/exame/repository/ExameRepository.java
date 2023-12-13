package br.com.sbs.avaliacaodevspring.dominio.exame.repository;

import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {
}
