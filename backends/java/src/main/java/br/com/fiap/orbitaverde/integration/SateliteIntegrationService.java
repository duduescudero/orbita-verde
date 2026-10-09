package br.com.fiap.orbitaverde.integration;

import br.com.fiap.orbitaverde.model.Satelite;
import br.com.fiap.orbitaverde.service.SateliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Camada de integracao entre API REST e Servico SOAP.
 *
 * Esta classe representa o ponto de integracao SOA:
 * a API REST (AlertaController) utiliza este servico para
 * consultar dados de satelites atraves da camada SOAP (SateliteService),
 * demonstrando a comunicacao entre servicos na arquitetura SOA.
 *
 * Fluxo de integracao:
 *   REST (AlertaController)
 *     -> AlertaService
 *       -> SateliteIntegrationService (esta classe)
 *         -> SateliteService (logica compartilhada com SOAP Endpoint)
 *           -> SateliteRepository (H2 Database)
 */
@Service
public class SateliteIntegrationService {

    @Autowired
    private SateliteService sateliteService;

    /**
     * Consulta satelite por nome via camada de servico SOAP.
     * Retorna relatorio gerado pelo proprio satelite (polimorfismo).
     *
     * @param nome nome do satelite
     * @return relatorio do satelite ou null se nao encontrado
     */
    public String consultarPorNome(String nome) {
        Optional<Satelite> satelite = sateliteService.buscarPorNome(nome);
        return satelite.map(Satelite::gerarRelatorio).orElse(null);
    }

    /**
     * Consulta satelite por ID via camada de servico SOAP.
     *
     * @param id identificador do satelite
     * @return objeto Satelite ou null
     */
    public Satelite consultarPorId(Long id) {
        return sateliteService.buscarPorId(id).orElse(null);
    }

    /**
     * Valida se um satelite esta ativo.
     *
     * @param nome nome do satelite
     * @return true se ativo, false caso contrario
     */
    public boolean isSateliteAtivo(String nome) {
        Optional<Satelite> satelite = sateliteService.buscarPorNome(nome);
        return satelite.map(s -> "ATIVO".equals(s.getStatus())).orElse(false);
    }
}
