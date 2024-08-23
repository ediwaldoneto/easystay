package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.mapper.LogoMapper;
import br.com.nt.easystay.domain.exception.BusinessException;
import br.com.nt.easystay.domain.exception.LogoNotFoundException;
import br.com.nt.easystay.domain.model.Logo;
import br.com.nt.easystay.domain.repository.LogoRepository;
import br.com.nt.easystay.domain.service.LogoService;
import br.com.nt.easystay.infrastructure.mapper.response.LogoResponseMapper;
import br.com.nt.easystay.infrastructure.response.LogoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LogoServiceImpl implements LogoService {

    private final LogoRepository logoRepository;

    @Override
    public void saveFile(String name, MultipartFile file) throws IOException {
        handleNonNullName(name);

        Objects.requireNonNull(file, () -> {
            throw new BusinessException("Logo cannot be null", 400);
        });

        final Logo logo = LogoMapper.toEntity(name, file);

        logoRepository.save(logo);
    }

    @Override
    public void updateFile(String name, MultipartFile file) throws IOException {
        final Logo logo = logoRepository.findByName(name)
                .orElseThrow(() -> new LogoNotFoundException("Logo not found with name" + name));
        logo.setImage(file.getBytes());

        logoRepository.save(logo);
    }

    @Override
    public byte[] findById(Integer id) {
        return logoRepository.findById(id)
                .orElseThrow(() -> new LogoNotFoundException("Logo not found with" + id)).getImage();
    }

    @Override
    public byte[] findByName(String name) {
        handleNonNullName(name);

        return logoRepository.findByName(name)
                .orElseThrow(() -> new LogoNotFoundException("Logo not found with name" + name)).getImage();
    }

    @Override
    public List<LogoResponse> findAll() {
        return logoRepository.findAll()
                .stream()
                .map(LogoMapper::toDTO)
                .map(LogoResponseMapper::toResponse).toList();
    }

    @Override
    public void delete(String name) {
        handleNonNullName(name);

        final Logo logo = logoRepository.findByName(name)
                .orElseThrow(() -> new LogoNotFoundException("Logo not found with name" + name));
        delete(logo);
    }

    @Override
    public void delete(Integer id) {

        final Logo logo = logoRepository.findById(id)
                .orElseThrow(() -> new LogoNotFoundException("Logo not found with" + id));

        delete(logo);
    }

    @Override
    public void delete(Logo logo) {
        logoRepository.delete(logo);
    }

    private void handleNonNullName(final String name) {
        Objects.requireNonNull(name, () -> {
            throw new BusinessException("Name cannot be null", 400);
        });
    }
}
