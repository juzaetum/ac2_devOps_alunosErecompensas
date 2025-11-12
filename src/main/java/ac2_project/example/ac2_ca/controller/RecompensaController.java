package ac2_project.example.ac2_ca.controller;

import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.service.RecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recompensas")
@CrossOrigin(origins = "*") 
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    @GetMapping
    public ResponseEntity<List<Recompensa>> getAll() {
        List<Recompensa> recompensas = recompensaService.getAllRecompensas();
        return ResponseEntity.ok(recompensas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> getById(@PathVariable Long id) {
        Optional<Recompensa> recompensa = recompensaService.getRecompensaById(id);
        return recompensa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Recompensa> create(@RequestBody Recompensa recompensa) {
        Recompensa nova = recompensaService.createRecompensa(recompensa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recompensa> update(@PathVariable Long id, @RequestBody Recompensa recompensa) {
        Optional<Recompensa> atualizada = recompensaService.updateRecompensa(id, recompensa);
        return atualizada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = recompensaService.deleteRecompensa(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
