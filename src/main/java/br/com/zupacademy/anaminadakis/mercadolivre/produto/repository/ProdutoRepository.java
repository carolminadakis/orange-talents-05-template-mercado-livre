package br.com.zupacademy.anaminadakis.mercadolivre.produto.repository;

import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
