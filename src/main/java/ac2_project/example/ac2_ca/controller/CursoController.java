package ac2_project.example.ac2_ca.controller;

import ac2_project.example.ac2_ca.dto.CursoDTO;
import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public List<CursoDTO> getAll() {
        return service.getAll().stream().map(CursoDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(c -> ResponseEntity.ok(new CursoDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CursoDTO create(@RequestBody CursoDTO dto) {
        Curso entity = dto.toEntity();
        return new CursoDTO(service.create(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@PathVariable Long id, @RequestBody CursoDTO dto) {
        Curso entity = dto.toEntity();
        return service.update(id, entity)
                .map(c -> ResponseEntity.ok(new CursoDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
