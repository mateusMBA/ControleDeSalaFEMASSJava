package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Indisponibilidade;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.IndisponibilidadeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IndisponibilidadeService {

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private SalaService salaService; // Assuming you have a SalaService

    public List<Indisponibilidade> getAllIndisponibilidades() {
        return indisponibilidadeRepository.findAll();
    }

    public Optional<Indisponibilidade> getIndisponibilidadeById(int id) {
        return indisponibilidadeRepository.findById(id);
    }

    public Indisponibilidade createIndisponibilidade(@Valid Indisponibilidade indisponibilidade) {
        // You might want to add business logic here, e.g., checking if the sala exists
        return indisponibilidadeRepository.save(indisponibilidade);
    }

    @Transactional
    public Indisponibilidade updateIndisponibilidade(int id, Indisponibilidade indisponibilidadeAtualizada) {
        Indisponibilidade existingIndisponibilidade = indisponibilidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));

        if (indisponibilidadeAtualizada.getSala() != null && salaService.getSalaById(indisponibilidadeAtualizada.getSala().getId()).isEmpty()) {
            throw new IllegalArgumentException("Sala com o ID " + indisponibilidadeAtualizada.getSala().getId() + " não encontrada.");
        }

        existingIndisponibilidade.setSala(indisponibilidadeAtualizada.getSala());
        existingIndisponibilidade.setDiaSemana(indisponibilidadeAtualizada.getDiaSemana());
        existingIndisponibilidade.setTempo(indisponibilidadeAtualizada.getTempo());
        return indisponibilidadeRepository.save(existingIndisponibilidade);
    }

    public void deleteIndisponibilidade(int id) {
        indisponibilidadeRepository.deleteById(id);
    }

}