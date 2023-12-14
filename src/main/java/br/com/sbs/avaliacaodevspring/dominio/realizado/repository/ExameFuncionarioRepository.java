package br.com.sbs.avaliacaodevspring.dominio.realizado.repository;

import br.com.sbs.avaliacaodevspring.dominio.exame.entity.Exame;
import br.com.sbs.avaliacaodevspring.dominio.funcionario.entity.Funcionario;
import br.com.sbs.avaliacaodevspring.dominio.realizado.entity.ExameFuncionario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ExameFuncionarioRepository extends JpaRepository<ExameFuncionario, Long> {

    boolean existsByExameRowidAndFuncionarioRowidAndRealizadoEm(Long exameId, Long funcionarioId, LocalDate realizadoEm);
//    boolean existsByExameRowidAndFuncionarioRowidAndRealizadoEmAndRowidNot(Long rowid, Long exameId, Long funcionarioId, LocalDate realizadoEm);

    default boolean existsByExameAndFuncionarioAndDate(Long exameId, Long funcionarioId) {
        return existsByExameRowidAndFuncionarioRowidAndRealizadoEm(exameId, funcionarioId, LocalDate.now());
    }

//    default boolean existsByExameAndFuncionarioAndDateAndIdNot(Long rowid, Long exameId, Long funcionarioId) {
//        return existsByExameRowidAndFuncionarioRowidAndRealizadoEmAndRowidNot(rowid, exameId, funcionarioId, LocalDate.now());
//    }

    boolean existsByExame_Rowid(Long exame_rowid);
}
