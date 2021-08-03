package br.com.zupacademy.anaminadakis.mercadolivre.imagens.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class ImagemRequest {

    @NotNull
    @Size(min = 1)
    private Set<MultipartFile> imagens = new HashSet<>();

    public Set<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(Set<MultipartFile> imagens) {
        this.imagens = imagens;
    }
}
