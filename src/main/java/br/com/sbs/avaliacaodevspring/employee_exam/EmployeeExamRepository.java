package br.com.sbs.avaliacaodevspring.employee_exam;

import br.com.sbs.avaliacaodevspring.report.ReportByPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeExamRepository extends JpaRepository<EmployeeExam, Long> {

    boolean existsByExam_Rowid(Long examRowid);

    @Query("""
            SELECT COUNT(ee) > 0 FROM EmployeeExam ee
            WHERE ee.exam.rowid = :examId
            AND ee.employee.rowid = :employeeId
            AND FORMATDATETIME(ee.accomplishedAt, 'yyyy-MM-dd') = FORMATDATETIME(:accomplishedAt, 'yyyy-MM-dd')
            """)
    boolean existsByExamIdAndEmployeeIdAndAccomplishedAt(@Param("examId") Long examId, @Param("employeeId") Long employeeId, @Param("accomplishedAt") LocalDateTime accomplishedAt);

    @Query("""
            SELECT COUNT(ee) > 0 FROM EmployeeExam ee
            WHERE ee.exam.rowid = :examId
            AND ee.employee.rowid = :employeeId
            AND FORMATDATETIME(ee.accomplishedAt, 'yyyy-MM-dd') = FORMATDATETIME(:accomplishedAt, 'yyyy-MM-dd')
            AND ee.rowid <> :excludeRowId
            """)
    boolean existsByExamIdAndEmployeeIdAndAccomplishedAt(@Param("examId") Long examId, @Param("employeeId") Long employeeId, @Param("accomplishedAt") LocalDateTime accomplishedAt, @Param("excludeRowId") Long excludeRowId);

    @Query(value = """
            SELECT fc.rowid AS rowIdEmployee, fc.name AS nomeEmployee, ex.rowid AS rowIdExam, ex.name AS nomeExam, ef.accomplished_at AS accomplishedAt FROM EMPLOYEE_EXAM ef
                JOIN EXAM ex ON ex.rowid = ef.exam_id
                JOIN EMPLOYEE fc ON fc.rowid = ef.EMPLOYEE_id
                WHERE ef.accomplished_at BETWEEN  :initialDate AND :finishDate
            """, nativeQuery = true)
    List<ReportByPeriod> getReportByPeriod(@Param("initialDate") LocalDate initialDate, @Param("finishDate") LocalDate finishDate);

}
