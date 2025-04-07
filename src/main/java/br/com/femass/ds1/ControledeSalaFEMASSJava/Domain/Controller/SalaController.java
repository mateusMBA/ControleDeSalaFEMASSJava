package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Controller;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Sala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping(produces="application/json")
    public ResponseEntity<List<Sala>> getAllSalas() {
        List<Sala> salas = salaService.getAllSalas();
        return new ResponseEntity<>(salas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> getSalaById(@PathVariable int id) {
        Optional<Sala> sala = salaService.getSalaById(id);
        return sala.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Sala> createSala(@Valid @RequestBody Sala sala) {
        Sala savedSala = salaService.createSala(sala);
        return new ResponseEntity<>(savedSala, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable int id) {
        salaService.deleteSala(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
