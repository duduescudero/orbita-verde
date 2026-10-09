package br.com.fiap.orbitaverde.service;

import br.com.fiap.orbitaverde.integration.SateliteIntegrationService;
import br.com.fiap.orbitaverde.model.Alerta;
import br.com.fiap.orbitaverde.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servico de negocio para Alerta.
 *
 * INTEGRACAO SOA: ao criar um novo alerta, consulta o servico SOAP
 * para validar e enriquecer os dados do satelite de origem.
 */
@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private SateliteIntegrationService sateliteIntegrationService;

    /**
     * Lista todos os alertas.
     */
    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    /**
     * Busca alerta por ID.
     */
    public Optional<Alerta> buscarPorId(Long id) {
        return alertaRepository.findById(id);
    }

    /**
     * Lista alertas por tipo.
     */
    public List<Alerta> listarPorTipo(String tipo) {
        return alertaRepository.findByTipo(tipo.toUpperCase());
    }

    /**
     * Lista alertas por nivel.
     */
    public List<Alerta> listarPorNivel(String nivel) {
        return alertaRepository.findByNivel(nivel.toUpperCase());
    }

    /**
     * Lista alertas nao resolvidos.
     */
    public List<Alerta> listarAtivos() {
        return alertaRepository.findByResolvido(false);
    }

    /**
     * Cria novo alerta.
     *
     * INTEGRACAO SOA: chama o servico de integracao SOAP para validar
     * o satelite de origem antes de persistir o alerta.
     */
    public Alerta criar(Alerta alerta) {
        // INTEGRACAO: consulta o SOAP service para validar/enriquecer satelite
        if (alerta.getSateliteOrigem() != null && !alerta.getSateliteOrigem().isBlank()) {
            String infoSatelite = sateliteIntegrationService.consultarPorNome(alerta.getSateliteOrigem());
            if (infoSatelite != null) {
                // Enriquece a descricao com informacoes do satelite vindas do SOAP
                alerta.setDescricao(alerta.getDescricao() + " [Validado via SOAP: " + infoSatelite + "]");
            }
        }
        return alertaRepository.save(alerta);
    }

    /**
     * Atualiza alerta existente.
     */
    public Optional<Alerta> atualizar(Long id, Alerta dados) {
        return alertaRepository.findById(id).map(a -> {
            a.setTipo(dados.getTipo());
            a.setDescricao(dados.getDescricao());
            a.setLatitude(dados.getLatitude());
            a.setLongitude(dados.getLongitude());
            a.setNivel(dados.getNivel());
            a.setSateliteOrigem(dados.getSateliteOrigem());
            a.setStatus(dados.getStatus());
            return alertaRepository.save(a);
        });
    }

    /**
     * Marca alerta como resolvido.
     */
    public Optional<Alerta> resolver(Long id) {
        return alertaRepository.findById(id).map(a -> {
            a.setResolvido(true);
            a.setStatus("RESOLVIDO");
            return alertaRepository.save(a);
        });
    }

    /**
     * Deleta alerta por ID.
     */
    public boolean deletar(Long id) {
        if (alertaRepository.existsById(id)) {
            alertaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Retorna relatorio de todos os alertas (demonstra polimorfismo).
     */
    public List<String> gerarRelatorios() {
        return alertaRepository.findAll()
            .stream()
            .map(Alerta::gerarRelatorio) // polimorfismo
            .toList();
    }
}
