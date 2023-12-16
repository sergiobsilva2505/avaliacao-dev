package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.relatorio.ReportByPeriod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeExamRepository extends JpaRepository<EmployeeExam, Long> {

    boolean existsByEmployeeRowidAndExamRowidAndAccomplishedAt(Long employeeRowid, Long examRowid, @NotNull @PastOrPresent LocalDate accomplishedAt);

    default boolean existsByEmployeeAndExamAndDate(Long employeeRowid, Long examRowid) {
        return existsByEmployeeRowidAndExamRowidAndAccomplishedAt(employeeRowid, examRowid, LocalDate.now());
    }

    boolean existsByEmployeeRowidAndExamRowidAndAccomplishedAtAndRowidNot(Long employeeRowid, Long examRowid, @NotNull @PastOrPresent LocalDate accomplishedAt, Long rowid);

    default boolean existsByEmployeeAndExamAndDateAndRowidNot(Long employeeRowid, Long examRowid, Long rowid) {
        return existsByEmployeeRowidAndExamRowidAndAccomplishedAtAndRowidNot(employeeRowid, examRowid, LocalDate.now(), rowid);
    }

    boolean existsByExam_Rowid(Long examRowid);

    @Query(value = """
            SELECT fc.rowid AS rowIdEmployee, fc.name AS nomeEmployee, ex.rowid AS rowIdExam, ex.name AS nomeExam, ef.accomplishedIn AS createdAt FROM  ef
                JOIN EXAME ex ON ex.rowid = ef.exame_id
                JOIN FUNCIONARIO fc ON fc.rowid = ef.funcionario_id
                WHERE ef.realizado_em BETWEEN  :initialDate AND :finishDate
            """, nativeQuery = true)
    List<ReportByPeriod> getReportByPeriod(@Param("initialDate") LocalDate initialDate, @Param("finishDate") LocalDate finishDate);

}
