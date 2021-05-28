package br.com.zupacademy.anaminadakis.mercadolivre.categorias.controller.request;

import br.com.zupacademy.anaminadakis.mercadolivre.categorias.model.Categoria;
import br.com.zupacademy.anaminadakis.mercadolivre.categorias.repository.CategoriaRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.config.ExistsId;
import br.com.zupacademy.anaminadakis.mercadolivre.config.UniqueValue;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class CategoriaRequest {

    @NotBlank(message = "Campo obrigatório")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;


    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @Positive   //idCategoriaMae pode ser nulo, ou positivo
    private Long idCategoriaMae;

    public void setNome(String nome) {
        this.nome = nome;
    }   //podemos usar o setter pois essa categoria é formada por um atributo de informação obrigatória

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }   //e um atributo de informação opcional

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = categoriaRepository.getById(idCategoriaMae);
            Assert.notNull(categoriaMae, "O id da categoria mãe precisa ser válido");
            categoria.addCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
/*
No método converter, instanciamos uma Categoria, passando o atributo nome, e caso o idCategoriaMae seja diferente de nulo,
pesquisamos no banco de dados, a existência de um idCategoriaMae, que retorna um objeto do tipo Categoria, nos certificamos
de que o idCategoriaMae é válido (ou seja, não é nulo),  com o uso do Assert.notNull, e passamos para o método addCategoriaMae, que vai settar o atributo categoriaMae
na entidade Categoria. O método converter retorna a categoria populada, que por sua vez, no controller, irá popular o meu
atributo do tipo Categoria, que será salvo no banco de dados.
 */
