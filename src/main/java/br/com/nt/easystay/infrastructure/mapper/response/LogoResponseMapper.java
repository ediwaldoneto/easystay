package br.com.nt.easystay.infrastructure.mapper.response;

import br.com.nt.easystay.application.dto.LogoDTO;
import br.com.nt.easystay.infrastructure.response.LogoResponse;

public class LogoResponseMapper {

    private LogoResponseMapper() {
    }

    public static LogoResponse toResponse(final LogoDTO dto) {
        return LogoResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
