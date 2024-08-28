package br.com.nt.easystay.domain.component;

import br.com.nt.easystay.domain.model.Logo;
import br.com.nt.easystay.domain.service.LogoService;
import org.springframework.stereotype.Component;

@Component
public class LogoComponent {

    private final LogoService logoService;

    public LogoComponent(LogoService logoService) {
        this.logoService = logoService;
    }

    public Logo finByIdLogo(final Integer id) {
        return logoService.findById(id);
    }
}
