package br.com.zupacademy.anaminadakis.mercadolivre.produto.model;

import br.com.zupacademy.anaminadakis.mercadolivre.categorias.model.Categoria;
import br.com.zupacademy.anaminadakis.mercadolivre.imagens.model.ImagemProduto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.request.CaracteristicaRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull @Positive
    @Column(nullable = false)
    private int quantidadeDisponivel;

    @NotBlank @Length(max = 1000)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario vendedor;

    // sempre que cadastrar um produto, quero cadastrar suas características também
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome,
                   BigDecimal preco,
                   int quantidadeDisponivel,
                   String descricao,
                   Categoria categoria,
                   Usuario vendedor,
                   @Size(min = 3) Collection<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.vendedor = vendedor;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(c -> c.converte(this))
                .collect(Collectors.toSet()));
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                ", categoria=" + categoria +
                ", vendedor=" + vendedor +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                '}';
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public String getNome() {
        return nome;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public String getEmailDoVendedor() {
        return this.vendedor.getUsername();
    }

}
