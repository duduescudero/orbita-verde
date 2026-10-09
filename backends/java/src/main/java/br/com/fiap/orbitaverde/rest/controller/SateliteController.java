package br.com.fiap.orbitaverde.rest.controller;

import br.com.fiap.orbitaverde.model.Satelite;
import br.com.fiap.orbitaverde.service.SateliteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * API REST - CRUD de Satelites (tambem exposto via SOAP).
 * Demonstra como o mesmo recurso pode ser acessado por REST e SOAP.
 */
@RestController
@RequestMapping("/api/satelites")
public class SateliteController {

    @Autowired
    private SateliteService sateliteService;

    @GetMapping
    public ResponseEntity<List<Satelite>> listarTodos() {
        return ResponseEntity.ok(sateliteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return sateliteService.buscarPorId(id)
            .<ResponseEntity<?>>map(s -> ResponseEntity.ok(Map.of(
                "satelite", s,
                "tipoMonitoramento", s.getTipoMonitoramento(),
                "relatorio", s.gerarRelatorio()
            )))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Satelite nao encontrado", "id", id)));
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Satelite satelite) {
        try {
            Satelite criado = sateliteService.cadastrar(satelite);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", e.getMessage(), "timestamp", LocalDateTime.now().toString()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                        @Valid @RequestBody Satelite satelite) {
        return sateliteService.atualizar(id, satelite)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Satelite nao encontrado", "id", id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (sateliteService.deletar(id)) {
            return ResponseEntity.ok(Map.of("mensagem", "Satelite #" + id + " deletado."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("erro", "Satelite nao encontrado", "id", id));
    }

    @GetMapping("/relatorios")
    public ResponseEntity<List<String>> relatorios() {
        List<String> relatorios = sateliteService.listarTodos()
            .stream()
            .map(Satelite::gerarRelatorio)
            .toList();
        return ResponseEntity.ok(relatorios);
    }
}
