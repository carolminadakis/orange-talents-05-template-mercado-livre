package br.com.zupacademy.anaminadakis.mercadolivre.imagens.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface Uploarder {

    /**
     *
     * @param imagens
     * @return urls para as imagens enviadas
     */
    Set<String> envia(Set<MultipartFile> imagens);
}
