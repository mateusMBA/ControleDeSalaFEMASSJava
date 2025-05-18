package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Indisponibilidade;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Sala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.IndisponibilidadeRepository;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.SalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    public List<Sala> getAllSalas() {
        return salaRepository.findAll();
    }

    public Optional<Sala> getSalaById(int id) {
        return salaRepository.findById(id);
    }

    public Sala createSala(Sala sala) {
        return salaRepository.save(sala);
    }

    @Transactional
    public Sala updateSala(int id, Sala updatedSala) {
        Sala existingSala = salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada com o ID: " + id));

        existingSala.setBloco(updatedSala.getBloco());
        existingSala.setNumero(updatedSala.getNumero());
        existingSala.setCapacidadeMaxima(updatedSala.getCapacidadeMaxima());
        existingSala.setPossuiLaboratorio(updatedSala.getPossuiLaboratorio());
        existingSala.setPossuiArCondicionado(updatedSala.getPossuiArCondicionado());
        existingSala.setPossuiLousaDigital(updatedSala.getPossuiLousaDigital());
        return salaRepository.save(existingSala);
    }

    public void deleteSala(int id) {
        salaRepository.deleteById(id);
    }

    public List<Sala> getSalasDisponiveisParaAlocacao(DayOfWeek diaSemana, TempoSala tempo) {
        List<Sala> todasSalas = salaRepository.findAll();
        List<Indisponibilidade> indisponibilidadesNoPeriodo = indisponibilidadeRepository.findByDiaSemanaAndTempo(diaSemana, tempo);

        return todasSalas.stream()
                .filter(sala -> indisponibilidadesNoPeriodo.stream()
                        .noneMatch(indisponibilidade -> indisponibilidade.getSala().getId() == sala.getId()))
                .collect(Collectors.toList());
    }
}
