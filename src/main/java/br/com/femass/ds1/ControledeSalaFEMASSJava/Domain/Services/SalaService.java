package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Sala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    @Autowired
    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> getAllSalas() {
        return salaRepository.findAll();
    }

    public Optional<Sala> getSalaById(int id) {
        return salaRepository.findById(id);
    }

    public Sala createSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public void deleteSala(int id) {
        salaRepository.deleteById(id);
    }
}
