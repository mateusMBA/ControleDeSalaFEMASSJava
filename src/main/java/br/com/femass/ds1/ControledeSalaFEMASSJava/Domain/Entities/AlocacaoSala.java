package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;

@Entity
@Table(name = "tb_alocacao_sala")
public class AlocacaoSala {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alocacao")
    private int id;

    @NotNull(message =  "Sala é obrigatória")
    @JoinColumn(name = "id_sala")
    @ManyToOne
    private Sala sala;

    @NotNull(message = "Turma é obrigatória")
    @JoinColumn(name = "id_turma")
    @ManyToOne
    private Turma turma;

    @NotNull(message = "Dia da semana é obrigatório!")
    @Column(name = "dia_semana")
    private DayOfWeek diaSemana;

    @NotNull(message = "Tempo é obrigatório!")
    @Column(name = "tempo")
    private TempoSala tempo;

    public AlocacaoSala(Sala sala, Turma turma, DayOfWeek diaSemana, TempoSala tempo) {
        this.sala = sala;
        this.turma = turma;
        this.diaSemana = diaSemana;
        this.tempo = tempo;
    }

    public int getId() {
        return id;
    }

    public @NotNull(message = "Sala é obrigatória") Sala getSala() {
        return sala;
    }

    public void setSala(@NotNull(message = "Sala é obrigatória") Sala sala) {
        this.sala = sala;
    }

    public @NotNull(message = "Turma é obrigatória") Turma getTurma() {
        return turma;
    }

    public void setTurma(@NotNull(message = "Turma é obrigatória") Turma turma) {
        this.turma = turma;
    }

    public @NotNull(message = "Dia da semana é obrigatório!") DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(@NotNull(message = "Dia da semana é obrigatório!") DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public @NotNull(message = "Tempo é obrigatório!") TempoSala getTempo() {
        return tempo;
    }

    public void setTempo(@NotNull(message = "Tempo é obrigatório!") TempoSala tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "AlocacaoSala{" +
                "id=" + id +
                ", sala=" + sala +
                ", turma=" + turma +
                ", diaSemana=" + diaSemana +
                ", tempo=" + tempo +
                '}';
    }
}
