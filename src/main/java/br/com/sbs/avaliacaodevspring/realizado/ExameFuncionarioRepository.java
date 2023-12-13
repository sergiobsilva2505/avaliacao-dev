package br.com.sbs.avaliacaodevspring.realizado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ExameFuncionarioRepository extends JpaRepository<ExameFuncionario, Long> {

    boolean existsByExameRowidAndFuncionarioRowidAndRealizadoEm(Long exameId, Long funcionarioId, LocalDate realizadoEm);

    default boolean existsByExameAndFuncionarioAndDate(Long exameId, Long funcionarioId) {
        return existsByExameRowidAndFuncionarioRowidAndRealizadoEm(exameId, funcionarioId, LocalDate.now());
    }
}
