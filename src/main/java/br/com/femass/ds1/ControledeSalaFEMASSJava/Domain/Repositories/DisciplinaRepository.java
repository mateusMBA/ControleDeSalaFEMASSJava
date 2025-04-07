package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
