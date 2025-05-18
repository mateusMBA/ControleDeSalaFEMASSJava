package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Indisponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface IndisponibilidadeRepository extends JpaRepository<Indisponibilidade, Integer> {
    List<Indisponibilidade> findByDiaSemanaAndTempo(DayOfWeek diaSemana, TempoSala tempo);
}