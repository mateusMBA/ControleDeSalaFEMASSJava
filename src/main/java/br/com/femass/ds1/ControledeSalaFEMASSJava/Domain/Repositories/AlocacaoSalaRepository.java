package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.AlocacaoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoSalaRepository extends JpaRepository<AlocacaoSala, Integer> {
}
