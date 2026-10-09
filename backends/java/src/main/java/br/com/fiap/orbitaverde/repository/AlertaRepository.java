package br.com.fiap.orbitaverde.repository;

import br.com.fiap.orbitaverde.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findByTipo(String tipo);

    List<Alerta> findByNivel(String nivel);

    List<Alerta> findByResolvido(boolean resolvido);

    List<Alerta> findBySateliteOrigem(String sateliteOrigem);
}
