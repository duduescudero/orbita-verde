package br.com.fiap.orbitaverde.soap.endpoint;

import br.com.fiap.orbitaverde.model.Satelite;
import br.com.fiap.orbitaverde.service.SateliteService;
import br.com.fiap.orbitaverde.soap.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

/**
 * Web Service SOAP - Endpoint de Satelites.
 *
 * Expoe 2 operacoes conforme requisito do enunciado:
 * 1. consultarSatelite  - consulta um satelite por ID
 * 2. cadastrarSatelite  - cadastra um novo satelite
 *
 * WSDL disponivel em: http://localhost:8085/ws/satelite.wsdl
 */
@Endpoint
public class SateliteEndpoint {

    private static final String NAMESPACE_URI = "http://fiap.com.br/orbitaverde/soap";

    @Autowired
    private SateliteService sateliteService;

    // ======================== OPERACAO 1: CONSULTAR ========================

    /**
     * Consulta um satelite pelo ID via SOAP.
     * Operacao de consulta obrigatoria (enunciado SOA).
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "consultarSateliteRequest")
    @ResponsePayload
    public ConsultarSateliteResponse consultarSatelite(
            @RequestPayload ConsultarSateliteRequest request) {

        ConsultarSateliteResponse response = new ConsultarSateliteResponse();

        Optional<Satelite> sateliteOpt = sateliteService.buscarPorId(request.getId());

        if (sateliteOpt.isPresent()) {
            Satelite s = sateliteOpt.get();
            SateliteType sateliteType = toSateliteType(s);
            response.setSatelite(sateliteType);
            response.setMensagem("Satelite encontrado com sucesso.");
        } else {
            response.setMensagem("Satelite com ID " + request.getId() + " nao encontrado.");
        }

        return response;
    }

    // ======================== OPERACAO 2: CADASTRAR ========================

    /**
     * Cadastra um novo satelite via SOAP.
     * Operacao de cadastro/processamento obrigatoria (enunciado SOA).
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "cadastrarSateliteRequest")
    @ResponsePayload
    public CadastrarSateliteResponse cadastrarSatelite(
            @RequestPayload CadastrarSateliteRequest request) {

        CadastrarSateliteResponse response = new CadastrarSateliteResponse();

        try {
            Satelite satelite = new Satelite();
            satelite.setNome(request.getNome());
            satelite.setTipo(request.getTipo());
            satelite.setAltitude(request.getAltitude());
            satelite.setAgencia(request.getAgencia());
            satelite.setStatus(request.getStatus() != null ? request.getStatus() : "ATIVO");

            Satelite salvo = sateliteService.cadastrar(satelite);

            response.setId(salvo.getId());
            response.setMensagem("Satelite '" + salvo.getNome() + "' cadastrado com sucesso.");
        } catch (Exception e) {
            response.setId(-1);
            response.setMensagem("Erro ao cadastrar satelite: " + e.getMessage());
        }

        return response;
    }

    // ======================== HELPER ========================

    private SateliteType toSateliteType(Satelite s) {
        SateliteType st = new SateliteType();
        st.setId(s.getId());
        st.setNome(s.getNome());
        st.setTipo(s.getTipo());
        st.setAltitude(s.getAltitude());
        st.setAgencia(s.getAgencia());
        st.setStatus(s.getStatus());
        st.setDataCadastro(s.getDataCadastro() != null ? s.getDataCadastro().toString() : "N/A");
        st.setRelatorio(s.gerarRelatorio()); // polimorfismo
        return st;
    }
}
