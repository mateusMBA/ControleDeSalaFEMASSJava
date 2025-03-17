package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int id;

    @NotBlank(message="Bloco é obrigatória")
    @Column(name="bloco")
    private String bloco;

    @NotBlank(message="Número é obrigatório")
    @Column(name="numero")
    private long numero;

    @NotBlank(message="Capacidade máxima é obrigatório")
    @Column(name="capacidade_maxima")
    private int capacidadeMaxima;

    @NotBlank(message="Informação sobre laboratório é obrigatória")
    @Column(name="laboratorio")
    private boolean possuiLaboratorio;

    @NotBlank(message="Informação sobre ar-condicionado é obrigatória")
    @Column(name="ar_condicionado")
    private boolean possuiArCondicionado;

    @NotBlank(message="Informação sobre lousa digital é obrigatória")
    @Column(name="lousa_digital")
    private boolean possuiLousaDigital;

//    private List<Indisponibilidade> indisponibilidades;
//
//    private List<AlocacaoSala> alocacoes;
}
