package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Disciplina;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Turma;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return turmaRepository.save(turma);
    }

    @Transactional
    public Turma updateTurma(int id, Turma turmaAtualizada) {
        Turma existingTurma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada com o ID: " + id));

        existingTurma.setProfessor(turmaAtualizada.getProfessor());
        existingTurma.setDisciplina(turmaAtualizada.getDisciplina());
        existingTurma.setQuantidadeAlunos(turmaAtualizada.getQuantidadeAlunos());
        existingTurma.setCodigoHorario(turmaAtualizada.getCodigoHorario());
        existingTurma.setTurmaGrandeAntiga(turmaAtualizada.getTurmaGrandeAntiga());
        return turmaRepository.save(existingTurma);
    }

    public void deleteTurma(int id) {
        turmaRepository.deleteById(id);
    }

    public List<Turma> getTurmasByDisciplina(Disciplina disciplina) {
        return turmaRepository.findByDisciplina(disciplina);
    }
}
