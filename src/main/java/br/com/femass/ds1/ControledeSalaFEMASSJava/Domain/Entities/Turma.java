package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private int id;

    private String professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    private int quantidadeAlunos;

    private int codigoHorario;

    private boolean turmaGrandeAntiga;

//    private List<Turma> turmasGradeAntiga;
//
//    private int turmaId;
//
//    private int totalQuantidadeAlunosAgrupados;

    //private List<AlocacaoSala> alocacoes;

}
