package ac2_project.example.ac2_ca.controller;

import ac2_project.example.ac2_ca.dto.ProfessorDTO;
import ac2_project.example.ac2_ca.entity.Professor;
import ac2_project.example.ac2_ca.service.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getAll() {
        return professorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getById(@PathVariable Long id) {
        return professorService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody ProfessorDTO dto) {
        return ResponseEntity.ok(professorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody ProfessorDTO dto) {
        return professorService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (professorService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
