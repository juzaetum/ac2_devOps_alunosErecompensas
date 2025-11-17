package ac2_project.example.ac2_ca.controller;

import ac2_project.example.ac2_ca.dto.InstituicaoDTO;
import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.service.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    @Autowired
    private InstituicaoService service;

    @GetMapping
    public List<InstituicaoDTO> getAll() {
        return service.getAll().stream().map(InstituicaoDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(i -> ResponseEntity.ok(new InstituicaoDTO(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InstituicaoDTO create(@RequestBody InstituicaoDTO dto) {
        Instituicao inst = dto.toEntity();
        return new InstituicaoDTO(service.create(inst));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoDTO> update(@PathVariable Long id, @RequestBody InstituicaoDTO dto) {
        Instituicao inst = dto.toEntity();
        return service.update(id, inst)
                .map(i -> ResponseEntity.ok(new InstituicaoDTO(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
