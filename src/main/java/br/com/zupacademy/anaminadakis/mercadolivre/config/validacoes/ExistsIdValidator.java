package br.com.zupacademy.anaminadakis.mercadolivre.config.validacoes;

import br.com.zupacademy.anaminadakis.mercadolivre.config.validacoes.ExistsId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    private String domainAttribute;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ExistsId parametros) {
        domainAttribute = parametros.fieldName();
        clazz = parametros.domainClass();
    }

    @Override
    public boolean isValid(Long valor, ConstraintValidatorContext contexto) {
        if (valor != null) {    //fiz essa validação pois quando ia cadastrar a primeira categoria não era aceita, por não obter categoria mãe
        Query q = em.createQuery("SELECT 1 FROM "+clazz.getName()+" WHERE "+domainAttribute+"=:value");
        q.setParameter("value", valor);

        List<?> list = q.getResultList();
        Assert.isTrue(list.size() <=1, "você tem mais de um "+clazz+" com o atributo "+domainAttribute+" com o valor = "+valor);

        return !list.isEmpty(); //caso tente cadastrar uma categoria mais de uma vez, ele retorna o erro
        }
        return true;    //no caso de ser nulo (como a primeira categoria, a validação não irá dar erro.
    }
}
