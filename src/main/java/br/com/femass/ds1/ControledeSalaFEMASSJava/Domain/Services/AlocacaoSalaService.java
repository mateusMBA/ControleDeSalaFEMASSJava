package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.AlocacaoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.AlocacaoSalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlocacaoSalaService {

    @Autowired
    private AlocacaoSalaRepository alocacaoSalaRepository;

    @Autowired
    private SalaService salaService;

    @Autowired
    private TurmaService turmaService;

    public List<AlocacaoSala> getAllAlocacoes() {
        return alocacaoSalaRepository.findAll();
    }

    public Optional<AlocacaoSala> getAlocacaoById(int id) {
        return alocacaoSalaRepository.findById(id);
    }

    public AlocacaoSala createAlocacao(AlocacaoSala alocacao) {
        return alocacaoSalaRepository.save(alocacao);
    }

    @Transactional
    public AlocacaoSala updateAlocacao(int id, AlocacaoSala updatedAlocacao) {
        AlocacaoSala existingAlocacao = alocacaoSalaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alocacao não encontrada com o ID: " + id));
        // You might want to add validation to ensure the associated Sala and Turma exist
        if (updatedAlocacao.getSala() != null && salaService.getSalaById(updatedAlocacao.getSala().getId()).isEmpty()) {
            throw new IllegalArgumentException("Sala com o ID " + updatedAlocacao.getSala().getId() + " não encontrada.");
        }
        if (updatedAlocacao.getTurma() != null && turmaService.getTurmaById(updatedAlocacao.getTurma().getId()).isEmpty()) {
            throw new IllegalArgumentException("Turma com o ID " + updatedAlocacao.getTurma().getId() + " não encontrada.");
        }

        existingAlocacao.setSala(updatedAlocacao.getSala());
        existingAlocacao.setTurma(updatedAlocacao.getTurma());
        existingAlocacao.setDiaSemana(updatedAlocacao.getDiaSemana());
        existingAlocacao.setTempo(updatedAlocacao.getTempo());
        return alocacaoSalaRepository.save(existingAlocacao);
    }

    public void deleteAlocacao(int id) {
        alocacaoSalaRepository.deleteById(id);
    }
}
