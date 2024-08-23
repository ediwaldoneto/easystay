package br.com.nt.easystay.infrastructure.controller;

import br.com.nt.easystay.domain.service.LogoService;
import br.com.nt.easystay.infrastructure.response.LogoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/logos")
@RequiredArgsConstructor
public class LogoController {

    private final LogoService logoService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadLogo(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws IOException {
        logoService.saveFile(name, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<LogoResponse>> logoList() {
        return new ResponseEntity<>(logoService.findAll(), HttpStatus.OK);
    }

    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> updateUploadedFile(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws IOException {
        logoService.updateFile(name, file);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUploadFile(@RequestParam("name") String name) {
        logoService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<byte[]> downloadLogo(@PathVariable String name) {
        byte[] image = logoService.findByName(name);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        headers.setContentDispositionFormData("attachment", "logo_" + name + ".jpg");

        return new ResponseEntity<>(image, headers, HttpStatus.OK);

    }
}
