package br.com.nt.easystay.infrastructure.controller;

import br.com.nt.easystay.domain.service.EmailTemplateService;
import br.com.nt.easystay.infrastructure.request.EmailTemplateRequest;
import br.com.nt.easystay.infrastructure.response.EmailTemplateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email-template")
@RequiredArgsConstructor
public class EmailTemplateController {

    private final EmailTemplateService emailTemplateService;

    @PostMapping
    public ResponseEntity<Void> saveEmailTemplate(@RequestBody EmailTemplateRequest request) {
        emailTemplateService.saveTemplate(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmailTemplateResponse>> findAll() {
        return new ResponseEntity<>(emailTemplateService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTemplate(final Integer id) {
        emailTemplateService.deleteTemplate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
