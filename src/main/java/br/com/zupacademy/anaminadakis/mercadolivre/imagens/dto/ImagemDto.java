package br.com.zupacademy.anaminadakis.mercadolivre.imagens.dto;

import br.com.zupacademy.anaminadakis.mercadolivre.imagens.model.ImagemProduto;

public class ImagemDto {

    private String link;

    public ImagemDto(ImagemProduto imagemProduto) {
        this.link = imagemProduto.getLink();
    }

    public String getLink() {
        return link;
    }
}
