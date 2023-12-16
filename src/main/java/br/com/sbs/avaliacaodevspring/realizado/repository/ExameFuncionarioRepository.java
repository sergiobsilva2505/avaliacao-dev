package br.com.sbs.avaliacaodevspring.realizado.repository;

import br.com.sbs.avaliacaodevspring.realizado.entity.ExameFuncionario;
import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExameFuncionarioRepository extends JpaRepository<ExameFuncionario, Long> {

    boolean existsByExameRowidAndFuncionarioRowidAndRealizadoEm(Long exameId, Long funcionarioId, LocalDate realizadoEm);

    default boolean existsByExameAndFuncionarioAndDate(Long exameId, Long funcionarioId) {
        return existsByExameRowidAndFuncionarioRowidAndRealizadoEm(exameId, funcionarioId, LocalDate.now());
    }

    boolean existsByExame_RowidAndFuncionario_RowidAndRealizadoEmAndRowidNot(Long exameId, Long funcionarioId, LocalDate realizadoEm, Long rowid);

    default boolean existsByExameAndFuncionarioAndDateAndIdNot(Long exameId, Long funcionarioId, Long rowid) {
        return existsByExame_RowidAndFuncionario_RowidAndRealizadoEmAndRowidNot(exameId, funcionarioId, LocalDate.now(), rowid);
    }

    boolean existsByExame_Rowid(Long exame_rowid);

    @Query(value = """
            SELECT fc.rowid AS rowIdEmployee, fc.nm_funcionario AS nomeEmployee, ex.rowid AS rowIdExam, ex.nm_exame AS nomeExam, ef.realizado_em AS createdAt FROM EXAME_FUNCIONARIO ef
                JOIN EXAME ex ON ex.rowid = ef.exame_id
                JOIN FUNCIONARIO fc ON fc.rowid = ef.funcionario_id
                WHERE ef.realizado_em BETWEEN  :initialDate AND :finishDate
            """, nativeQuery = true)
    List<ReportByPeriod> getReportByPeriod(@Param("initialDate") LocalDate initialDate, @Param("finishDate") LocalDate finishDate);

}
