package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.repository;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
