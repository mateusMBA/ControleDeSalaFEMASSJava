package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Controller;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.AlocacaoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Disciplina;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Sala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Turma;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.AlocacaoSalaService;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.DisciplinaService;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.SalaService;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private AlocacaoSalaService alocacaoSalaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Turma>> getAllTurmas() {
        List<Turma> turmas = turmaService.getAllTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable int id) {
        Optional<Turma> turma = turmaService.getTurmaById(id);
        return turma.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
        Turma savedTurma = turmaService.createTurma(turma);
        return new ResponseEntity<>(savedTurma, HttpStatus.CREATED);
    }

    @PostMapping("/alocar-turma")
    public ResponseEntity<AlocacaoSala> alocarTurma(@RequestBody int idTurma ,@RequestBody int idSala, @RequestBody DayOfWeek diaSemana, @RequestBody TempoSala tempo) {
        Turma turma = turmaService.getTurmaById(idTurma).orElseThrow();
        Sala sala = salaService.getSalaById(idSala).orElseThrow();
        AlocacaoSala alocacaoSala = new AlocacaoSala(sala, turma, diaSemana, tempo);
        alocacaoSalaService.createAlocacao(alocacaoSala);
        return new ResponseEntity<>(alocacaoSala, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable int id, @RequestBody @Valid Turma updatedTurma) {
        Turma turma = turmaService.updateTurma(id, updatedTurma);
        return ResponseEntity.ok(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable int id) {
        turmaService.deleteTurma(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletar-alocacao/{id}")
    public ResponseEntity<Void> deletarAlocacao(@PathVariable int id) {
        alocacaoSalaService.deleteAlocacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Turma>> getTurmasByDisciplina(@PathVariable int disciplinaId) {
         Disciplina disciplina = disciplinaService.getDisciplinaById(disciplinaId).orElse(null);
         if (disciplina == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         List<Turma> turmas = turmaService.getTurmasByDisciplina(disciplina);
         return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
}
