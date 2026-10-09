package br.com.fiap.orbitaverde.repository;

import br.com.fiap.orbitaverde.model.Satelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SateliteRepository extends JpaRepository<Satelite, Long> {

    Optional<Satelite> findByNome(String nome);

    List<Satelite> findByAgencia(String agencia);

    List<Satelite> findByTipo(String tipo);

    List<Satelite> findByStatus(String status);
}
