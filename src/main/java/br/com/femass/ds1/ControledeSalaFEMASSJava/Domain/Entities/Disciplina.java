package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disciplina")
    private int id;

    @Column(name = "nome_disciplina")
    private String nome;

    @NotBlank(message = "informação sobre laboratório é obrigatória")
    @Column(name = "laboratorio")
    private boolean necessitaLaboratiorio;

    @NotBlank(message = "informação sobre ar-condicionado é obrigatória")
    @Column(name = "ar_condicionado")
    private boolean necessitaArCondicionado;

    @NotBlank(message = "informação sobre lousa é obrigatória")
    @Column(name = "lousa_digital")
    private boolean necessitaLousaDigital;

}
