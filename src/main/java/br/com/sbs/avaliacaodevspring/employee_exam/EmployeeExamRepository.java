package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.report.ReportByPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeExamRepository extends JpaRepository<EmployeeExam, Long> {

    boolean existsByExam_Rowid(Long examRowid);

    boolean existsByEmployee_RowidAndExam_RowidAndAccomplishedAt(Long employeeRowId, Long examRowId, LocalDate accomplishedAt);

    default boolean newQuery(Long employeeRowId, Long examRowId, LocalDate accomplishedAt) {
         return existsByEmployee_RowidAndExam_RowidAndAccomplishedAt(employeeRowId, examRowId, accomplishedAt);
    }

    boolean existsByEmployee_RowidAndExam_RowidAndAccomplishedAtAndRowidNot(Long employeeRowId, Long examRowId, LocalDate accomplishedAt, Long rowid);

    @Query(value = """
            SELECT ep.rowid AS rowIdEmployee, ep.name AS nomeEmployee, ex.rowid AS rowIdExam, ex.name AS nomeExam, ee.accomplished_at AS accomplishedAt FROM employee_exam ee
                JOIN exam ex ON ex.rowid = ee.exam_id
                JOIN employee ep ON ep.rowid = ee.employee_id
                WHERE ee.accomplished_at BETWEEN  :initialDate AND :finishDate
            """, nativeQuery = true)
    List<ReportByPeriod> getReportByPeriod(@Param("initialDate") LocalDate initialDate, @Param("finishDate") LocalDate finishDate);

}
