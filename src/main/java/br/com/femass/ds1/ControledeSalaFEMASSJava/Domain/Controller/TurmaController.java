package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Controller;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Turma;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable int id) {
        turmaService.deleteTurma(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Example of a controller endpoint to find turmas by disciplina
    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Turma>> getTurmasByDisciplina(@PathVariable int disciplinaId) {
        // Assuming you have a way to retrieve the Disciplina entity by its ID
        // For example, using a DisciplinaService
        // Disciplina disciplina = disciplinaService.getDisciplinaById(disciplinaId).orElse(null);
        // if (disciplina == null) {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        // List<Turma> turmas = turmaService.getTurmasByDisciplina(disciplina);
        // return new ResponseEntity<>(turmas, HttpStatus.OK);

        // For simplicity, let's assume you pass the Disciplina object directly in the request body for this example endpoint
        // In a real application, you'd likely fetch the Disciplina based on an ID.
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
