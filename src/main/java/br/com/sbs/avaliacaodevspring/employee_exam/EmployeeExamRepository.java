package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeExamRepository extends JpaRepository<EmployeeExam, Long> {

//    boolean existsByEmployeeRowidAndExamRowidAndAccomplishedIn(Long employeeId, Long examId, LocalDate accomplishedIn);
//
//    default boolean existsByExameAndFuncionarioAndDate(Long employeeId, Long examId) {
//        return existsByEmployeeRowidAndExamRowidAndAccomplishedIn(employeeId, examId, LocalDate.now());
//    }
//
//    boolean existsByEmployeeRowidAndExamRowidAndAccomplishedInAAndRowidNot(Long employeeId, Long examId, LocalDate accomplishedIn, Long rowid);
//
//    default boolean existsByExameAndFuncionarioAndDateAndIdNot(Long exameId, Long funcionarioId, Long rowid) {
//        return existsByExame_RowidAndFuncionario_RowidAndRealizadoEmAndRowidNot(exameId, funcionarioId, LocalDate.now(), rowid);
//    }

    boolean existsByExam_Rowid(Long examRowid);

    @Query(value = """
            SELECT fc.rowid AS rowIdEmployee, fc.name AS nomeEmployee, ex.rowid AS rowIdExam, ex.name AS nomeExam, ef.accomplishedIn AS createdAt FROM  ef
                JOIN EXAME ex ON ex.rowid = ef.exame_id
                JOIN FUNCIONARIO fc ON fc.rowid = ef.funcionario_id
                WHERE ef.realizado_em BETWEEN  :initialDate AND :finishDate
            """, nativeQuery = true)
    List<ReportByPeriod> getReportByPeriod(@Param("initialDate") LocalDate initialDate, @Param("finishDate") LocalDate finishDate);

}
