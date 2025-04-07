package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.AlocacaoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.AlocacaoSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlocacaoSalaService {

    @Autowired
    private AlocacaoSalaRepository repository;

    public List<AlocacaoSala> getAllAlocacoes() {
        return repository.findAll();
    }

    public Optional<AlocacaoSala> getAlocacaoById(int id) {
        return repository.findById(id);
    }

    public AlocacaoSala createAlocacao(AlocacaoSala alocacao) {
        return repository.save(alocacao);
    }

    public void deleteAlocacao(int id) {
        repository.deleteById(id);
    }
}
