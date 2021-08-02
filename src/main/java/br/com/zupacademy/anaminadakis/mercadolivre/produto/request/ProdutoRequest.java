package br.com.zupacademy.anaminadakis.mercadolivre.produto.request;

import br.com.zupacademy.anaminadakis.mercadolivre.categorias.model.Categoria;
import br.com.zupacademy.anaminadakis.mercadolivre.config.validacoes.ExistsId;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @Positive
    private int quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();


    public ProdutoRequest(String nome,
                          BigDecimal preco,
                          int quantidadeDisponivel,
                          String descricao,
                          Long categoriaId,
                          List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto converte(Categoria categoria, Usuario dono) {
        return new Produto(nome, preco, quantidadeDisponivel, descricao, categoria, dono, caracteristicas);
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}
