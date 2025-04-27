package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Controller;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.AlocacaoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.AlocacaoSalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoSalaController {

    @Autowired
    private AlocacaoSalaService service;

    @GetMapping
    public ResponseEntity<List<AlocacaoSala>> getAllAlocacoes() {
        return ResponseEntity.ok(service.getAllAlocacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlocacaoSala> getAlocacaoById(@PathVariable int id) {
        Optional<AlocacaoSala> alocacao = service.getAlocacaoById(id);
        return alocacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlocacaoSala> createAlocacao(@RequestBody AlocacaoSala alocacao) {
        return new ResponseEntity<>(service.createAlocacao(alocacao), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlocacaoSala> updateAlocacaoSala(@PathVariable int id, @RequestBody @Valid AlocacaoSala updatedAlocacao) {
        AlocacaoSala result = service.updateAlocacao(id, updatedAlocacao);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlocacao(@PathVariable int id) {
        service.deleteAlocacao(id);
        return ResponseEntity.noContent().build();
    }
}