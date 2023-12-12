package br.com.sbs.avaliacaodevspring.realizado;

import br.com.sbs.avaliacaodevspring.exame.Exame;
import br.com.sbs.avaliacaodevspring.funcionario.Funcionario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXAME_FUNCIONARIO")
public class ExameFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;
    @NotNull
    @PastOrPresent
    @Column(name = "realizado_em")
    private LocalDateTime realizadoEm;
    @NotNull
    @JoinColumn(name = "exame_id")
    @ManyToOne
    private Exame exame;
    @NotNull
    @JoinColumn(name = "funcionario_id")
    @ManyToOne
    private Funcionario funcionario;

    public ExameFuncionario() {}

    public ExameFuncionario(Exame exame, Funcionario funcionario) {
        this.realizadoEm = LocalDateTime.now();
        this.exame = exame;
        this.funcionario = funcionario;
    }

    public String getNomeExame() {
        return getExame().getNome();
    }

    public String getNomeFuncionario() {
        return getFuncionario().getNome();
    }

    public Long getRowid() {
        return rowid;
    }

    public LocalDateTime getRealizadoEm() {
        return realizadoEm;
    }

    public Exame getExame() {
        return exame;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public String toString() {
        return "NewExameFuncionarioForm{" +
                "rowid=" + rowid +
                ", realizadoEm=" + realizadoEm +
                ", exameId=" + exame +
                ", funcionarioId=" + funcionario +
                '}';
    }
}