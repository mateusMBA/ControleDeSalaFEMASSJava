package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Controller;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Indisponibilidade;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.IndisponibilidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/indisponibilidades")
public class IndisponibilidadeController {

    @Autowired
    private IndisponibilidadeService indisponibilidadeService;

    @GetMapping
    public ResponseEntity<List<Indisponibilidade>> getAllIndisponibilidades() {
        List<Indisponibilidade> indisponibilidades = indisponibilidadeService.getAllIndisponibilidades();
        return new ResponseEntity<>(indisponibilidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indisponibilidade> getIndisponibilidadeById(@PathVariable int id) {
        Optional<Indisponibilidade> indisponibilidade = indisponibilidadeService.getIndisponibilidadeById(id);
        return indisponibilidade.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Indisponibilidade> createIndisponibilidade(@Valid @RequestBody Indisponibilidade indisponibilidade) {
        Indisponibilidade savedIndisponibilidade = indisponibilidadeService.createIndisponibilidade(indisponibilidade);
        return new ResponseEntity<>(savedIndisponibilidade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Indisponibilidade> updateIndisponibilidade(@PathVariable int id, @RequestBody @Valid Indisponibilidade updatedIndisponibilidade) {
        Indisponibilidade result = indisponibilidadeService.updateIndisponibilidade(id, updatedIndisponibilidade);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndisponibilidade(@PathVariable int id) {
        indisponibilidadeService.deleteIndisponibilidade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}