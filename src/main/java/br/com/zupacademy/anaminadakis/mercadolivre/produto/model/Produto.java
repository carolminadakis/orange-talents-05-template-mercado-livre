package br.com.zupacademy.anaminadakis.mercadolivre.produto.model;

import br.com.zupacademy.anaminadakis.mercadolivre.categorias.model.Categoria;
import br.com.zupacademy.anaminadakis.mercadolivre.imagens.model.ImagemProduto;
import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.model.Opiniao;
import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;
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
import java.util.*;
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

    @NotNull
    @Positive
    @Column(nullable = false)
    private int quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
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


    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas = new ArrayList<>();

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

    public Long getId() {
        return id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public int getTotalOpinioes() {
        return opinioes.size();
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public BigDecimal getMediaNotas() {
//rebebe as notas dadas em todas as opiniões, e devolve uma collection
        List<Integer> notas = this.opinioes
                .stream()
                .map(opiniao -> opiniao.getNota())
                .collect(Collectors.toList());
//soma todas as notas recebidas
        Integer totalNotas = 0;
        for (int nota : notas) {
            totalNotas += nota;
        }
        Integer tamanhoLista = notas.size();

        return BigDecimal.valueOf(totalNotas).divide(BigDecimal.valueOf(tamanhoLista));
    }

    public boolean abateEstoque(@Positive int quantidade) {
        if(this.quantidadeDisponivel >= quantidade) {
            this.quantidadeDisponivel -= quantidade;
            return true;
        }
        return false;
    }
}
