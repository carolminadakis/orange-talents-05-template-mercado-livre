package br.com.zupacademy.anaminadakis.mercadolivre.imagens.model;

import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @Valid
    private Produto produto;

    @NotBlank
    @URL
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(Produto produto,
                         String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagemProduto)) return false;

        ImagemProduto that = (ImagemProduto) o;

        if (!produto.equals(that.produto)) return false;
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        int result = produto.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", produto=" + produto +
                ", link='" + link + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }
}
