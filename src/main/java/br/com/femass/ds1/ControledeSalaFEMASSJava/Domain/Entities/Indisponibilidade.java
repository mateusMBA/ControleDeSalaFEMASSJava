package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;

@Entity
@Table(name = "tb_indisponibilidade")
public class Indisponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indisponibilidade")
    private int id;

    @NotNull(message =  "Sala é obrigatória")
    @JoinColumn(name = "id_sala")
    @ManyToOne
    private Sala sala;

    @NotNull(message = "Dia da semana é obrigatório!")
    @Column(name = "dia_semana")
    private DayOfWeek diaSemana;

    @NotNull(message = "Tempo é obrigatório!")
    @Column(name = "tempo")
    private TempoSala tempo;

    public int getId() {
        return id;
    }

    public @NotNull(message = "Sala é obrigatória") Sala getSala() {
        return sala;
    }

    public void setSala(@NotNull(message = "Sala é obrigatória") Sala sala) {
        this.sala = sala;
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
        return "Indisponibilidade{" +
                "id=" + id +
                ", sala=" + sala +
                ", diaSemana=" + diaSemana +
                ", tempo=" + tempo +
                '}';
    }
}
