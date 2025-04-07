package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Disciplina;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Turma;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> getTurmaById(int id) {
        return turmaRepository.findById(id);
    }

    public Turma createTurma(Turma turma) {
        // You might want to add validation or business logic here before saving
        return turmaRepository.save(turma);
    }

    public void deleteTurma(int id) {
        turmaRepository.deleteById(id);
    }

    // Example of a service method to find turmas by disciplina
    public List<Turma> getTurmasByDisciplina(Disciplina disciplina) {
        return turmaRepository.findByDisciplina(disciplina);
    }

    // You can add other service methods based on your repository's custom queries
}
