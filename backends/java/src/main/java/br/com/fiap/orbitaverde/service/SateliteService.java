package br.com.fiap.orbitaverde.service;

import br.com.fiap.orbitaverde.model.Satelite;
import br.com.fiap.orbitaverde.repository.SateliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servico de negocio para Satelite.
 * Utilizado tanto pelo endpoint SOAP quanto pela camada de integracao REST.
 */
@Service
public class SateliteService {

    @Autowired
    private SateliteRepository sateliteRepository;

    public List<Satelite> listarTodos() {
        return sateliteRepository.findAll();
    }

    public Optional<Satelite> buscarPorId(Long id) {
        return sateliteRepository.findById(id);
    }

    public Optional<Satelite> buscarPorNome(String nome) {
        return sateliteRepository.findByNome(nome);
    }

    public Satelite cadastrar(Satelite satelite) {
        return sateliteRepository.save(satelite);
    }

    public Optional<Satelite> atualizar(Long id, Satelite dados) {
        return sateliteRepository.findById(id).map(s -> {
            s.setNome(dados.getNome());
            s.setTipo(dados.getTipo());
            s.setAltitude(dados.getAltitude());
            s.setAgencia(dados.getAgencia());
            s.setStatus(dados.getStatus());
            return sateliteRepository.save(s);
        });
    }

    public boolean deletar(Long id) {
        if (sateliteRepository.existsById(id)) {
            sateliteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
