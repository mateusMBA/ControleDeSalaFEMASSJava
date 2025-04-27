package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "tb_sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int id;

    @NotBlank(message="Bloco é obrigatório")
    @Column(name="bloco")
    private String bloco;

    @Min(value = 1,message="Número deve ser maior que 0")
    @Column(name="numero")
    private long numero;

    @Min(value = 1,message="Capacidade máxima deve ser maior que 0")
    @Column(name="capacidade_maxima")
    private int capacidadeMaxima;

    @NotNull(message="Informação sobre laboratório é obrigatória")
    @Column(name="laboratorio")
    private boolean possuiLaboratorio;

    @NotNull(message="Informação sobre ar-condicionado é obrigatória")
    @Column(name="ar_condicionado")
    private boolean possuiArCondicionado;

    @NotNull(message="Informação sobre lousa digital é obrigatória")
    @Column(name="lousa_digital")
    private boolean possuiLousaDigital;

    public int getId() {
        return id;
    }

    public @NotBlank(message = "Bloco é obrigatória") String getBloco() {
        return bloco;
    }

    public void setBloco(@NotBlank(message = "Bloco é obrigatória") String bloco) {
        this.bloco = bloco;
    }

    @Min(value = 1, message = "Número deve ser maior que 0")
    public long getNumero() {
        return numero;
    }

    public void setNumero(@Min(value = 1, message = "Número deve ser maior que 0") long numero) {
        this.numero = numero;
    }

    @Min(value = 1, message = "Capacidade máxima deve ser maior que 0")
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(@Min(value = 1, message = "Capacidade máxima deve ser maior que 0") int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    @NotNull(message = "Informação sobre laboratório é obrigatória")
    public boolean getPossuiLaboratorio() {
        return possuiLaboratorio;
    }

    public void setPossuiLaboratorio(@NotNull(message = "Informação sobre laboratório é obrigatória") boolean possuiLaboratorio) {
        this.possuiLaboratorio = possuiLaboratorio;
    }

    @NotNull(message = "Informação sobre ar-condicionado é obrigatória")
    public boolean getPossuiArCondicionado() {
        return possuiArCondicionado;
    }

    public void setPossuiArCondicionado(@NotNull(message = "Informação sobre ar-condicionado é obrigatória") boolean possuiArCondicionado) {
        this.possuiArCondicionado = possuiArCondicionado;
    }

    @NotNull(message = "Informação sobre lousa digital é obrigatória")
    public boolean getPossuiLousaDigital() {
        return possuiLousaDigital;
    }

    public void setPossuiLousaDigital(@NotNull(message = "Informação sobre lousa digital é obrigatória") boolean possuiLousaDigital) {
        this.possuiLousaDigital = possuiLousaDigital;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", bloco='" + bloco + '\'' +
                ", numero=" + numero +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", possuiLaboratorio=" + possuiLaboratorio +
                ", possuiArCondicionado=" + possuiArCondicionado +
                ", possuiLousaDigital=" + possuiLousaDigital +
                '}';
    }

    //    private List<Indisponibilidade> indisponibilidades;
//
//    private List<AlocacaoSala> alocacoes;
}
