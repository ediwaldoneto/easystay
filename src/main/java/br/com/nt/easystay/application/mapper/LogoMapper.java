package br.com.nt.easystay.application.mapper;

import br.com.nt.easystay.application.dto.LogoDTO;
import br.com.nt.easystay.domain.model.Logo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class LogoMapper {

    private LogoMapper() {
    }

    public static LogoDTO toDTO(final Logo logo) {
        return LogoDTO.builder()
                .id(logo.getId())
                .name(logo.getName())
                .image(logo.getImage())
                .build();
    }

    public static Logo toEntity(final String name, final MultipartFile file) throws IOException {
        Logo logo = new Logo();
        logo.setName(name);
        logo.setImage(file.getBytes());
        return logo;

    }
}
