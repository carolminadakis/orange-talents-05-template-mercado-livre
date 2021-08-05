package br.com.zupacademy.anaminadakis.mercadolivre.pergunta.repository;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
