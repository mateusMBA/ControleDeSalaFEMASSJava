package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "tb_disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disciplina")
    private int id;

    @NotBlank(message = "nome da disciplina é obrigatório")
    @Column(name = "nome_disciplina")
    private String nome;

    @NotNull(message = "informação sobre laboratório é obrigatória")
    @Column(name = "laboratorio")
    private boolean necessitaLaboratiorio;

    @NotNull(message = "informação sobre ar-condicionado é obrigatória")
    @Column(name = "ar_condicionado")
    private boolean necessitaArCondicionado;

    @NotNull(message = "informação sobre lousa é obrigatória")
    @Column(name = "lousa_digital")
    private boolean necessitaLousaDigital;

    public int getId() {
        return id;
    }

    public @NotBlank(message = "nome da disciplina é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "nome da disciplina é obrigatório") String nome) {
        this.nome = nome;
    }

    @NotNull(message = "informação sobre laboratório é obrigatória")
    public boolean isNecessitaLaboratiorio() {
        return necessitaLaboratiorio;
    }

    public void setNecessitaLaboratiorio(@NotNull(message = "informação sobre laboratório é obrigatória") boolean necessitaLaboratiorio) {
        this.necessitaLaboratiorio = necessitaLaboratiorio;
    }

    @NotNull(message = "informação sobre ar-condicionado é obrigatória")
    public boolean isNecessitaArCondicionado() {
        return necessitaArCondicionado;
    }

    public void setNecessitaArCondicionado(@NotNull(message = "informação sobre ar-condicionado é obrigatória") boolean necessitaArCondicionado) {
        this.necessitaArCondicionado = necessitaArCondicionado;
    }

    @NotNull(message = "informação sobre lousa é obrigatória")
    public boolean isNecessitaLousaDigital() {
        return necessitaLousaDigital;
    }

    public void setNecessitaLousaDigital(@NotNull(message = "informação sobre lousa é obrigatória") boolean necessitaLousaDigital) {
        this.necessitaLousaDigital = necessitaLousaDigital;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", necessitaLaboratiorio=" + necessitaLaboratiorio +
                ", necessitaArCondicionado=" + necessitaArCondicionado +
                ", necessitaLousaDigital=" + necessitaLousaDigital +
                '}';
    }
}
