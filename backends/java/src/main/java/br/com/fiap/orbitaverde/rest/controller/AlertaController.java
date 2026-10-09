package br.com.fiap.orbitaverde.rest.controller;

import br.com.fiap.orbitaverde.model.Alerta;
import br.com.fiap.orbitaverde.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * API REST - CRUD completo de Alertas.
 *
 * Endpoints:
 * GET    /api/alertas              - lista todos
 * GET    /api/alertas/{id}         - busca por ID
 * GET    /api/alertas/ativos       - lista nao resolvidos
 * GET    /api/alertas/tipo/{tipo}  - filtra por tipo
 * GET    /api/alertas/nivel/{nivel}- filtra por nivel
 * GET    /api/alertas/relatorios   - relatorios (demonstra polimorfismo)
 * POST   /api/alertas              - cria (integra com SOAP)
 * PUT    /api/alertas/{id}         - atualiza
 * PATCH  /api/alertas/{id}/resolver- resolve alerta
 * DELETE /api/alertas/{id}         - deleta
 */
@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    // ======================== GET ========================

    @GetMapping
    public ResponseEntity<List<Alerta>> listarTodos() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return alertaService.buscarPorId(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                    "erro", "Alerta nao encontrado",
                    "id", id,
                    "timestamp", LocalDateTime.now().toString()
                )));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Alerta>> listarAtivos() {
        return ResponseEntity.ok(alertaService.listarAtivos());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Alerta>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(alertaService.listarPorTipo(tipo));
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Alerta>> listarPorNivel(@PathVariable String nivel) {
        return ResponseEntity.ok(alertaService.listarPorNivel(nivel));
    }

    @GetMapping("/relatorios")
    public ResponseEntity<List<String>> gerarRelatorios() {
        return ResponseEntity.ok(alertaService.gerarRelatorios());
    }

    // ======================== POST ========================

    /**
     * Cria novo alerta.
     * INTEGRACAO SOA: internamente consulta servico SOAP para validar satelite.
     */
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Alerta alerta) {
        try {
            Alerta criado = alertaService.criar(alerta);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                    "erro", e.getMessage(),
                    "timestamp", LocalDateTime.now().toString()
                ));
        }
    }

    // ======================== PUT ========================

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                        @Valid @RequestBody Alerta alerta) {
        return alertaService.atualizar(id, alerta)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                    "erro", "Alerta nao encontrado para atualizacao",
                    "id", id,
                    "timestamp", LocalDateTime.now().toString()
                )));
    }

    // ======================== PATCH ========================

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<?> resolver(@PathVariable Long id) {
        return alertaService.resolver(id)
            .<ResponseEntity<?>>map(a -> ResponseEntity.ok(Map.of(
                "mensagem", "Alerta #" + id + " marcado como resolvido.",
                "alerta", a
            )))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                    "erro", "Alerta nao encontrado",
                    "id", id
                )));
    }

    // ======================== DELETE ========================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (alertaService.deletar(id)) {
            return ResponseEntity.ok(Map.of(
                "mensagem", "Alerta #" + id + " deletado com sucesso.",
                "timestamp", LocalDateTime.now().toString()
            ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of(
                "erro", "Alerta nao encontrado",
                "id", id,
                "timestamp", LocalDateTime.now().toString()
            ));
    }
}
