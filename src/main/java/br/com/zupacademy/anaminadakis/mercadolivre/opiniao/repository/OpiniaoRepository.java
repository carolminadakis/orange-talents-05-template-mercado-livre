package br.com.zupacademy.anaminadakis.mercadolivre.opiniao.repository;

import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.model.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
}
