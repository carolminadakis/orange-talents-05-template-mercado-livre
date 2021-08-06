package br.com.zupacademy.anaminadakis.mercadolivre.detalhaproduto.dto;

import br.com.zupacademy.anaminadakis.mercadolivre.imagens.dto.ImagemDto;
import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.dto.OpiniaoDto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhaProdutoDto {

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Set<CaracteristicaDto> caracteristicas;
    private Set<ImagemDto> imagens;
    private List<OpiniaoDto> opinioes;
    private int totalNotas;
    private BigDecimal mediaDeNotas;

//Faz todo o detalhamento do produto que será exibido
    public DetalhaProdutoDto(Produto produto) {
        this.nome = produto
                .getNome();
        this.valor = produto
                .getPreco();
        this.descricao = produto
                .getDescricao();
        this.caracteristicas = produto
                .getCaracteristicas()
                .stream()
                .map(caracteristica -> new CaracteristicaDto(caracteristica))
                .collect(Collectors.toSet());
        this.imagens = produto
                .getImagens()
                .stream()
                .map(imagem -> new ImagemDto(imagem))
                .collect(Collectors.toSet());
        this.totalNotas = produto
                .getTotalOpinioes();
        this.opinioes = produto
                .getOpinioes()
                .stream()
                .map(opiniao -> new OpiniaoDto(opiniao))
                .collect(Collectors.toList());
        this.mediaDeNotas = produto
                .getMediaNotas();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemDto> getImagens() {
        return imagens;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public int getTotalNotas() {
        return totalNotas;
    }

    public BigDecimal getMediaDeNotas() {
        return mediaDeNotas;
    }
}
