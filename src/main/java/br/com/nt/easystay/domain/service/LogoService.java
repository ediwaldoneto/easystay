package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Logo;
import br.com.nt.easystay.infrastructure.response.LogoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LogoService {

    void saveFile(final String name, final MultipartFile file) throws IOException;

    void updateFile(final String name, final MultipartFile file) throws IOException;

    Logo findById(final Integer id);

    Logo findByName(final String name);

    byte[] findByIdImage(final Integer id);

    byte[] findByNameImage(final String name);

    List<LogoResponse> findAll();

    void delete(final String name);

    void delete(final Integer id);

    void delete(final Logo logo);
}
