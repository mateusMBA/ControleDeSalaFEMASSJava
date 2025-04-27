package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Disciplina;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.DisciplinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> getDisciplinaById(int id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Disciplina updateDisciplina(int id, Disciplina updatedDisciplina) {
        Disciplina existingDisciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada com o ID: " + id));

        existingDisciplina.setNome(updatedDisciplina.getNome());
        existingDisciplina.setNecessitaLaboratiorio(updatedDisciplina.getNecessitaLaboratiorio());
        existingDisciplina.setNecessitaArCondicionado(updatedDisciplina.getNecessitaArCondicionado());
        existingDisciplina.setNecessitaLousaDigital(updatedDisciplina.getNecessitaLousaDigital());
        return disciplinaRepository.save(existingDisciplina);
    }

    public boolean deleteDisciplina(int id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
            return true;
        }
        return false; // Or throw an exception indicating the disciplina was not found
    }
}
