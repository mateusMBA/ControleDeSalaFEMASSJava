package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_alocacao_sala")
public class AlocacaoSala {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alocacao")
    private int id;

    @NotBlank(message =  "Sala é obrigatória")
    @JoinColumn(name = "id_sala")
    @ManyToOne
    private Sala sala;

    @NotBlank(message = "Turma é obrigatória")
    @JoinColumn(name = "id_turma")
    @ManyToOne
    private Turma turma;

    @NotBlank(message = "Dia da semana é obrigatório!")
    @Column(name = "dia_semana")
    private DayOfWeek diaSemana;

    @NotBlank(message = "Tempo é obrigatório!")
    @Column(name = "tempo")
    private TempoSala tempo;
}
