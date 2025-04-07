package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Indisponibilidade;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.IndisponibilidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IndisponibilidadeService {

    private final IndisponibilidadeRepository indisponibilidadeRepository;
    private final SalaService salaService; // Assuming you have a SalaService

    @Autowired
    public IndisponibilidadeService(IndisponibilidadeRepository indisponibilidadeRepository, SalaService salaService) {
        this.indisponibilidadeRepository = indisponibilidadeRepository;
        this.salaService = salaService;
    }

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

    public void deleteIndisponibilidade(int id) {
        indisponibilidadeRepository.deleteById(id);
    }

}